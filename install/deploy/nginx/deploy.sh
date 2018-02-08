#!/bin/bash

# This script will individually build docker container of this component and deploy it using docker-compose

export buildPath=/home/appdeployment/scripts/build


function usage {
  echo USAGE:
  echo ==========================
  echo
  echo "$0 <options>"
  echo
  echo "--build : Build docker image before deploying it using docker-compose"
  echo "--help : Print usage document"
  echo
}

if echo $@ | grep '\-\-help' &>/dev/null; then
  usage
  exit 0
fi


# Getting all build properties ready
if [[ -f ${buildPath}/build.properties ]]; then
  source ${buildPath}/build.properties
else
  echo "[ERROR] - Build property file not found.. Exiting."
  exit 1
fi

if echo $@ | grep '\-\-build' &>/dev/null; then 

  sed -i -e 's;server_name  localhost;server_name  nginx;g' \
       -e 's;http://127.0.0.1:8082;http://'${appUserHost}':'${appUserPort}';g' \
       -e 's;http://127.0.0.1:8083;http://'${appShopHost}':'${appShopPort}';g' \
       -e 's;http://127.0.0.1:8081;http://'${appFccHost}':'${appFccPort}';g' \
       -e 's;http://127.0.0.1:8084;http://'${appPromotionHost}':'${appPromotionPort}';g' \
       -e 's;http://127.0.0.1:3000;http://'${appBackboneHost}':'${appBackbonePort}';g' ${projectDirectory}/nginx/nginx.conf

  # Copy nginx configuration file.
  echo
  echo "Copying nginx configuration file.."
  cp -v -f ${projectDirectory}/nginx/nginx.conf ${deployPath}/nginx/nginx.conf

  # Building docker image now
  docker-compose --file $deployPath/docker-compose.yml build nginx

  # If docker-compose build process fails
  if [[ $? -ne 0 ]]; then
    echo "[ERROR] - docker-compose build command failed.. Exiting."
    exit 1
  fi

fi

##############################################
# STOPPING AND REMOVING CONTAINER
##############################################

docker stop nginx
docker rm nginx

####################################
# STARTING CONTAINER
####################################

if docker-compose --file $deployPath/docker-compose.yml up -d nginx; then

  echo "[INFO] - Container started.. Please check logs to make sure it's working."

else

  echo "[ERROR] - Failed to start container. Exiting!"

fi


