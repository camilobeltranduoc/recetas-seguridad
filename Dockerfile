# Dockerfile para aplicación de Recetas con Spring Boot
# Usa multi-stage build para optimizar el tamaño de la imagen

# Etapa 1: Build
FROM eclipse-temurin:21-jdk-alpine AS build

# Directorio de trabajo
WORKDIR /app

# Copiar archivos de configuración de Maven
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Dar permisos de ejecución al wrapper de Maven
RUN chmod +x mvnw

# Descargar dependencias (se cachea esta capa si pom.xml no cambia)
RUN ./mvnw dependency:go-offline -B

# Copiar código fuente
COPY src src

# Compilar la aplicación (sin ejecutar tests para agilizar)
RUN ./mvnw clean package -DskipTests

# Etapa 2: Runtime
FROM eclipse-temurin:21-jre-alpine

# Crear usuario no-root para ejecutar la aplicación (seguridad)
RUN addgroup -S spring && adduser -S spring -G spring

# Directorio de trabajo
WORKDIR /app

# Copiar el JAR compilado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Cambiar al usuario no-root
USER spring:spring

# Exponer el puerto 8080
EXPOSE 8080

# Variables de entorno por defecto
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Comando para ejecutar la aplicación
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
