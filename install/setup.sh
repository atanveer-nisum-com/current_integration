#!/bin/bash

# This file will be used to prepare all the dependencies required to build and deploy ecommerce training application.

serviceAccount="appdeployment"

# Confirm if this script should also build and deploy project
if [[ "$(echo $1 | tr A-Z a-z)" == "build" ]]; then
  echo "[INFO] - $0 will trigger build after successful completion."
  build=true
fi

if [[ "$(echo $2 | tr A-Z a-z)" == "deploy" ]]; then
  echo "[INFO] - $0 will trigger deployment after successful completion."
  deploy=true
fi

## Make sure this script is running as root user.
if [[ "$(whoami)" != "root" ]]; then
  echo "[ERROR] - Script not running as root. Please execute this script as root user."
  exit 1
fi  

## Create $serviceAccount user for build and deployment purposes
useradd $serviceAccount

## Make sure root account has private key in its .ssh directory
if [[ -f /root/.ssh/id_rsa ]]; then
  echo "[INFO] - private key found.. Assuming this key has access to github.."
  echo "[INFO] - Copying private key to /home/${serviceAccount}/.ssh/id_rsa"
  mkdir -p /home/${serviceAccount}/.ssh/
  cp -v -f /root/.ssh/id_rsa /home/${serviceAccount}/.ssh/id_rsa
  chown -R ${serviceAccount}:${serviceAccount} /home/${serviceAccount}/.ssh/
  chmod -R 700 /home/${serviceAccount}/.ssh/id_rsa
else
  echo "[ERROR] - Private key not found in /root/.ssh/ directory. Setup will exit."
  echo "[ERROR] - Please make sure to create private key and provide this key access to your github account."
  exit 1
fi

## Install Docker CE
yum install -y yum-utils device-mapper-persistent-data lvm2
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
yum install -y docker-ce

## Add service account to docker group
usermod -a -G docker $serviceAccount

## Install docker-compose
yum install -y epel-release
yum install -y python-pip
pip install docker-compose

## Install and configure JDK 1.8
mkdir -p /opt/tools
if cd /opt/tools; then
  wget --no-check-certificate -c --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u151-b12/e758a0de34e24606bca991d704f6dcbf/jdk-8u151-linux-x64.tar.gz
  tar -xzf jdk-8u151-linux-x64.tar.gz
  ln -s /opt/tools/jdk1.8.0_151 /opt/tools/java
  echo 'JAVA_HOME=/opt/tools/java' >> /home/${serviceAccount}/.bash_profile
  echo 'PATH=$PATH:$JAVA_HOME/bin:$HOME/.local/bin:$HOME/bin' >> /home/${serviceAccount}/.bash_profile
fi

chown -R ${serviceAccount}:${serviceAccount} /opt/tools

## Install maven
yum install -y maven

## Generate ssh keys for service account
#sudo -u $serviceAccount -i echo -e "\n\n\n" | ssh-keygen -t rsa

## Install git and dos2unix
yum install -y git dos2unix

## Clone github repository 
sudo -H -i -u $serviceAccount bash -c "mkdir -p /home/${serviceAccount}/repo; \
                                       mkdir -p /home/${serviceAccount}/scripts; \
                                       cd /home/${serviceAccount}/repo; \
                                       git clone git@github.com:nisum-inc/training-ecommerce-project.git; \
                                       cd training-ecommerce-project && git checkout sprint-15; git reset --hard; git pull; \
                                       cp -v -r /home/${serviceAccount}/repo/training-ecommerce-project/install/build /home/${serviceAccount}/scripts/; \
                                       cp -v -r /home/${serviceAccount}/repo/training-ecommerce-project/install/deploy /home/${serviceAccount}/scripts/;"

## copy jdk to deployment directory
cp -r /opt/tools/jdk1.8.0_151/ /home/${serviceAccount}/scripts/deploy/common/

## Enable and start docker daemon
echo "[INFO] - Enabling and starting docker service.."
systemctl enable docker
systemctl start docker

echo "[INFO] - All pre-requisites have been installed successfully."
echo

if [[ "$build" == "true" ]]; then
  echo "[INFO] - Trigging build now.."
  echo
  sudo -H -i -u $serviceAccount bash -c "cd /home/${serviceAccount}/scripts/build/; \
                                         ./build-project.sh app-fcc app-promotion app-shop app-user;"
  if [[ $? -eq 0 ]]; then
    echo "[INFO] - Build completed successfully."
  else
    echo "[ERROR] - Build failed.. Exiting."
  fi
fi 

if [[ "$deploy" == "true" ]]; then
  echo "[INFO] - Trigging deployment now.."
  echo
  sudo -H -i -u $serviceAccount bash -c "cd /home/${serviceAccount}/scripts/deploy/; \
                                         ./deploy-project.sh;"
  if [[ $? -eq 0 ]]; then
    echo "[INFO] - Deployment completed successfully."
  else
    echo "[ERROR] - Deployment failed.. Exiting."
  fi
fi
