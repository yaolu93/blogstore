```bash

kafka-topics --list --bootstrap-server odfhost1:9092
kafka-topics --describe --bootstrap-server odfhost1:9092 --topic streams-input
kafka-topics --delete --bootstrap-server odfhost1:9092 --topic streams-input
kafka-topics --create --bootstrap-server odfhost1:9092 --replication-factor 1 --partitions 1 --topic streams-input       
sudo kafka-avro-console-consumer --from-beginning --bootstrap-server odfhost1:9092 --topic streams-input --property print.key=true --key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
sudo kafka-avro-console-consumer --from-beginning --bootstrap-server odfhost1:9092 --topic streams-output --property print.key=true --key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
sudo kafka-avro-console-consumer --from-beginning --bootstrap-server odfhost1:9092 --topic streams-duplicate --property print.key=true --key-deserializer=org.apache.kafka.common.serialization.StringDeserializer


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
