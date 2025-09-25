# Start with OpenJDK 17 base image
FROM openjdk:17-jdk-slim

# Set a volume for temporary files
VOLUME /tmp

# Set working directory
WORKDIR /app

# Copy jar file (will be built using maven/gradle)
ARG JAR_FILE=target/FitMeal-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Expose application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
