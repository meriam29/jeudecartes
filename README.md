# Card Game API

## Overview

The **Card Game API** allows users to generate a random hand of unique cards from a set of 52 cards. The API supports:

- Generating a random hand of cards with a specified number of cards (`1 <= x <= 52`).
- Ensuring that all generated cards in the hand are unique.

This project is implemented in Java using **Spring Boot** and follows best practices for scalability and testability.

---

## Features

- **Random Hand Generation**:
    - Generate a random hand of `x` unique cards, where `1 <= x <= 52`.
    - Cards are represented by their `Color` (e.g., "COEUR", "PIQUE") and `Value` (e.g., "AS", "ROI").

- **Validation**:
    - Ensures that the requested number of cards is within the valid range.
    - Provides clear error responses for invalid inputs.

- **RESTful API**:
    - Endpoints for interacting with the card generation logic.

- **Comprehensive Tests**:
    - Includes unit and integration tests to validate functionality and ensure robustness.

---

## Technologies Used

- **Java**: Core language for the implementation.
- **Spring Boot**: Framework for building RESTful APIs.
- **JUnit 5**: Testing framework for unit and integration tests.
- **Maven**: Build tool for managing dependencies and running the application.
- **Jackson**: For JSON serialization and deserialization.

---

## Prerequisites

To run this application, ensure the following are installed:

1. **Java**: Version 17 or higher.
2. **Maven**: Version 3.8 or higher.
3. **Git**: For version control (optional).

---

## How to Run the Application

### 1. Clone the Repository

```bash
git clone https://github.com/meriam29/jeudecartes
cd jeudecartes
```

### 2. Build the Application

Use Maven to build the project:

```bash
mvn clean install
```

### 3. Run the Application

Start the Spring Boot application:

```bash
mvn spring-boot:run
```

The application will start and be accessible at `http://localhost:8080`.

---

## API Endpoints

### **1. Generate Random Hand**

#### **Endpoint**:

```http
GET /api/game/generate-hand
```

#### **Query Parameters**:

- `cardNumber` (integer, required): Number of cards to generate (1 to 52).

#### **Request Example**:

```http
GET http://localhost:8080/api/game/generate-hand?cardNumber=5
```

#### **Response Example**:

```json
[
  {
    "color": "COEUR",
    "value": "AS"
  },
  {
    "color": "PIQUE",
    "value": "ROI"
  },
  {
    "color": "TREFLE",
    "value": "DIX"
  },
  {
    "color": "CARREAUX",
    "value": "SEPT"
  },
  {
    "color": "COEUR",
    "value": "DEUX"
  }
]
```

#### **Error Example**:

If `cardNumber` is invalid (e.g., `0`):

```json
{
  "error": "cardNumber must be between 1 and 52 inclusive."
}
```

---

## API Documentation

This project includes Swagger (via SpringDoc OpenAPI) for API documentation.

### Access Swagger UI

Once the application is running, Swagger UI is available at:

```http
http://localhost:8080/swagger-ui.html
```

---

## Testing

### Run Unit and Integration Tests

To execute the test suite:

```bash
mvn test
```

---

## Project Structure

```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com.meriammejri.jeudecarets
│   │   │   │   ├── controller
│   │   │   │   │   └── CardController.java
│   │   │   │   ├── dto
│   │   │   │   │   └── CardDto.java
│   │   │   │   ├── service
│   │   │   │   │   └── CardGameImpl.java
│   │   │   │   ├── utils
│   │   │   │   │   ├── Color.java
│   │   │   │   │   ├── Value.java
│   │   │   │   └── JeudecaretsApplication.java
│   ├── test
│       ├── java
│       │   ├── com.meriammejri.jeudecarets
│       │   │   ├── integration
│       │   │   │   └── CardControllerIntegrationTest.java
│       │   │   ├── service
│       │   │   │   └── CardGameImplTest.java
├── pom.xml
└── HELP.md
```

---
