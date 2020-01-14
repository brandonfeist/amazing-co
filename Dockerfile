FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ADD target/amazing-company-0.0.1-SNAPSHOT.jar amazingco.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/amazingco.jar"]