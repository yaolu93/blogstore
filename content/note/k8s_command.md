```bash

kubectl get events -w

minikube start --embed-certs --cpus 2 --memory 8192 --kubernetes-version=${K8S_VERSION}

minikube start --embed-certs --cpus 2 --memory 8192 --kubernetes-version=v1.25.0


minikube start --kubernetes-version=1.21.0 --embed-certs --cpus 2 --memory 8192
