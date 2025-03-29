FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app

COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app

COPY --from=build /app/target/empresa-0.0.1-SNAPSHOT.jar empresa-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "empresa-0.0.1-SNAPSHOT.jar"]

ENTRYPOINT ["java", "-jar", "empresa-0.0.1-SNAPSHOT.jar"]