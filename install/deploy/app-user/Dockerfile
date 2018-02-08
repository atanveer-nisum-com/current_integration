FROM openjdk:alpine

COPY common/app-common-0.0.1.jar /opt/app-common-0.0.1.jar
COPY app-user/app-user.jar /opt/app-user.jar

ENTRYPOINT ["java", "-jar", "/opt/app-user.jar"]			  
