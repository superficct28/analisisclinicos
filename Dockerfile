# Usa una imagen base de OpenJDK
FROM eclipse-temurin:17-jdk-alpine

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado en el contenedor
COPY medicos-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que correrá la aplicación (8080 o el que hayas configurado)
EXPOSE 8081

# Define el comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
