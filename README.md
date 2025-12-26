# TakeHomeChallenge

## Notifications Backend – Java Spring Boot

This project is a REST API developed in Java with Spring Boot for managing users and notifications, using JWT-based authentication and supporting multiple notification channels through the Strategy design pattern.

The main goal of this project is to demonstrate backend development best practices as part of a Take-Home Challenge for a Backend Java position.

## Description

The application allows authenticated users to:

- Manage users

- Create, update, and delete notifications

- Send notifications through different channels (e.g. email, push, etc.)

- Access secured endpoints using JWT authentication

The design is extensible by nature, allowing new notification channels to be added without modifying existing business logic.

## Features

- JWT-based authentication and authorization

- Users CRUD (User creation is handled through the authentication register endpoint )

- Notifications CRUD

- Multi-channel notification sending (Strategy Pattern)

- Interactive API documentation with Swagger (OpenAPI)

- Dockerized application

- Basic unit testing (JUnit / Mockito) – work in progress


## Tech Stack

- Java 17

- Spring Boot 3

- Maven 3.8+

- Hibernate (JPA implementation)

- Spring Web

- Spring Data JPA

- Spring Security

- JWT (JJWT)

- H2 Database (in-memory)

- Swagger / OpenAPI

- Docker

- JUnit 5

- Mockito

- Git (version control)

## Prerequisites
- Java 17

- Maven

- Docker (optional)

## Quick Start

### Run locally

    mvn clean install
    mvn spring-boot:run

### Run with Docker

    docker build -t notifications-app .
    docker run -p 8085:8085 -e JWT_SECRET=change_me notifications-app


## How to run the project

### Option 1: Run locally

- Clone the repository:

        git clone https://github.com/your-username/your-repository.git


- Build the project:

        mvn clean install


- Run the application:

        mvn spring-boot:run

The application will be available at:  http://localhost:8085

### Option 2: Run with Docker

- Build the Docker image:

        docker build -t notifications-app .


- Run the container:

        docker run -p 8085:8085 -e JWT_SECRET=change_me notifications-app

## Swagger / OpenAPI

The API is documented using Swagger.

Access it at: http://localhost:8085/swagger-ui.html

##  Authentication Flow with JWT

All endpoints are secured using JWT authentication.

To access the API:

1. Authenticate using the login endpoint with existing user credentials.
2. Copy the JWT returned in the response.
3. Open Swagger UI and click **Authorize**.
4. Paste the token using the following format: Bearer YOUR_JWT_TOKEN

Once authorized, all protected endpoints can be accessed normally.

For development purposes, a default user is created at startup.
Credentials can be found in the `.env.example` file.

## H2 Database

The application uses an in-memory H2 database, ideal for development and testing.

H2 Console: http://localhost:8085/h2-console

Configuration:

    JDBC URL: jdbc:h2:mem:devdb
    Driver: org.h2.Driver
    Username: sa
    Password:

## Design Decisions

### Technology choice: Java + Spring Boot
Java with Spring Boot was chosen due to its maturity, strong ecosystem, and wide adoption in enterprise backend systems. Spring Boot allows rapid development while enforcing best practices such as dependency injection, layered architecture, and testability.

### Architecture
A layered architecture was implemented to clearly separate responsibilities between controllers, services, repositories, and domain models.

DTOs are used to decouple API contracts from persistence models, improving maintainability and flexibility.

Swagger UI is used as a lightweight frontend to interact with and test the API endpoints.

The API follows RESTful principles, using standard HTTP methods, resource-oriented URLs,
and stateless communication.

### Notification Channels – Strategy Pattern
The Strategy design pattern was used to encapsulate notification delivery logic per channel. This allows new channels to be added without modifying existing code, adhering to the Open/Closed Principle.

### Authentication
JWT was selected to provide stateless authentication, enabling scalability and simplifying session management.

### Database
An in-memory H2 database is used to simplify setup and ensure the project can be executed easily in any environment. The same configuration is used for development and testing to ensure consistency.

### Containerization
Docker was added to ensure portability and reproducibility across environments.

## Extending Notification Channels

To add a new notification channel:

1. Create a new implementation of the notification strategy interface.
2. Implement the channel-specific sending logic.
3. Register the strategy in the application context.

No existing business logic needs to be modified, ensuring scalability and maintainability.

## Environment Variables

The application supports configuration via environment variables.

    Example: JWT_SECRET=change_me

A sample file with example values can be found in `.env.example`.

## Additional comments

Although the challenge suggests using a relational database such as PostgreSQL or MySQL,
H2 was chosen as an in-memory relational database to simplify setup and allow the project
to be executed easily without external dependencies.

The data model and JPA mappings are fully compatible with production-ready relational
databases and could be migrated with minimal effort.

## Possible Improvements

- Use a production-ready database (MySQL / PostgreSQL)

- Database migrations with Flyway or Liquibase

- Increase test coverage and expose coverage metrics

- Add integration / E2E tests

- Improve error handling and validation messages (ejemplo de uno q no este OK)

- Deployment (Heroku)