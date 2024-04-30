# Use a base image with OpenJDK installed
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY target/BookHaven-0.0.1-SNAPSHOT.jar /app/BookHaven-0.0.1-SNAPSHOT.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "BookHaven-0.0.1-SNAPSHOT.jar"]
