FROM openjdk:8-jdk-alpine
COPY target/your-application.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
