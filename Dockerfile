# =========================
# 1️⃣ BUILD STAGE
# =========================
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /build

# Copiamos pom primero (mejor cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código
COPY src ./src

# Build del jar
RUN mvn clean package -DskipTests

# =========================
# 2️⃣ RUNTIME STAGE
# =========================

# 1️⃣ Imagen base: Java 17
FROM eclipse-temurin:17-jdk

# 2️⃣ Directorio de trabajo dentro del container
WORKDIR /app

# Copiamos solo el jar final
COPY --from=build /build/target/miaplicacion-0.0.1-SNAPSHOT.jar app.jar

# 4️⃣ Exponemos el puerto de la app
EXPOSE 8085

# 5️⃣ Comando para arrancar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
