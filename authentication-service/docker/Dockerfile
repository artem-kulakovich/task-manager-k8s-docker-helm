FROM openjdk:11
EXPOSE 8080
WORKDIR /task-manager/application
COPY ../target/authentication-service-0.0.1-SNAPSHOT.jar /task-manager/application
ENTRYPOINT ["java","-jar","authentication-service-0.0.1-SNAPSHOT.jar"]