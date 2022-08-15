FROM openjdk:11
EXPOSE 8080
WORKDIR /task-manager/application
ENV DATA_SOURCE_HOST "172.17.0.2"
COPY ../target/task-service-0.0.1-SNAPSHOT.jar /task-manager/application
ENTRYPOINT ["java","-jar","task-service-0.0.1-SNAPSHOT.jar"]