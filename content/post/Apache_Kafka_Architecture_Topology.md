---
title: "Apache_Kafka_Architecture_Topology"
date: 2024-04-19
lastmod: 2026-11-11
draft: false
tags: ["Java"]
categories: ["Kafka"]
author: "Yao"


---

> 本文沿着一条订单数据链路，理解 Kafka 如何接收、保存、处理和分发数据，以及各个组件为什么要分工。

# Kafka and components：

Apache Kafka 是一个**分布式事件流平台**。这里的“事件”就是已经发生的一件事，例如“订单创建了”“订单支付成功了”。事件不断产生，就形成了事件流。

本文以 Kafka 4.x 的 KRaft 架构和常规 Consumer Group 为背景；连接器和 Schema Registry 的能力，需要结合具体实现与版本理解。

可以把 Kafka 想成一本允许多个系统共同使用的流水账：订单系统负责记账，库存、物流和统计系统各自按自己的进度阅读。统计系统临时停机，不需要订单系统等它恢复后才能继续记账。生产者与消费者通过 Kafka 解耦。[Kafka 官方入门](https://kafka.apache.org/41/getting-started/introduction/)

## 先看一条完整的数据链路

假设我们希望把数据库中的订单变化同步到 Kafka，实时统计销售额，再把结果写入报表数据库：

```text
Orders DB
    |
    v
Source Connector (Kafka Connect worker)
    |
    v
orders.raw (topic in Kafka brokers)
    |
    v
Stream processing application (Kafka Streams)
    |
    v
sales.by-user (topic in Kafka brokers)
    |
    v
Sink Connector (Kafka Connect worker)
    |
    v
Reporting DB

Schema Registry <--> Schema-aware serializers / deserializers / converters
```

最后一行表示格式信息的查询关系。**业务消息经过 Kafka，Schema Registry 管理这些消息的格式约定。** 客户端通常会缓存查询到的 Schema。

| 组件 | 负责什么 | 在订单例子中的作用 |
| --- | --- | --- |
| Producer | 向 Kafka 写消息的客户端 | 订单服务直接发布“支付成功”事件 |
| Broker | 保存分区数据、响应读写请求的 Kafka 服务节点 | 保存订单消息及其副本 |
| Consumer | 从 Kafka 读取消息的客户端 | 库存服务读取订单事件 |
| Source Connector | 将外部系统的数据导入 Kafka | 捕获订单数据库的变化 |
| Sink Connector | 将 Kafka 数据导出到外部系统 | 把统计结果写入报表库 |
| Streamer | 本文用来表示流处理应用 | 持续计算每个用户的销售额 |
| Transformer | 本文用来表示数据转换步骤 | 添加来源字段、调整字段名称 |
| Schema Registry | 管理消息结构及版本的独立服务 | 约定订单金额、订单编号等字段的类型 |

这些组件不一定要全部部署。业务服务可以直接使用 Producer 和 Consumer；只有需要对接外部系统、进行流处理或管理 Schema 时，才加入相应组件。Kafka Connect 是集成框架，Kafka Streams 是客户端库；它们的代码不在 Broker 中执行。[Kafka APIs](https://kafka.apache.org/41/getting-started/introduction/#kafka-apis)、[Kafka Connect](https://kafka.apache.org/41/kafka-connect/overview/)、[Kafka Streams](https://kafka.apache.org/41/streams/core-concepts/)

## Kafka Server:

### 1. Broker 与 Cluster：谁负责保存数据？

一个 Broker 是一个 Kafka 服务进程；多个 Broker 共同组成集群（Cluster）。Topic 的分区及其副本分布在这些 Broker 上。

客户端配置的 `bootstrap.servers` 是**发现集群的入口地址**。客户端先获取元数据，了解目标分区位于哪里，再与负责该分区的 Broker 通信。入口地址不代表所有消息都必须经过同一台机器转发。[Kafka 协议与客户端路由](https://kafka.apache.org/41/design/protocol/)

### 2. Topic、Partition、Key 与 Offset：消息放在哪里？

| 概念 | 通俗解释 | 示例 |
| --- | --- | --- |
| Topic | 按业务用途命名的一类消息 | `orders.raw` 保存原始订单事件 |
| Partition | Topic 内可独立追加、读取的一段日志 | `orders.raw` 分成 P0、P1、P2 |
| Key | 客户端可用来决定分区的消息键 | 使用订单编号 `O-1001` |
| Value | 消息的业务内容 | 金额、状态、用户编号 |
| Offset | 一条记录在某个分区中的位置编号 | P1 中的 offset 42 |

分区里的消息按写入顺序追加：

```text
Topic: orders.raw

P0: [offset 0] -> [offset 1] -> [offset 2]
P1: [offset 0] -> [offset 1] -> [offset 2] -> [offset 3]
P2: [offset 0] -> [offset 1]
```

需要特别区分三个编号：

- `orderId`：业务上的订单编号，可以用作 Key。
- `eventId`：业务上的事件编号，可以用于业务去重。
- `offset`：Kafka 在分区内分配的位置，与业务编号没有对应关系，也不是整个 Topic 的全局编号。

Kafka **不会自动根据 Value 中的 `eventId` 分配分区或去重**。分区由生产者的分区逻辑决定；在常见的按 Key 分区方式下，Key 相同、分区数量和分区策略不变时，消息会进入同一个分区。没有 Key 或显式指定分区时，规则会不同。[Producer 分区配置](https://kafka.apache.org/41/configuration/producer-configs/#partitioner.class)

如果希望同一个订单的“创建、支付、发货”按写入顺序读取，可以使用 `orderId` 作为 Key。Kafka 保证的是**单个分区内的日志顺序**，跨分区没有统一顺序；业务端并发发送、重试配置和消费者自行并行处理，也需要相应设计。[分区与顺序](https://kafka.apache.org/41/getting-started/introduction/#main-concepts-and-terminology)

### 3. Producer 与 Consumer：写入和读取如何衔接？

Producer 把事件序列化为字节后写入 Kafka；Consumer 主动拉取消息，再反序列化为程序可以使用的数据。

下面是订单事件的可读示意，实际存储格式可以是 JSON、Avro 或其他格式：

```json
{
  "eventId": "E-1001",
  "orderId": "O-1001",
  "userId": "U-8",
  "status": "PAID",
  "amountCents": 19900,
  "currency": "EUR",
  "occurredAt": "2026-11-11T10:00:00Z"
}
```

这里将金额表示为最小货币单位的整数，例如 `19900` 表示 199 欧元，方便后面的统计示例避免浮点小数误差。`orderId` 出现在 Value 中，并不意味着它已经被设置为 Kafka 的 Key，发送时还要单独指定。

### 4. Consumer Group：多个消费者如何分工？

在常规 Consumer Group 的订阅模式下，同一组中的消费者共同分担分区：**同一个分区同一时刻只分配给组内一个消费者，一个消费者可以负责多个分区。** 不同组可以独立读取同一份数据。[KafkaConsumer 官方说明](https://kafka.apache.org/41/javadoc/org/apache/kafka/clients/consumer/KafkaConsumer.html)

```text
orders.raw: P0, P1, P2

Group: inventory
  Consumer A -> P0, P1
  Consumer B -> P2

Group: analytics
  Consumer C -> P0, P1, P2
```

库存组和统计组互不“抢消息”，各自维护消费进度。如果这里只有 3 个分区，单个组增加到 4 个消费者，也会有消费者分不到分区。消费者加入、退出等情况可能触发重新分配，这个过程称为 Rebalance。

每个组会为各个分区记录已提交的消费位置。可以把它理解为书签：提交 offset 43，表示恢复时从该分区的 43 开始读取，而不是再读 42。**拉取到了消息，不代表业务已经处理成功。**

例如，先更新库存、再提交消费位置，若两步之间进程崩溃，恢复后可能重复更新库存。因此消费者通常还需要使用业务编号做幂等处理：同一笔业务重复执行，结果也不会重复生效。Consumer Lag 则帮助观察消费进度落后了多少。[消费位置与提交](https://kafka.apache.org/41/javadoc/org/apache/kafka/clients/consumer/KafkaConsumer.html)

### 5. Retention 与 Replication：读过是否删除？机器坏了怎么办？

**保留策略（Retention）**决定数据何时可以被清理。消息通常不会因为某个消费者读完就删除；只要数据仍然保留，就可以按需要重新读取。例如统计程序出错后，可以在明确业务影响的前提下调整消费位置，重新计算。

按时间或容量清理日志，以及按 Key 压缩日志（Log Compaction），是不同的策略。压缩日志适合保留每个 Key 的最新状态，不适合当作永久保留全部历史事件的保证；清理也不是逐条消息到期后立即发生。[Kafka 日志压缩](https://kafka.apache.org/41/design/design/#log-compaction)

**副本（Replica）**则用于提高容错能力。以一个分区配置 3 个副本为例，它们位于不同 Broker：一个是 Leader，其他是 Follower。常规写入发给 Leader，Follower 从 Leader 复制数据。副本因子 3 表示总共 3 份，不是“1 份主数据再加 3 份备份”。

ISR（In-Sync Replicas）表示当前与 Leader 保持同步的副本集合，包含 Leader 本身。Leader 故障时，可以按集群的选主规则选出新的 Leader。[Kafka 副本机制](https://kafka.apache.org/41/design/design/#replication)

下面三个配置分别解决不同问题，位置也不同：

| 配置 | 配置位置 | 作用 |
| --- | --- | --- |
| `--replication-factor 3` | 创建 Topic 时的命令行参数 | 为每个分区安排 3 个副本 |
| `min.insync.replicas=2` | Topic 配置，可继承 Broker 默认值 | 配合 `acks=all`，要求写入时至少有 2 个 ISR |
| `acks=all` | Producer 配置 | 等待当前 ISR 全部确认写入 |

当 ISR 缩减为 1 个时，上述组合会拒绝这样的写入，避免只剩一份数据仍然报告成功。`acks=all` 等待的是**当前 ISR**，不一定是所有已分配副本；写入被确认，也不代表消费者的库存、发货等业务已经完成。[写入确认配置](https://kafka.apache.org/41/configuration/producer-configs/#acks)、[最小 ISR 与持久性](https://kafka.apache.org/41/design/design/#availability-and-durability-guarantees)

### 6. Controller 与 KRaft：谁管理集群？

Broker 负责业务数据的读写；Controller 负责集群元数据和协调管理，例如 Broker 注册、分区 Leader 变更等。可以把 Controller 理解为维护“哪个分区由谁负责”这张分工表的管理者。

Kafka 4.0 起只支持 KRaft 模式，已经移除了 ZooKeeper 模式。因此阅读旧架构图时，需要注意其中的 ZooKeeper 属于旧部署方式。[Kafka 4.0 升级说明](https://kafka.apache.org/40/getting-started/upgrade/)

KRaft 使用 Controller 仲裁组维护元数据。以 3 个 Controller 为例，需要多数节点可用，才能保持该仲裁组可用；它与业务 Topic 的“3 副本”是两个不同概念。开发环境可以让进程同时承担 Broker 和 Controller 角色，关键生产环境通常将角色分开部署。[KRaft 角色与仲裁组](https://kafka.apache.org/41/operations/kraft/#controllers)

# Source connector

Source Connector 的方向是 **外部系统 → Kafka**。“Source”以 Kafka 为参照，表示数据从哪里进入 Kafka。

如果业务服务直接调用 Producer API，就需要自己实现发送逻辑；如果需求是把已有数据库或其他系统中的数据持续导入 Kafka，可以使用适合该系统的 Connector 插件。

## Connector、Task 与 Worker 怎么理解？

| 概念 | 职责 | 类比 |
| --- | --- | --- |
| Connector | 描述连接哪个系统、同步什么数据，并组织任务 | 一份搬运计划 |
| Task | 实际执行读取或写入 | 执行计划的搬运工 |
| Worker | 运行 Connector 和 Task 的 Kafka Connect 进程 | 安排工人执行任务的工作站 |

Source Task 从外部系统读数据，交给 Connect 运行时发送到 Kafka。Task 的实际数量受插件和数据源能力限制，`tasks.max=8` 只是上限，不保证启动 8 个并行任务。[Kafka Connect 用户指南](https://kafka.apache.org/41/kafka-connect/user-guide/#configuring-connectors)

Kafka Connect 可以独立于 Broker 部署。分布式模式下，多个 Worker 可以分担任务，并在成员变化后重新分配；框架管理配置、任务状态和相应的进度信息。[Connect 运行模式](https://kafka.apache.org/41/kafka-connect/user-guide/#running-kafka-connect)

## 数据库中的变化怎么进入 Kafka？

常见思路有两类，具体能力由插件决定：

- **轮询查询**：周期性查询新增或更新的记录，需要合适的增量列；仅靠普通增量查询，往往难以完整捕获删除和短时间内的多次变化。
- **CDC（Change Data Capture）**：捕获数据库的变更流。一些插件先做初始快照，再持续读取数据库日志中的变更；需要数据库和插件支持相应能力。

例如 Debezium 的 MySQL Connector 就支持初始快照和后续的 Binlog 变更捕获。实际 CDC 消息可能带有变更前、变更后等封装；本文的订单 JSON 仅演示业务字段，接入时需要按实际消息格式提取和映射。[Debezium MySQL Connector](https://debezium.io/documentation/reference/stable/connectors/mysql.html)

以订单同步为例，可以把链路理解为：

```text
Order row changes
    -> Source Task reads changes
    -> Optional single-record transformations
    -> Converter serializes the key and value
    -> Kafka topic: orders.raw
```

Connect 中的 Converter 负责连接器内部数据与 Kafka 字节数据之间的转换，Key 和 Value 可以分别配置。选用什么 Converter，需要与下游读取格式保持一致。[Connect Converter 配置](https://kafka.apache.org/41/kafka-connect/user-guide/#running-kafka-connect)

恢复时，还需要知道“外部系统读到哪里了”，例如某个数据库日志位置。这个**源端进度**与消息进入 Kafka 后获得的分区 offset 不是同一回事。能否精确恢复、是否可能重复发送，需要结合插件和运行配置判断。[Source Connector 开发模型](https://kafka.apache.org/41/kafka-connect/connector-development-guide/)

# Sink connector

Sink Connector 的方向是 **Kafka → 外部系统**。这里的“Sink”指数据最终流向的目标，例如数据库、对象存储或搜索系统。

```text
Kafka topic: sales.by-user
    -> Converter deserializes the key and value
    -> Optional single-record transformations
    -> Sink Task writes records
    -> Reporting DB
```

Sink Connector 通过 `topics` 或 `topics.regex` 等配置选择输入 Topic。具体如何映射字段、建表、写入和处理删除，由所选插件决定。[Sink Connector 配置](https://kafka.apache.org/41/kafka-connect/user-guide/#configuring-connectors)

## 最容易忽略的点：写成功与提交进度是两件事

假设 Sink 已经把一条统计结果写入报表库，但还没来得及提交消费进度就崩溃。恢复后，这条记录可能再次被处理。

如果每次执行的是“销售额加 199”，重复处理可能导致多加一次。若输出消息表示“用户当前累计销售额为 399”，目标库又支持按用户编号执行幂等 Upsert（存在则更新，不存在则插入），就更容易让重复写入收敛到同一结果。但仍需设计更新顺序，避免较旧结果覆盖较新结果。

因此，不能看到 Kafka 支持事务，就推断所有外部数据库写入都自动具备 Exactly-once。端到端效果需要 Connect、具体 Sink 插件和目标系统共同配合，可能涉及幂等主键、事务或去重记录。[Kafka 对外部系统交付语义的说明](https://kafka.apache.org/41/design/design/#message-delivery-semantics)

## 如何判断同步链路是否正常？

需要一起观察：Task 是否运行、消费积压是否持续增大、目标系统是否写入成功，以及失败记录如何处理。

部分错误可以通过配置重试或送入死信队列（DLQ，用于隔离失败记录的 Topic），但不能假定 DLQ 能兜住所有异常。格式转换、单条记录处理和目标系统连接失败可能走不同的错误路径，需要核对插件支持范围。[Connect 错误处理](https://kafka.apache.org/41/kafka-connect/user-guide/#error-reporting-in-connect)

# Streamer

这里的 Streamer 指**持续处理事件的应用**，不是 Kafka Broker 中一个叫“Streamer”的固定服务。下面用 Kafka Streams 来说明。

Kafka Streams 是嵌入应用的客户端库。应用读取 Kafka 中的数据，执行计算，再把结果写回 Kafka；应用实例由我们自行部署和运行。[Kafka Streams 核心概念](https://kafka.apache.org/41/streams/core-concepts/)

## Topology：把计算步骤连接起来

Topology 是一张处理流程图：节点表示处理步骤，连线表示数据从哪里流向哪里。例如：

```text
orders.raw
    -> filter: keep PAID events
    -> group by userId + currency
    -> sum amountCents
    -> sales.by-user
```

这里按“用户 + 币种”累计金额，避免把欧元和美元直接相加。若输入 Key 原本是 `orderId`，按用户和币种聚合前通常需要重新按新 Key 分区，让相同分组的数据进入同一个计算任务。Streams 可为这类操作创建内部 Repartition Topic。[Streams DSL 的分组与重分区](https://kafka.apache.org/41/streams/developer-guide/dsl-api/)

还要明确输入的业务含义：如果同一订单的多条状态更新都带着 `PAID`，简单筛选后求和会重复统计。应根据事件模型选择支付事件流，或先按 `eventId`、`orderId` 等业务标识去重。Kafka 的处理保证不会自动识别“这两条不同记录实际上是同一笔业务”。

## KStream 与 KTable：事件过程和当前状态

| 概念 | 如何理解同一个 Key 的多条记录 | 订单例子 |
| --- | --- | --- |
| KStream | 每条记录都是一个独立事件 | “创建订单”和“订单支付”都需要处理 |
| KTable | 每条记录表示该 Key 的状态更新 | 同一订单从 `CREATED` 更新为 `PAID` |

KTable 中同一个 Key 的后续更新会替代之前的状态，非空 Key 对应的空 Value（Tombstone）表示删除。它是在流处理应用中维护的表视图，不等于 Kafka Broker 自动提供了一张可以随意做 SQL 查询的关系表。

例如按用户和币种累计金额，会得到一张不断更新的结果表。把更新写回 Topic 后，Sink 可以将每个 Key 的最新金额写入报表库；这与“收到一条事件就在数据库中加一次金额”的语义不同。[Streams DSL 数据模型](https://kafka.apache.org/41/streams/developer-guide/dsl-api/)

## 无状态与有状态处理

| 类型 | 是否需要记住之前的数据 | 例子 |
| --- | --- | --- |
| 无状态处理 | 不需要，只看当前记录 | 过滤未支付事件、调整字段格式 |
| 有状态处理 | 需要保存并更新中间结果 | 累计销售额、时间窗口统计、关联另一条数据流 |

例如累计金额时，需要记住用户之前已经累计了多少，这就是 State Store 的作用。Kafka Streams 的持久化状态通常保存在应用本地；启用相应的 Changelog 后，可以利用 Kafka 中的变更日志恢复状态，而不是要求程序重启后从零开始。[状态存储与容错](https://kafka.apache.org/41/streams/core-concepts/#state-stores)

## Window：按发生时间统计，还是按收到时间统计？

一笔付款在 10:00:59 发生，因为网络延迟，到 10:01:03 才被处理：

- Event Time 是事件实际发生的时间。
- Processing Time 是应用处理它的时间。
- Window 把连续事件放入某个时间范围，例如每分钟的固定窗口。

若按事件时间统计，这笔付款属于 10:00 这一分钟；前提是应用正确提取了事件时间，并配置允许相应的迟到范围（Grace Period）。超过允许范围的记录，可能无法再更新该窗口。消息里写了 `occurredAt`，不会自动让 Streams 把这个字段当作记录时间。[时间语义](https://kafka.apache.org/41/streams/core-concepts/#time)、[窗口与迟到记录](https://kafka.apache.org/41/streams/developer-guide/dsl-api/)

## Exactly-once 的边界

Kafka Streams 可以通过 `processing.guarantee=exactly_once_v2` 启用 Exactly-once 处理，把输入消费位置、Kafka 输出以及状态变更的提交协调起来。这不表示故障时处理代码绝不会重新执行，而是让已提交的处理结果保持一致。[Streams 处理保证配置](https://kafka.apache.org/41/streams/developer-guide/config-streams/#processing-guarantee)

如果处理代码中还调用了“扣款 HTTP 接口”或直接写外部数据库，这些外部操作不会自动加入 Kafka 事务，仍需业务幂等或其他协调机制。[Kafka 事务与外部系统](https://kafka.apache.org/41/design/design/#message-delivery-semantics)

# Transformer

Transformer 表示**数据转换这一步做什么**。它可以是 Connect 中的轻量转换，也可以是流处理 Topology 中的一个处理节点，不一定需要单独部署一个服务。

## Connect 中的 SMT：处理单条记录

Kafka Connect 的 Single Message Transform（SMT）适合对当前记录做轻量修改，例如：

- 给每条记录增加固定的来源标记。
- 重命名、挑选或移除字段。
- 提取某个字段，或调整 Topic 路由。

例如，订单数据经过 SMT 后增加 `source` 字段：

```json
{
  "orderId": "O-1001",
  "amountCents": 19900,
  "source": "orders-db"
}
```

下面是添加静态字段的**配置片段**，需要放入完整的 Connector 配置中；输入 Value 应是支持添加字段的 Map 或 Struct：

```json
{
  "transforms": "AddSource",
  "transforms.AddSource.type": "org.apache.kafka.connect.transforms.InsertField$Value",
  "transforms.AddSource.static.field": "source",
  "transforms.AddSource.static.value": "orders-db"
}
```

`AddSource` 是自定义别名，`InsertField$Value` 表示修改消息 Value。多个 SMT 可以按照 `transforms` 中列出的顺序串联。[Connect SMT 与 InsertField 示例](https://kafka.apache.org/41/kafka-connect/user-guide/#transformations)

## 什么情况应该放到流处理应用？

如果需要“统计最近 5 分钟的销售额”“关联用户资料”“记住已处理过的订单”，就涉及时间、多个事件或状态，适合用 Streams 等流处理工具实现。

| 需求 | 可放在哪一层 |
| --- | --- |
| 数据源接入、目标系统写入 | Source / Sink Connector |
| 添加固定字段、简单字段整理 | Connect SMT |
| 根据当前记录做业务计算 | 流处理中的 `mapValues`、`filter` 等操作 |
| 聚合、窗口、跨记录关联 | 有状态流处理 |
| 检查消息格式版本能否兼容 | Schema Registry 配合相应客户端 |

如果转换改变了 Key，还需要重新考虑分区和顺序：同一业务对象是否仍会进入同一分区？下游是否依赖原来的 Key？字段整理也可能改变数据契约，需要与消费者共同确认。

# Register Schema

这一部分通常称为 **Schema Registry（模式注册中心）**；Register Schema 是“注册一份 Schema”这个动作。

Schema 可以理解为消息的“字段说明书”：有哪些字段、每个字段是什么类型、哪些允许为空。Schema Registry 保存说明书及其版本，供生产者、消费者和相应的 Connect Converter 使用。

以 Confluent Schema Registry 为例，它是独立于 Kafka Broker 的服务，支持 Avro、Protobuf 和 JSON Schema。它不是 Apache Kafka Broker 内置的必需组件。[Schema Registry 的工作方式](https://docs.confluent.io/platform/current/schema-registry/fundamentals/index.html)

## 为什么单靠 JSON 字段名还不够？

假设生产者原来发送 `"amountCents": 19900`，后来改成 `"amountCents": "199.00 EUR"`。这两条消息都可以是合法 JSON，但消费者原本按整数计算，遇到新格式就可能失败。

Schema Registry 让团队显式管理这种格式变化。它能按配置检查 Schema 的兼容性，但**无法判断所有业务含义**：即使类型仍是整数，把金额单位从“分”改成“元”，也可能悄悄算错。

## 一份订单 Schema 长什么样？

下面用 Avro 演示一份精简的消息结构，不是完整的订单业务模型：

```json
{
  "type": "record",
  "name": "OrderPaid",
  "namespace": "com.example.orders",
  "fields": [
    { "name": "orderId", "type": "string" },
    { "name": "amountCents", "type": "long" },
    { "name": "currency", "type": "string" },
    { "name": "source", "type": ["null", "string"], "default": null }
  ]
}
```

这里 `amountCents` 要求整数，`source` 允许为空并提供默认值。是否需要这些字段、如何解释它们，仍然是业务双方的约定。[Avro 字段类型与默认值](https://avro.apache.org/docs/1.12.0/specification/#records)

## Schema 如何与消息一起工作？

以常见的 Confluent Avro 序列化方式为例：

1. 生产者使用支持 Schema Registry 的 Serializer，注册或查找 Schema。
2. Serializer 按该 Schema 编码消息，并在消息中携带 Schema ID 等格式信息。
3. 业务消息写入 Kafka Topic。
4. 消费者使用匹配的 Deserializer，根据 ID 查询或复用已缓存的 Schema，解码消息。

Schema Registry 不需要接收每一条业务消息，Kafka Broker 也不会因为部署了 Registry 就自动校验所有消息的业务字段。普通客户端发送任意字节数据时，不会自动得到上述格式管理能力。[Schema Registry 与客户端序列化](https://docs.confluent.io/platform/current/schema-registry/fundamentals/index.html)

## Subject、Version 与兼容性

Subject 是组织一组 Schema 版本的名称。在默认的 Topic 命名策略下，`orders.raw` 的 Value Schema 通常属于 `orders.raw-value`，Key Schema 通常属于 `orders.raw-key`。同一 Subject 下可以有多个版本；**版本号与 Schema ID 是不同概念**，不能把二者混用。[Schema Subject 命名策略](https://docs.confluent.io/platform/current/schema-registry/fundamentals/serdes-develop/index.html#subject-name-strategy)

| 兼容性 | 检查的问题 |
| --- | --- |
| BACKWARD | 使用新 Schema 的消费者，能否读取旧 Schema 写出的数据？ |
| FORWARD | 使用旧 Schema 的消费者，能否读取新 Schema 写出的数据？ |
| FULL | 上述两个方向是否都兼容？ |

普通兼容模式通常检查上一个版本；对应的 `*_TRANSITIVE` 模式检查所有先前版本。具体改动能否兼容，取决于 Avro、Protobuf、JSON Schema 各自的规则，不能一概认为“增加字段一定兼容”。

例如，对 Avro 而言，为新字段提供合适默认值，通常有助于新读者读取没有该字段的旧数据。在 BACKWARD 策略下，升级消费者再让生产者开始发送新格式，是常见的演进顺序。[Schema 演进与兼容性](https://docs.confluent.io/platform/current/schema-registry/fundamentals/schema-evolution.html)

