FROM eclipse-temurin:17-jdk-alpine
COPY ./target/population-project-1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "population-project-1.0.2-jar-with-dependencies.jar"]