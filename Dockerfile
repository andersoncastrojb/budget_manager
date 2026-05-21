FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

COPY gradlew .
COPY gradle gradle

COPY build.gradle .
COPY settings.gradle .
COPY configuration/build.gradle configuration/
COPY core/build.gradle core/
COPY entrypoints/build.gradle entrypoints/
COPY infrastructure/build.gradle infrastructure/
COPY utilities/build.gradle utilities/

# Copy the rest of the source code
COPY . .

# Grant execution rights to the Gradle wrapper
RUN chmod +x ./gradlew

# Build the executable jar for the configuration module
RUN ./gradlew :configuration:bootJar -x test --no-daemon


FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=builder /app/configuration/build/libs/configuration-0.0.1-SNAPSHOT.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]