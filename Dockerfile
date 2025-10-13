FROM openjdk:latest
COPY ./target/population-project-1.0.0-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "population-project-1.0.0-jar-with-dependencies.jar"]