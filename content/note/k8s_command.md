```bash

kubectl get events -w

minikube start --embed-certs --cpus 4 --memory 8192 --kubernetes-version=${K8S_VERSION}
