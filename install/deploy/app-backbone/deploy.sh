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

  # Remove old app-backbone directory and copy new directory from projectDirectory
  echo
  echo "[INFO] - Removing old app-backbone/app directory.."
  if [[ -d ${deployPath}/app-backbone/app ]]; then
    rm -rf ${deployPath}/app-backbone/app
  fi

  # Replace appropriate nginx url in app-backbone configuration file
  echo
  echo "[INFO] - Modifying app-backbone configuration files.."
  sed -i "s,baseApi =.*$,baseApi = \"http://${nginxPubHost}/api/\";,g" ${projectDirectory}/app-backbone/public/js/core/common.js

  echo
  echo "[INFO] - Copying ${projectDirectory}/app-backbone to ${deployPath}/app-backbone/app"
  cp -r ${projectDirectory}/app-backbone ${deployPath}/app-backbone/app

  # Building docker image now
  docker-compose --project-name eta --file $deployPath/docker-compose.yml build appbackbone

  # Fail is docker-compose build process fails
  if [[ $? -ne 0 ]]; then
    echo "[ERROR] - docker-compose build command failed.. Exiting."
    exit 1
  fi

fi

##############################################
# STOPPING AND REMOVING APPBACKBONE CONTAINER
##############################################

docker stop appbackbone
docker rm appbackbone

####################################
# STARTING APPBACKBONE CONTAINER
####################################

if docker-compose --project-name eta --file $deployPath/docker-compose.yml up -d appbackbone; then

  echo "[INFO] - Container started.. Please check logs to make sure it's working."

else

  echo "[ERROR] - Failed to start container. Exiting!"

fi


