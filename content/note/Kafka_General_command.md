```bash

kafka-topics --list --bootstrap-server odfhost1:9092
kafka-topics --describe --bootstrap-server odfhost1:9092 --topic streams-input
kafka-topics --delete --bootstrap-server odfhost1:9092 --topic streams-input
kafka-topics --create --bootstrap-server odfhost1:9092 --replication-factor 1 --partitions 1 --topic streams-input

kubectl -n <namespace> exec -ti <Kafka Pod ID> -- ./bin/kafka-topics.sh --version
kubectl -n cgf exec -ti odf-cluster-kafka-0 -- ./bin/kafka-topics.sh --version

sudo kafka-avro-console-consumer --from-beginning --bootstrap-server odfhost1:9092 --topic streams-input --property print.key=true --key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
sudo kafka-avro-console-consumer --from-beginning --bootstrap-server odfhost1:9092 --topic streams-output --property print.key=true --key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
sudo kafka-avro-console-consumer --from-beginning --bootstrap-server odfhost1:9092 --topic streams-duplicate --property print.key=true --key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
sudo kafka-avro-console-consumer --from-beginning --bootstrap-server odfhost1:9092 --topic streams-error --property print.key=true --key-deserializer=org.apache.kafka.common.serialization.StringDeserializer

### for json
sudo kafka-console-consumer --from-beginning --bootstrap-server localhost:9092 --topic topic-distributor


### to check the record headers
sudo kafka-avro-console-consumer --from-beginning --bootstrap-server odfhost1:9092 --topic streams-input --property print.key=true --key-deserializer=org.apache.kafka.common.serialization.StringDeserializer --property print.headers=true


sudo systemctl status confluent*
sudo systemctl restart confluent-kafka-connect
sudo systemctl restart confluent-kafka
sudo systemctl restart confluent-zookeeper
sudo systemctl restart confluent-schema-registry
sudo systemctl restart odf-streamer

sudo systemctl status confluent-kafka-connect
sudo systemctl status confluent-kafka
sudo systemctl status confluent-zookeeper
sudo systemctl status confluent-schema-registry
sudo systemctl status odf-streamer

kubectl delete -f ~/workspace/ngm-test-container/jenkins/resources/minikube/kcat.yaml
kubectl apply -f ~/workspace/ngm-test-container/jenkins/resources/minikube/kcat.yaml       

## operate kafka-topic
kafka-topics --list --bootstrap-server odfhost1:9092
kafka-topics --describe --bootstrap-server odfhost1:9092 --topic streams-output
kafka-topics --delete --bootstrap-server odfhost1:9092 --topic streams-output
kafka-topics --create --bootstrap-server odfhost1:9092 --replication-factor 1 --partitions 1 --topic streams-output                               		 

## get image version
$ kubectl describe deployment -n spacename  componentname | grep -i image

## initial infra
minikube start --kubernetes-version=1.21.0 --embed-certs --cpus 2 --memory 16384
kubectl create ns cgf
kubectl config set-context --current --namespace=cgf

## Debug in Ansible env:
#Edit the conf file
sudo vi /etc/systemd/system/confluent-kafka-connect.service.d/override.conf
 
 
[Service]
LimitNOFILE=100000
Environment="KAFKA_OPTS=-Dlog4j.configuration=file:/etc/kafka/connect_distributed_log4j.properties"
Environment="KAFKA_HEAP_OPTS=-Xms256M -Xmx2G -agentlib:jdwp=transport=dt_socket,server=y,address=8000,suspend=n"


### For K8s
1.
kubectl create configmap debug-port --from-literal=debug=-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5555
2.
strimzi-kafkaconnect:
  externalConfiguration:
    env:
      - name: JAVA_TOOL_OPTIONS
        valueFrom:
          configMapKeyRef:
            name: debug-port
            key: debug
3.
kubectl get svc <kafkaconnect-service-name> -n cgf -o=jsonpath='{.metadata.ownerReferences[0].uid}'
==
kubectl get svc odf-connect-cluster-connect  -n cgf -o=jsonpath='{.metadata.ownerReferences[0].uid}'
kubectl get svc odf-connect-cluster-connect-api  -n cgf -o=jsonpath='{.metadata.ownerReferences[0].uid}'

=>b2a729d2-2ffd-41cf-a1d8-ef048e36062b
4.

### logs for kafka:
sudo journalctl -f -u confluent-kafka

### logs for zookeeper:
sudo journalctl -f -u confluent-zookeeper

### logs for Schema Register
sudo journalctl -f -u confluent-schema-registry

### get describe from schema
curl -X GET http://localhost:8081/subjects/schema/versions/

#### topic-distributor
curl -X GET http://localhost:8081/subjects/topic-distributor-value/versions/latest

### delete the schema
curl -X DELETE http://localhost:8081/subjects/topic-distributor-value
