FROM maven:3.8.5-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
RUN find /app/target -name "*.jar" -type f

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/anonymous-chat-0.0.1-SNAPSHOT.jar app.jar
ENV PORT=8080
EXPOSE ${PORT}
CMD ["java", "-jar", "-Dserver.port=${PORT}", "app.jar"]
