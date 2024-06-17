# Etapa de construcción
FROM maven:3.8.4-openjdk-17-slim AS build

# Copiar el proyecto y compilarlo
WORKDIR /usr/src/restaurant_modern
COPY . .
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:17-jre-focal
WORKDIR /usr/restaurant_modern
COPY --from=build /usr/src/restaurant_modern/target/restaurant_modern-0.0.1-SNAPSHOT.jar ./restaurant_modern-0.0.1-SNAPSHOT.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "restaurant_modern-0.0.1-SNAPSHOT.jar"]
