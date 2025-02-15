# Etapa 1: Build
FROM maven:3.9.2-eclipse-temurin-17 AS builder
WORKDIR /app

# Copiar arquivos do projeto
COPY pom.xml .
COPY src ./src

# Fazer o build do projeto
RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar o jar gerado na etapa anterior
COPY --from=builder /app/target/processing-service-*.jar app.jar

# Expor a porta da aplicação
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]