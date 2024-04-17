# K8s

kgetpods -n kafka-external -w

kubectl get events -w
k get events -w -n kafka-external
k get pods -n monitoring -w
k logs -f monitoring-metrics-beat-metricbeat-665485c685-tcz72 -n monitoring

watch -n 1 helm list
watch -n 10 helm list -n kafka-external
watch -n 1 kubectl get pods -n cgf

minikube start --embed-certs --cpus 2 --memory 8192 --kubernetes-version=${K8S_VERSION}
minikube start --embed-certs --cpus 2 --memory 8192 --kubernetes-version=v1.25.0
minikube start --kubernetes-version=1.21.0 --embed-certs --cpus 2 --memory 8192

### get the password, userid is elastic
kubectl -n monitoring get secret monitoring-elasticsearch-es-elastic-user -o=jsonpath='{.data.elastic}' | base64 --decode; echo
### do port forwarding on your dev-vm
kubectl port-forward --address 0.0.0.0 svc/gateway-proxy -n  monitoring 30090:80

# Docker
Docker Edu
docker pull debian:bullseye
docker run  -ti <IMAGE ID > /bin/bash
usermod -aG sudo yaolu
su - yaolu
docker commit fd77c00ede1d yaolu/debian:test01

docker login
lytreetree@gmail.com
Ly19931223

docker tag <EXISTING_IMAGE> <PRIVATE_REPO>/<NEW_IMAGE_NAME>:<TAG>
docker tag yaolu/debian:test01 achillesly/debian:test01

docker push achillesly/debian:test01
docker pull achillesly/debian:test01

#### docker cp:
docker cp kafka-connect:/tmp/heapdump-1713352230746.hprof /tmp/test

## Emace inside Docker
**https://rebeja.eu/posts/python-ide-with-emacs-and-docker/**
**[docker_emacs_gui]:https://dev.to/acro5piano/we-can-virtualize-even-gui-text-editor-with-docker-container--5bhh**
# Emacs
### Download and install
wget https://mirror.lyrahosting.com/gnu/emacs/emacs-29.3.tar.gz
 
tar -xzf emacs-29.3.tar.gz
sudo apt update
sudo apt install build-essential autoconf automake texinfo libjpeg-dev libpng-dev libgif-dev libtiff-dev libx11-dev libncurses-dev
apt install libgnutls28-dev
sudo apt install libxpm-dev
sudo apt install libgnutls28-dev
./autogen.sh
./configure
make
make install
