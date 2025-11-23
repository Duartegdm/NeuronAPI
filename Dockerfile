FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiar o JAR
COPY target/neuron-1.0.0-SNAPSHOT-runner.jar app.jar

# Expor a porta
EXPOSE 8080

# Health check (opcional)
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/health || exit 1

# Comando para executar
ENTRYPOINT ["java", "-jar", "app.jar"]