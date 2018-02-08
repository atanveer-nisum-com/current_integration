#!/bin/bash

# Exit if no component name is defined
if [[ $# -lt 1 ]]; then
  echo "Usage: $0 <component-name>"
  echo "ERROR: Component name not provided"
  exit 5
fi 


export packages="$@"

source build.properties

# create a function that will build package through maven
function buildPackage {

  echo "Building package: $1"
  cd ${packageDirectory}/${1}
  mvn clean package -DskipTests || echo "Error packaging $1 .. Exiting." 

} 

# Fail if project directory doesn't exist. You should clone github repository at $projectDirectory path before executing this script
if ! cd $projectDirectory; then

  echo "Project directory doesn't exist.. Exiting."
  exit 1

fi

# This will discard all local changes inside repository directory
echo "Discarding local changes in repository directory.."
git reset --hard

# Pulling latest changes from github repository
echo "Pulling repository changes from GitHub.."
git pull || echo "Error pulling training-ecommerce-project repository."

# Switch to required branch before moving on..
echo "Checking out $branchName branch.."
git checkout $branchName



# Process all property files before packaging

# Replace app-common parameters in conf file
echo
echo "Processing app-common property file.."
sed -i -e "s;shop.url =.*$;shop.url = ${appShopUrl};g" \
       -e "s;fcc.url =.*$;fcc.url = ${appFccUrl};g" \
       -e "s;APP_PROMOTION_BASE_URL =.*$;APP_PROMOTION_BASE_URL = ${APP_PROMOTION_BASE_URL};g" \
       -e "s;APP_USER_BASE_URL =.*$;APP_USER_BASE_URL = ${APP_USER_BASE_URL};g" $appCommonPropertyFile

# Replace app-fcc parameters in conf file only if we need to build app-fcc
if echo $packages | grep "app-fcc" &>/dev/null; then

echo
echo "Processing app-fcc property file.."
sed -i -e "s;spring.data.cassandra.contact-points=.*$;spring.data.cassandra.contact-points=${cassandraHost};g" \
       -e "s;spring.data.cassandra.port=.*$;spring.data.cassandra.port=${cassandraPort};g" \
       -e "s;spring.data.cassandra.keyspace-name=.*$;spring.data.cassandra.keyspace-name=${cassandraDb};g" $appFccPropertyFile

fi

# Replace app-promotion parameters in conf file only if we need to build app-promotion
if echo $packages | grep "app-promotion" &>/dev/null; then

echo
echo "Processing app-promotion pom.xml file.."
sed -i -e "s;<db.host>.*$;<db.host>${mariaDbHost}</db.host>;g" \
       -e "s;<db.port>.*$;<db.port>${mariaDbPort}</db.port>;g" \
       -e "s;<db.username>.*$;<db.username>${mariaDbUser_appPromotion}</db.username>;g" \
       -e "s;<db.password>.*$;<db.password>${mariaDbPass_appPromotion}</db.password>;g" $appPromotionPropertyFile

fi

# Replace app-shop parameters in conf file only if we need to build app-shop
if echo $packages | grep "app-shop" &>/dev/null; then

echo
echo "Processing app-shop pom.xml file.."
sed -i -e "s;<db.host>.*$;<db.host>${mariaDbHost}</db.host>;g" \
       -e "s;<db.port>.*$;<db.port>${mariaDbPort}</db.port>;g" \
       -e "s;<db.username>.*$;<db.username>${mariaDbUser_appShop}</db.username>;g" \
       -e "s;<db.password>.*$;<db.password>${mariaDbPass_appShop}</db.password>;g" $appShopPropertyFile

fi

# Replace app-user parameers in conf file only if we need to build app-user
if echo $packages | grep "app-user" &>/dev/null; then

echo "Processing app-user pom.xml file.."
sed -i -e "s;<db.host>.*$;<db.host>${mariaDbHost}</db.host>;g" \
       -e "s;<db.port>.*$;<db.port>${mariaDbPort}</db.port>;g" \
       -e "s;<db.username>.*$;<db.username>${mariaDbUser_appUser}</db.username>;g" \
       -e "s;<db.password>.*$;<db.password>${mariaDbPass_appUser}</db.password>;g" $appUserPropertyFile

fi


# Running dos2unix on DB migration sql scripts just in case.
for file in ${appShopDbMigrationPath}/*; do
  dos2unix $file
done

for file in ${appUserDbMigrationPath}/*; do
  dos2unix $file
done

# Build app-common package before any other component
echo "Building package: app-common"
cd ${packageDirectory}/app-common
mvn clean install -DskipTests || echo "Error packaging app-common .. Exiting."

# Iterate package building process in a loop for all provided components
for appComponent in $packages; do

  buildPackage $appComponent

done

echo
# List all packaged jar files
echo
echo "All components are packaged.."
echo
ls -lh $appCommonJarFile $appFccJarFile $appPromotionJarFile $appShopJarFile $appUserJarFile

echo
echo "Copying app-common jar file to deploy path.."
cp -f -v $appCommonJarFile ${deployPath}/common/

# Replace nginx configuration parameters according to our naming convention written in docker-compose
echo
echo "Modifying nginx config file with appropriate properties.."

sed -i -e 's;server_name  localhost;server_name  nginx;g' \
       -e 's;http://127.0.0.1:8082;http://'${appUserHost}':'${appUserPort}';g' \
       -e 's;http://127.0.0.1:8083;http://'${appShopHost}':'${appShopPort}';g' \
       -e 's;http://127.0.0.1:8081;http://'${appFccHost}':'${appFccPort}';g' \
       -e 's;http://127.0.0.1:8084;http://'${appPromotionHost}':'${appPromotionPort}';g' \
       -e 's;http://127.0.0.1:3000;http://'${appBackboneHost}':'${appBackbonePort}';g' ${projectDirectory}/nginx/nginx.conf

# Replace appropriate nginx url in app-backbone configuration file
echo
echo "Modifying app-backbone configuration files.."
sed -i "s,baseApi =.*$,baseApi = \"http://${nginxPubHost}/api/\";,g" ${projectDirectory}/app-backbone/public/js/core/common.js

# Modify docker-compose.yml file to point to correct build context
echo
echo "[INFO] - Modifying docker-compose.yml file to point to correct build context"
sed -i "s,context:.*$,context: ${deployPath},g" ${deployPath}/docker-compose.yml
