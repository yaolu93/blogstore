```bash

sudo systemctl status confluent*
sudo systemctl restart confluent-kafka-connect
sudo systemctl restart confluent-kafka
sudo systemctl restart confluent-zookeeper
sudo systemctl restart confluent-schema-registry
sudo systemctl restart odf-streamer

sudo systemctl stop odf-streamer
sudo systemctl start odf-streamer


kubectl exec -ti [streamer-pod] -- bash -c "ls -al bundles/app-bundles/streamer"

$  sudo vim odf-streamer.service.d/override.conf
// add debugger to streamer / ansible
sudo nano /home/openet/streamer/bin/StartService.sh
DEBUG_PORT=5555
DEBUG_SUSPEND=y
save the file and restart the service

### How to debugger in Docker
---
version: '2'
services:
  streams:
    image: openet/ngm_streamer:0.1.0
    build:
      context: .
      dockerfile: Dockerfile-local
    hostname: streams
    container_name: streams
    ports:
      - '6666:6666'
      - '6668:6668'
#      - '5555:5555'
    environment:
      - SBA_JMX_PORT=6668
#      - DEBUG_PORT=5555
#      - DEBUG_SUSPEND=y
networks:
  default:
    external:
      name: kafka_default

### How to debugger in minikube
replicaCount: 1
fullnameOverride: ngmstreamertimewindow-streamer-helm
image:
  pullPolicy: Always
env:
  - name: DEBUG_PORT
    value: "5566"
  - name: DEBUG_SUSPEND
    value: "y"  

### kafka streams headers 不能在input topic 之中读取

## streamer logs
sudo tail  /tmp/tests.log -n 10000 -f
sudo journalctl -u odf-streamer.service -n 10000 -f
## Or See Logs
sudo tail -f /var/log/odf/odf-streamer.log


### To print the record headers
docker run -it --network=host --rm         edenhill/kcat:1.7.0            -b odfhost1:9092        -s value=avro -r http://localhost:8081            -C            -f '\nKey (%K bytes): %k\t\nValue (%S bytes): %s\n\Partition: %p\tOffset: %o\nHeaders: %h\n--\n'            -t streams-output



#!/bin/bash

cd ~/workspace/ngm-mod-streamer
mvn clean install -DskipTests

VERSION=2.12.17-yao-SNAPSHOT
IP="odfhost1"
USER=yaolu
FILE=streamer-package-$VERSION-bundles.tar.gz
DIR=/home/yaolu/workspace/ngm-mod-streamer/package/target
scp $DIR/$FILE $USER@$IP:/tmp/

sleep 2

NAME=streamer-package-2.12.17-yao-SNAPSHOT
TARFILE=/tmp/$NAME-bundles.tar.gz
OUTPATH=/tmp/$NAME
STREAMER_PATH=/home/openet/streamer
## update streamer jars
sudo systemctl stop odf-streamer
sudo rm -rf $OUTPATH
mkdir -p $OUTPATH
tar -xvf $TARFILE -C $OUTPATH
sudo rm -rf $STREAMER_PATH/bundles/app-bundles/
sudo mkdir -p $STREAMER_PATH/bundles/app-bundles/
echo "copying app-bundles"
sudo cp -r $OUTPATH/opt/deploy/SBA/bundles/app-bundles/* $STREAMER_PATH/bundles/app-bundles/
sudo chown -R openet:openet $STREAMER_PATH/bundles/app-bundles/
sudo cp -r $OUTPATH/opt/deploy/SBA/lib/* $STREAMER_PATH/lib/
sudo chown -R openet:openet $STREAMER_PATH/lib/
# remove old third party bundles
# sudo rm $STREAMER_PATH/bundles/sba-core/org.apache.servicemix.bundles.avro*
# sudo rm $STREAMER_PATH/bundles/sba-core/org.apache.servicemix.bundles.common*
# sudo rm $STREAMER_PATH/bundles/sba-core/org.apache.servicemix.bundles.kafka*
# sudo rm $STREAMER_PATH/bundles/sba-core/org.apache.servicemix.bundles.reflections*
# update third party bundles
sudo cp -r $OUTPATH/opt/deploy/SBA/bundles/sba-core/* $STREAMER_PATH/bundles/sba-core/

# ## update streamer.yaml file
#echo "updating streamer file"
#STREAMER_FILE=./ansible/config/streamer.yaml
#OUTPUT_SCHEMA=./ansible/config/output.avsc
#sudo cp $STREAMER_FILE /home/openet/streamer/config/deployment-config/streamer.yaml
#sudo cp $OUTPUT_SCHEMA /home/openet/output.avsc

echo "Restarting streamer service"
sudo systemctl start odf-streamer
