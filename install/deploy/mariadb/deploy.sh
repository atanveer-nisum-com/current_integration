#!/bin/bash

# This script will individually build docker container of this component and deploy it using docker-compose

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
if [[ -f ../../build/build.properties ]]; then
  source ../../build/build.properties
else
  echo "[ERROR] - Build property file not found.. Exiting."
  exit 1
fi

if echo $@ | grep '\-\-build' &>/dev/null; then 

  # Building docker image now
  docker-compose --project-name eta --file $deployPath/docker-compose.yml build mariadb

  # If docker-compose build process fails
  if [[ $? -ne 0 ]]; then
    echo "[ERROR] - docker-compose build command failed.. Exiting."
    exit 1
  fi

fi

##############################################
# STOPPING AND REMOVING CONTAINER
##############################################

docker stop mariadb
docker rm mariadb

####################################
# STARTING CONTAINER
####################################

if docker-compose --project-name eta --file $deployPath/docker-compose.yml up -d mariadb; then

  echo "[INFO] - Container started.. Please check logs to make sure it's working."

else

  echo "[ERROR] - Failed to start container. Exiting!"

fi


