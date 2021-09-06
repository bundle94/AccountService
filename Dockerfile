FROM openjdk:8-jdk-alpine
EXPOSE 7070
ARG JAR_FILE=target/accountservice-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]