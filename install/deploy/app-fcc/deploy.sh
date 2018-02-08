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

  # Copy recently packaged jar file to appropriate app deployment directory
  echo "[INFO] - Copying jar file to deployment directory.."
  cp -v -f $appFccJarFile ${deployPath}/app-fcc/app-fcc.jar

  # Building docker image now
  docker-compose --project-name eta --file $deployPath/docker-compose.yml build appfcc

  # If docker-compose build process fails
  if [[ $? -ne 0 ]]; then
    echo "[ERROR] - docker-compose build command failed.. Exiting."
    exit 1
  fi

fi

##############################################
# STOPPING AND REMOVING APP-FCC CONTAINER
##############################################

docker stop appfcc
docker rm appfcc

####################################
# STARTING APP-FCC CONTAINER
####################################

if docker-compose --project-name eta --file $deployPath/docker-compose.yml up -d appfcc; then

  echo "[INFO] - Container started.. Please check logs to make sure it's working."

else

  echo "[ERROR] - Failed to start container. Exiting!"

fi


