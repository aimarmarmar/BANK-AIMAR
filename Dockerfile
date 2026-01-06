# -- Tahap 1: Build (Masak Kodingan jadi JAR) --
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
# Kita skip test biar proses build di server lebih cepat
RUN mvn clean package -DskipTests

# -- Tahap 2: Run (Jalankan Aplikasi) --
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Ambil hasil masakan (file JAR) dari Tahap 1
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]