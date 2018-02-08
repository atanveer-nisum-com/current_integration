FROM openjdk:alpine

COPY common/app-common-0.0.1.jar /opt/app-common-0.0.1.jar
COPY app-promotion/app-promotion.jar /opt/app-promotion.jar

ENTRYPOINT ["java", "-jar", "/opt/app-promotion.jar"]			  
