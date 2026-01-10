# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

COPY app-customer/pom.xml .
RUN mvn dependency:go-offline -B

COPY app-customer/src ./src
RUN mvn clean package -DskipTests

# Download New Relic Agent
RUN mkdir -p /app/newrelic
RUN curl -O https://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic-java.zip \
    && jar xf newrelic-java.zip \
    && mv newrelic/newrelic.jar /app/newrelic/newrelic.jar \
    && rm newrelic-java.zip


# Stage 2: Create the runtime image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar customer.jar
COPY --from=builder /app/newrelic/newrelic.jar /app/newrelic/newrelic.jar
EXPOSE 8080
CMD ["java", "-javaagent:/app/newrelic/newrelic.jar", "-jar", "customer.jar"]