FROM openjdk:alpine

COPY common/app-common-0.0.1.jar /opt/app-common-0.0.1.jar
COPY app-shop/app-shop.jar /opt/app-shop.jar

ENTRYPOINT ["java", "-jar", "/opt/app-shop.jar"]			  
