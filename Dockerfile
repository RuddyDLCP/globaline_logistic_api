FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENV PORT=8080
# Configuración para que la aplicación use la variable PORT de Railway
CMD ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar"]
