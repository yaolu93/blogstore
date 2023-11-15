```bash

sudo systemctl status confluent*
sudo systemctl restart confluent-kafka-connect
sudo systemctl restart confluent-kafka
sudo systemctl restart confluent-zookeeper
sudo systemctl restart confluent-schema-registry
sudo systemctl restart odf-streamer


## streamer logs
sudo journalctl -u odf-streamer.service -n 1000 -f
## Or See Logs
sudo tail -f /var/log/odf/odf-streamer.log
