FROM openjdk:alpine

COPY common/app-common-0.0.1.jar /opt/app-common-0.0.1.jar
COPY app-fcc/app-fcc.jar /opt/app-fcc.jar

ENTRYPOINT ["java", "-jar", "/opt/app-fcc.jar"]			  
