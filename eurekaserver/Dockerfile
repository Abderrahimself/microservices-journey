FROM openjdk:17-jdk-slim

VOLUME /tmp

COPY target/eurekaserver-*.jar eurekaserver.jar

ENTRYPOINT ["java","-jar","/eurekaserver.jar"]

EXPOSE 8761