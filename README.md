# Java Backend Service

## Overview

A Java-based REST API for managing a collection of books.

The application provides CRUD (Create, Read, Update, Delete) operations through REST endpoints, uses Spring Boot to handle HTTP requests, and stores book records in an H2 database.

The project demonstrates object-oriented programming, REST API development, database integration, exception handling, and automated testing using Java and Spring Boot.

## Features

- Create, retrieve, update, and delete book records.
- RESTful API endpoints for book management.
- Persistent data storage using H2.
- Custom exception handling for books that cannot be found.
- Automated unit and integration testing.

## Technologies

- Java 21
- Spring Boot 4.1.2
- Spring Data JPA
- H2 Database
- Maven
- JUnit 5
- Mockito
- Git and GitHub

## Project Structure

```text
java-backend-service/
├── src/
│   ├── main/
│   │   ├── java/com/portfolio/backend/
│   │   │   ├── controller/
│   │   │   ├── exception/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── BackendServiceApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/portfolio/backend/
│           ├── service/
│           │   └── BookServiceTest.java
│           └── BookApiIntegrationTest.java
├── data/
├── pom.xml
└── README.md
```

## Installation and Setup

### Prerequisites

- Java Development Kit (JDK) 21
- Apache Maven 3.10.1

### Build the Project

Clone the repository:

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
```

Navigate into the project directory:

```bash
cd java-backend-service
```

Build the application:

```bash
mvn clean install
```

## Running the Application

Start the Spring Boot application:

```bash
mvn spring-boot:run
```

The application runs locally on:

```text
http://localhost:8080
```

Check the application health endpoint:

```text
GET http://localhost:8080/api/health
```

Expected response:

```text
Backend service is running
```

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/books` | Retrieve all books |
| GET | `/api/books/{id}` | Retrieve a book by ID |
| POST | `/api/books` | Create a new book |
| PUT | `/api/books/{id}` | Update an existing book |
| DELETE | `/api/books/{id}` | Delete a book |
| GET | `/api/health` | Check application health |

### Example: Create a Book

Send a POST request to:

```text
http://localhost:8080/api/books
```

Request body:

```json
{
    "title": "The Hobbit",
    "author": "J.R.R. Tolkien",
    "publicationYear": 1937
}
```

### Example: Retrieve All Books

Send a GET request to:

```text
http://localhost:8080/api/books
```

## Database

The application uses an H2 database with file-based storage to persist book records between application restarts.

Spring Data JPA handles database operations and entity persistence.

## Exception Handling

A custom `BookNotFoundException` handles requests for book records that do not exist.

A global exception handler returns an HTTP 404 Not Found response with an explanatory error message.

## Running Tests

Run the automated test suite:

```bash
mvn test
```

The tests cover:

- Retrieving all books.
- Retrieving a book by ID.
- Creating a book.
- Updating a book.
- Deleting a book.
- Handling requests for nonexistent books.
- Integration testing of the REST API and database.

## Project Purpose

This project was developed as a practical demonstration of Java backend development, including object-oriented programming, REST API design, database persistence, exception handling, and automated testing.