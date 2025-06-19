# Etapa 1: Compilación
FROM maven:3.8-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Crear el directorio data para persistencia
RUN mkdir -p /app/data

# Crear un volumen para persistencia
VOLUME ["/app/data"]

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]