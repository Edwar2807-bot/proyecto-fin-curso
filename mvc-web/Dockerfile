# Usa Java 17 como base
FROM openjdk:17-jdk-slim

# Crea un volumen temporal para archivos temporales
VOLUME /tmp

# Define la variable del JAR generado por Spring Boot
ARG JAR_FILE=target/mvc-web-0.0.1-SNAPSHOT.jar

# Copia el archivo .jar al contenedor
COPY ${JAR_FILE} app.jar

# Expone el puerto 8088 (puerto interno del contenedor)
EXPOSE 8088

# Comando para ejecutar la app Spring Boot en el contenedor con puerto 8088
ENTRYPOINT ["java","-jar","/app.jar","--server.port=8088"]
