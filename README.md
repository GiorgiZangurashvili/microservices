# spring-boot-microservices

An online shop backend application with Microservices Architecture.

This is a simple Microservices project built using Java Spring Boot. Each service contains nothing complex; the sole purpose of this project was for me to become familiar with the concepts of Microservices Architecture.

## How to run the project without Docker:
Build the applications by navigating to each module and running: `mvn clean verify -DskipTests`
Run the applications by navigating to each module and running: `mvn spring-boot:run`

## How to run the project with Docker:
Build the applications and create Docker images locally: `mvn clean package -DskipTests`
Run the application: `docker compose up -d`

Note: Running the project with Docker might not work because I could not test it thoroughly due to having a low-end PC. At this moment, running the application without Docker is recommended.
