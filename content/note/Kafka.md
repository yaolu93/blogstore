tokafka: real-time event driven application

produce comsume
fast
high accuracy
in order
fault tolerant


栈：负责运行。
一个请求一个线程，就会出现没有内存去分配的形况。 栈内存溢出。（cristiano问过这个。）
一个服务器承载不了大量用户的访问。
方法一：可以多加一个服务，（分布式）
默认的服务器地址是80，可是我们现在加入一个81的新服务器，用户应该怎么找到呢。~

分布式的用户1：（服务器端的负载均衡） Zookeeper

用户1； 用户2； 用户3
集群：
负载均衡器（80）。
服务器1（81），服务器2（82）
缓存服务器（redis）

在集群里面可以让两个服务之间的数据互相拷贝。效果就不可能高。
sun公司开发了框架rmi： 遵循了协议 rpc==> ejb (后面出现了这个封装框架)==》spring

分布式的用户2：（客户端的负载均衡）

用户1； 用户2； 用户3
集群：

注册中心：
server1->81
server2->82
server3->83

服务器1（81），服务器2（82）, 服务器3（83）,
缓存服务器（redis）

 用户的访问就不走集群了，就走注册中心了。（轮询）

 分布式的系统。 把一个系统分成几个系统就叫分布式系统，那么他们是怎么交互的呢？

 User1: User 2: User 3 :     User 4
    3                            1                
 server(IP1: 80) 【订单】,     server2(IP2: 81)【财务】,  Server2(IP3: 82) 【管理】, Server3 (IP3:83)【物流】
 不在在服务之间直接访问， 而是要在它们之间加一个消息系统===》 【Message】


 Flume:海量日志的信息采集工具。 缺点：数据主要保存在内存当中，也容易丢失： 消费者不容易


Kafka:每个系统只从消息系统中获取它们关系的数据。

Zookeeper( 协调和调度中心)

要学位运算！！！ 算法！ 所有框架的底层。

 为什么需要消息队列：
 1, 解耦
 2，冗余
 3，扩展性
 4， 灵活性+峰值处理能力
 5， 可恢复性
 6，顺序保证
 7，缓冲
 8，异步通信
 
 只有Leader有读写功能，Followers在Leader挂了之后才有读写能力，平时都不可以读写。

 每一个Partition 都是有序的队列。

 top 是列出linux的内存和cpu占用信息

 Kafka 的log 追踪。

 Kafka 零复制问题：
 因为Kafka是直接向系统底层发指令，读写文件，而不是像JVM 那样，读一次到缓存，再读一次到readbuffer,写一次到writebuffer，再写一次到缓存，总共四次。
 Kafka是直接发指令到系统，让其帮忙写入。底层指令为 fork()。JUC 写实复制技术

 log 是记录日志，
 index 是记录偏移量和字节的位置。


Kafka is able to seamlessly handle multiple producers
争取以后在 Emacs上开发了


特么的以后配置好的环境真的不能手贱去修改的啊

 
 
   
1. 同步发送消息，异步发送消息。

char 的取值范围和 byte, short, int 的都不一样，在向上转型的时候我们需要格外留意。


那我们也有必要学一下ANT了吗？

history 
!1031

Kafka Schema Registry


to know what is the git stage
and what


shell command 真心好玩呀呀呀呀～～


can we please retry the leetcode tomorrow?!

git upgrade and emacs~!

let's update git first


git 升级之后整个人都好了。。。
今天侥幸是coworker的配置文件除了问题。
那万一是要自己找错误该怎么办。
kafka 还没有看熟练吧。

好消息是emacs tramp 可以用了。


如果是Jenkins没有用好那么造成的冲突可不比代码造成的冲突低呀


我们今天来试一下改变一下， host_name来看看是反应。



我们只要看一下Github上面的日期就知道这，日子，这时光，飞得究竟得有多狠。

新的一天，卷动开始。

今天要是把那个  test case 10 搞出来那应该还是挺有成就感的。

记得给那个妹子发面试题目



这test case 10  还是没搞出来咯，不过对K8S有了进一步的了解，真的又被Mark卷到啊。


今天这个年是过得真舒服啊


how are you ? I m fine. thank you~