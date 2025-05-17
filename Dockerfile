FROM openjdk:23-jdk

WORKDIR /app

# Make sure to copy from the correct location
COPY build/libs/hello-world-api-on-k8s-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
