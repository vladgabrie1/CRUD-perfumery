FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-19-jdk -y
COPY . .

RUN ./gradlew bootJar --no-daemon

FROM openjdk:19-jdk

EXPOSE 8080

COPY --from=build /build/libs/CRUD-Perfumery-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]