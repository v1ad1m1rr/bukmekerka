from openjdk:21-jdk-slim-buster
workdir /app
copy /target/MyWebApp-0.0.1-SNAPSHOT.jar /app/MyApp.jar
entrypoint ["java", "-jar", "MyApp.jar"]