FROM amazoncorretto:17
COPY ./target/population-project-*-jar-with-dependencies.jar /tmp/app.jar
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "app.jar", "db:3306", "30000"]
