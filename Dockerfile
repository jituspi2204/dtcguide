FROM maven:3.9-amazoncorretto-23 as build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:23-jdk-slim
COPY --from=build /target/backend-0.0.1-SNAPSHOT.jar backend.jar
EXPOSE 8080
ENTRYPOINT ["java" , "-jar" , "backend.jar"]

