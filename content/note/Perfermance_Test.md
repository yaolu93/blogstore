```

####################Should change the firefox proxy#####################

./perf_new/e2e_enrich_keyRange/run_test_e2e_enrich_keyRange_k8s_local.sh
10.15.32.90:30090/kibana/

minikube start --embed-certs
minikube start --kubernetes-version=1.21.0 --embed-certs --cpus 2 --memory 16384

kubectl create ns cgf
kubectl config set-context --current --namespace=cgf

cd ./acceptance_perf_soak/perf_new/scripts
./start_odf_test_container_k8s.sh
./copy_test_files.sh

kubectl apply -f ~/workspace/ngm-test-container/resources/k8s-pods/sftp-local.yaml 

sh ~/workspace/ngm-test-container/acceptance_perf_soak/perf_new/scripts/deploy_strimzi_operator.sh

## inside acceptance_perf_soak
./scripts/start_odf_test_container.sh

kubectl apply -f ~/workspace/ngm-test-container/common/k8s/odf-test.yaml 


kubectl apply -f ~/packages/ngm-test-container/common/k8s/odf-test.yaml 



kubectl delete -f ~/workspace/ngm-test-container/resources/k8s-pods/sftp.yaml 
kubectl apply -f ~/workspace/ngm-test-container/resources/k8s-pods/sftp.yaml 

kubectl apply -f ~/workspace/ngm-test-container/resources/k8s-pods/sftp-local.yaml 
kubectl delete -f ~/workspace/ngm-test-container/resources/k8s-pods/sftp-local.yaml 


./scripts/setup_sftp_datagen.sh

docker  exec -it odf_test /bin/bash -c "export ODF_TEST_ENV="k8s";pytest -s /app/acceptance_perf_soak/test_main_perf_collect_data.py::test_perf_collect_data_source"

 kubectl -n cgf exec -ti odf-cluster-kafka-0 -- ./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic streams-input --from-beginning

 kubectl -n cgf exec -ti odf-cluster-kafka-0 -- ./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic streams-output --from-beginning

 kubectl -n cgf exec -ti odf-cluster-kafka-0 -- ./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic streams-refOffers --from-beginning

 odf-cluster-kafka-0

kubectl exec -ti odf-sftp-0 --  bash

 /ngm-test-container/acceptance_perf_soak/scripts
 ./acceptance_perf_soak/scripts/run_test_enrich_strategy_key_k8s.sh

 kubectl exec -ti `kubectl  get pod -l pod/name=schema-registry-helm | awk '{print $1}' | tail -n 1` -n cgf -c schema-registry-helm -- bash -c "unset JMX_PORT ; kafka-avro-console-consumer --topic streams-input --bootstrap-server odf-cluster-kafka-bootstrap:9092 --from-beginning --property print.key=true --property print.timestamp=true --property print.schema.ids=true --property print.headers=true --key-deserializer=org.apache.kafka.common.serialization.StringDeserializer"

kubectl exec -ti `kubectl  get pod -l pod/name=schema-registry-helm | awk '{print $1}' | tail -n 1` -n cgf -c schema-registry-helm -- bash -c "unset JMX_PORT ; kafka-avro-console-consumer --topic streams-output --bootstrap-server odf-cluster-kafka-bootstrap:9092 --from-beginning --property print.key=true --property print.timestamp=true --property print.schema.ids=true --property print.headers=true --key-deserializer=org.apache.kafka.common.serialization.StringDeserializer"


kubectl exec -ti `kubectl  get pod -l pod/name=schema-registry-helm | awk '{print $1}' | tail -n 1` -n cgf -c schema-registry-helm -- bash -c "unset JMX_PORT ; kafka-avro-console-consumer --topic streams-error --bootstrap-server odf-cluster-kafka-bootstrap:9092 --from-beginning --property print.key=true --property print.timestamp=true --property print.schema.ids=true --property print.headers=true --key-deserializer=org.apache.kafka.common.serialization.StringDeserializer"

kubectl exec -ti `kubectl  get pod -l pod/name=schema-registry-helm | awk '{print $1}' | tail -n 1` -n cgf -c schema-registry-helm -- bash -c "unset JMX_PORT ; kafka-avro-console-consumer --topic streams-input-enrich --bootstrap-server odf-cluster-kafka-bootstrap:9092 --from-beginning --property print.key=true --property print.timestamp=true --property print.schema.ids=true --property print.headers=true --key-deserializer=org.apache.kafka.common.serialization.StringDeserializer"


export ODF_TEST_ENV="k8s";pytest -s /app/acceptance_perf_soak/test_main_perf_collect_data.py::test_perf_collect_data_streamer

kubectl port-forward --address 0.0.0.0 svc/gateway-proxy -n  monitoring 30090:80


helm list

kubectl get pod

kubectl get pod -o yaml

kubectl describe pod <pod>

kubectl logs <pod>


kubectl get kctr (connector)
kubectl get kctr <connector> -o yaml

kubectl config set-context --current --namespace=cgf


kubectl cp ../test_lib/ cgf/odf-test:/app/
kubectl cp ../acceptance_perf_soak/ cgf/odf-test:/app/
docker cp ../acceptance_perf_soak/ odf_test:/app/
docker cp ../test_lib odf_test:/app/

kubectl describe pod strimzi-cluster-operator-56bcf69b8b-22hvc
kubectl logs strimzi-cluster-operator-56bcf69b8b-22hvc

alias kgetpods='kubectl get pods'

## for example  kcuc dev-lab-odf-2   ||  kcuc dev-lab-odf-1

alias kcuc='kubectl config use-context'
alias kcsc='kubectl config set-context'
alias kcdc='kubectl config delete-context'
alias kccc='kubectl config current-context'

# List all contexts
alias kcgc='kubectl config get-contexts'
alias kcn='kubectl config set-context --current --namespace'


set -ex
docker exec odf_test mkdir -p /tmp/shared
docker cp ../../test_lib/ odf_test:/app/
docker cp ../../acceptance_perf_soak/ odf_test:/app/
# Create test result directory
[ ! -d /tmp/perf_result ] && mkdir /tmp/perf_result
# Executing the Actual Test
docker exec -t odf_test /bin/bash -lc 'nohup python3 -u -m pytest -s /app/acceptance_perf_soak/test_main_perf.py::test_perf_enrich_strategy_key | tee test_perf_enrich_strategy_key.log'
# Saves the test logs and results in host machine
docker exec -it odf_test bash -c 'ls -1 *.log' | tr -d '\r' |  xargs -n1 -i docker cp odf_test:/app/{} /tmp/perf_result/{}
docker exec -it odf_test bash -c '(cd /tmp && ls -1 *.csv)' | tr -d '\r' |  xargs -n1 -i docker cp odf_test:/tmp/{} /tmp/perf_result/{}
set +x

To elaborate the issue:

I am running a perf test ngm-test-container/acceptance_perf_soak/perf/e2e_enrich_key. For which I had updated the test script ngm-test-container/acceptance_perf_soak/scripts/run_test_enrich_strategy_key_k8s.sh with below updates:



Text
set -ex
docker exec odf-test mkdir -p /tmp/shared
docker cp ../test_lib/ odf-test:/app/
docker cp ../acceptance_perf_soak/ odf-test:/app/
# Create test result directory
[ ! -d /tmp/perf_result ] && mkdir /tmp/perf_result
# Executing the Actual Test
docker exec -t odf-test /bin/bash -lc 'nohup python3 -u -m pytest -s /app/acceptance_perf_soak/test_main_perf.py::test_perf_enrich_strategy_key | tee test_perf_enrich_strategy_key.log'
# Saves the test logs and results in host machine
docker exec -it odf-test bash -c 'ls -1 *.log' | tr -d '\r' |  xargs -n1 -i docker cp odf-test:/app/{} /tmp/perf_result/{}
docker exec -it odf-test bash -c '(cd /tmp && ls -1 *.csv)' | tr -d '\r' |  xargs -n1 -i docker cp odf-test:/tmp/{} /tmp/perf_result/{}
set +x


2. And before I ran the script, I executed script 'ngm-test-container/resources/scripts/minikube/install_odf_minikube_perf_assembly.sh' And then I get sftp, odf-test and strimzi-cluster pod up. In the script, I set the variable minikube as true since I am running the test on local.



3. And then to get the pods kafka, zookeeper, SR, streamer etc. up, I executed:

helm upgrade -i -n cgf odf-assembly-test --version=2.8.2-SNAPSHOT openet-helm/odf-assembly -f ./perf/e2e_enrich_key/assembly-values-local.yaml



4. In assembly-value-local.yaml file, I have given tag for odf-streamer as 2.9.0-SNAPSHOT (2.9.1-SNAPSHOT also), strimizi connect package as 2.9.0-SNAPSHOT and all pods, gave enabled: true.



5. In the test file (test_config_perf_e2e_enrich_key.yaml), I provided chart version as 2.8.2-SNAPSHOT for odf-assembly-test.



But still, I am not getting the streamer pod up. Please help if I am still missing something.


docker exec -ti odf_test /bin/bash -c "export ODF_TEST_ENV="k8s";pytest -s /app/acceptance_perf_soak/test_main_perf_collect_data.py::test_perf_collect_data_streamer"
```
