# Stage 1: Build
FROM eclipse-temurin:21-jdk-alpine as builder

WORKDIR /app

# Copiar arquivos do Maven
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copiar código fonte
COPY src src

# Dar permissão e fazer build
RUN chmod +x mvnw && \
    ./mvnw clean package -Dquarkus.package.type=uber-jar -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiar o JAR do stage de build
COPY --from=builder /app/target/*-runner.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]