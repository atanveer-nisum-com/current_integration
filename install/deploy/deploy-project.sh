#!/bin/bash

# This script will be used to deploy micro services using docker-compose

export buildPath=/home/appdeployment/scripts/build

if [[ -f ${buildPath}/build.properties ]]; then
  source ${buildPath}/build.properties
else
  echo "[ERROR] - Build property file not found.. Exiting."
  exit 1
fi
  
# Fail if $deployPath doesn't exist
if ! cd $deployPath; then
  echo "ERROR: Deploy path doesn't exist. Exiting."
  exit
fi

# Copy recently packaged jar files to appropriate app deployment directory
echo "Copying jar files to deployment directory.."
cp -v -f $appFccJarFile ${deployPath}/app-fcc/app-fcc.jar
cp -v -f $appPromotionJarFile ${deployPath}/app-promotion/app-promotion.jar
cp -v -f $appShopJarFile ${deployPath}/app-shop/app-shop.jar
cp -v -f $appUserJarFile ${deployPath}/app-user/app-user.jar

# Copy nginx configuration file.
echo
echo "Copying nginx configuration file.."
cp -v -f ${projectDirectory}/nginx/nginx.conf ${deployPath}/nginx/nginx.conf

# Copy cassandra cql files.
echo
echo "Copying Cassandra cql files into init.cql"
rm -f ${deployPath}/cassandra/init.cql
echo "CREATE KEYSPACE ecommerce_app WITH REPLICATION = 
{'class': 'SimpleStrategy', 'replication_factor': 1};" > ${deployPath}/cassandra/init.cql
echo 'USE ecommerce_app;' >> ${deployPath}/cassandra/init.cql

for file in ${projectDirectory}/app-server/app-fcc/src/main/resources/db/cassandra/*.cql; do
  cat $file >> ${deployPath}/cassandra/init.cql
done


# Remove old app-backbone directory and copy new directory from projectDirectory
echo
echo "Removing old app-backbone/app directory.."
if [[ -d ${deployPath}/app-backbone/app ]]; then
  rm -rf ${deployPath}/app-backbone/app
fi

echo
echo "Copying ${projectDirectory}/app-backbone to ${deployPath}/app-backbone/app"
cp -r ${projectDirectory}/app-backbone ${deployPath}/app-backbone/app

# Building docker images now
docker-compose --project-name eta --file $deployPath/docker-compose.yml build 

# Fail is docker-compose build process fails
if [[ $? -ne 0 ]]; then
  echo "docker-compose build command failed.. Exiting."
  exit 1
fi

####################################
# STOPPING DOCKER COMPOSE STACK
####################################

docker-compose --project-name eta --file $deployPath/docker-compose.yml down

####################################
# STARTING DOCKER COMPOSE STACK NOW
####################################

if docker-compose --project-name eta --file $deployPath/docker-compose.yml up -d; then

  echo "All containers started.. Please check logs of containers to make sure they are running."

else

  echo "Failed to start containers. Exiting!"

fi
