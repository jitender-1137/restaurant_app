# Use a base image with Gradle and Java
FROM gradle:7.4.0-jdk11 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle build files to the container
COPY build.gradle settings.gradle gradlew /app/

# Copy the Gradle wrapper
COPY gradle /app/gradle

# Download and cache Gradle dependencies
RUN ./gradlew --no-daemon dependencies

# Copy the application source code
COPY src /app/src

# Build the application using Gradle
RUN ./gradlew --no-daemon build

# Create a new stage for the final image
FROM openjdk:21

# Set the working directory inside the container
WORKDIR /app

# Expose the port used by the application
EXPOSE 9090

# Copy the built JAR file from the builder stage to the final image
COPY --from=builder /app/build/libs/restaurant-0.0.1-SNAPSHOT.jar /app/

# Specify the command to run the application
CMD ["java", "-jar", "restaurant-0.0.1-SNAPSHOT.jar"]
