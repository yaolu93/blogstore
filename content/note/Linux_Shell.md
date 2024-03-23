```bash
sudo update-alternatives --config javac
sudo update-alternatives --config java

mvn clean install -DskipTests
mvn clean install -DskipTests

ssh-keygen -t rsa -b 4096 -C "yao.lu@openet.com"


----
理解 shell 的父子关系

默认为 command prompt

我们需要转换那 git 的用户名

yaolu
Ly19931223.

## Git Stage

>git stash save 'message...'可以添加一些注释

>stash_id是通过git stash list命令得到的

>git stash pop stash@{1}恢复指定的进度到工作区
## Git fetch

docker rm -f $(docker ps -aq)
docker volume ls -q | xargs docker volume rm
docker volume rm $(docker volume ls -q)


### top
top E 1

###Deploy the component
#!/usr/bin/bash

set -eux

example () {
  echo -e "Example:"
  echo -e "\t$0\n"
}

usage () {
  echo -e "Usage: $0"
  echo -e "Package and deploy connector project to local kafka connect\n"
  example
  echo -e "Operation:"
  echo -e "\t-h, --help   Prints this help"
}

error () {
  echo >&2 "deploy-connector:" "$@"
  echo "Try '$0 --help' or '$0 -h' for more information"
  exit 1
}

readPomTag () {
  local depth=0

  while IFS= read -r line; do

    # Disregard top xml tag and comment tags
    if [[ $line =~ \<?xml[[:blank:]].*\> || $line =~ \<!--.*--\> ]]; then
      continue
    fi

    # Extract the value of the tag trimmed
    if [[ $line =~ \<$2\> ]] && ((depth == 1)); then
      sed -n "s/<$2>\(.*\)<\/$2>/\1/p" <<< "$line" | xargs echo
      return
    fi

    # Set the xml tag depth
    if [[ ! $line =~ \<.+\>.*\<.+\> ]]; then
      if [[ $line =~ \<\/.+\> ]]; then
        depth=$((depth-1))
      elif [[ $line =~ \<.+\> ]]; then
        depth=$((depth+1))
      fi
    fi
    #echo "[$depth] $line"

  done < "$1"
}

stop_service () {
  sudo systemctl stop "$system_service"
}

start_service () {
  sudo systemctl start "$system_service"
}

package () {
  mvn clean package -DskipTests
}

prepare () {
  sudo rm -rf "$connectors_dir/kafka-connector-sink"
}

deploy () {
  sudo tar -C "$connectors_dir" --strip-components=4 \
  -xzvf target/"$artifactId"-"$version"-bin.tar.gz \
  "$artifactId"-"$version"/usr/share/kafka-connect/kafka-connector-sink
}

check_file () {
  if [[ ! -f "$1" ]]; then
    error "File $1 could not be found in the current directory"
  fi
}

# Show help
if [[ $# -gt 0 ]]; then
  if [[ $1 == "-h" || $1 == "--help" ]]; then
    usage; exit
  else
    error "$0 does not support any argument"
  fi
fi

## Execute script
pom='pom.xml'
connectors_dir='/usr/share/java'
system_service='confluent-kafka-connect'

# Preconditions
check_file $pom
artifactId=$(readPomTag $pom 'artifactId')
version=$(readPomTag $pom 'version')

## Actions
set -x
stop_service
package
prepare
deploy
start_service

# Result
cat <<EOF

------------------------------------------------------------------------
 DEPLOY CONNECTOR SUCCESS
------------------------------------------------------------------------
EOF



### jdtls
./mvnw clean verify -DskipTests=true
