FROM openjdk:8-jdk-alpine
VOLUME /main-app
ADD target/management-0.0.1-SNAPSHOT.jar management.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/management.jar"]