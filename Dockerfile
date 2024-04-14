FROM openjdk:21
WORKDIR /app
EXPOSE 9090
COPY ./target/restaurant-0.0.1-SNAPSHOT.jar /app
CMD ["java","-jar","restaurant-0.0.1-SNAPSHOT.jar"]