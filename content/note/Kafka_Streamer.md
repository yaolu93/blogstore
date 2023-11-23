```bash

sudo systemctl status confluent*
sudo systemctl restart confluent-kafka-connect
sudo systemctl restart confluent-kafka
sudo systemctl restart confluent-zookeeper
sudo systemctl restart confluent-schema-registry
sudo systemctl restart odf-streamer

$  sudo vim odf-streamer.service.d/override.conf
// add debugger to streamer / ansible
sudo nano /home/openet/streamer/bin/StartService.sh
DEBUG_PORT=5555
DEBUG_SUSPEND=y
save the file and restart the service

### kafka streams headers 不能在input topic 之中读取

## streamer logs
sudo tail  /tmp/tests.log -n 10000 -f
sudo journalctl -u odf-streamer.service -n 10000 -f
## Or See Logs
sudo tail -f /var/log/odf/odf-streamer.log


### To print the record headers
docker run -it --network=host --rm         edenhill/kcat:1.7.0            -b odfhost1:9092        -s value=avro -r http://localhost:8081            -C            -f '\nKey (%K bytes): %k\t\nValue (%S bytes): %s\n\Partition: %p\tOffset: %o\nHeaders: %h\n--\n'            -t streams-output
