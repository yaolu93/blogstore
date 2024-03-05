```bash

kgetpods -n kafka-external -w


kubectl get events -w

k get pods -n monitoring -w

k logs -f monitoring-metrics-beat-metricbeat-665485c685-tcz72 -n monitoring

watch -n 1 helm list

watch -n 10 helm list -n kafka-external


watch -n 1 kubectl get pods -n cgf

minikube start --embed-certs --cpus 2 --memory 8192 --kubernetes-version=${K8S_VERSION}

minikube start --embed-certs --cpus 2 --memory 8192 --kubernetes-version=v1.25.0


minikube start --kubernetes-version=1.21.0 --embed-certs --cpus 2 --memory 8192

# get the password, userid is elastic
kubectl -n monitoring get secret monitoring-elasticsearch-es-elastic-user -o=jsonpath='{.data.elastic}' | base64 --decode; echo
# do port forwarding on your dev-vm
kubectl port-forward --address 0.0.0.0 svc/gateway-proxy -n  monitoring 30090:80

