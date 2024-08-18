FROM maven:3.9.8-amazoncorretto-17-al2023 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*.jar /app/email_sender.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/email-sender.jar"]