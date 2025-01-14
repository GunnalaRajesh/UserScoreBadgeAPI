# Leaderboard Management API

## Overview

This is a RESTful API built using Spring Boot and MongoDB to manage a leaderboard for a coding contest. The service supports operations for user registration, score updates, badge assignments, and retrieval of leaderboard data.

## Features

- User Registration with initial score and badges.
- Score updates with dynamic badge assignments.
- CRUD operations for user management.
- Leaderboard retrieval sorted by score in descending order.
- Error handling with meaningful HTTP status codes and messages.

---

## Requirements

### Software

- Java 17+
- Spring Boot 3.0+
- MongoDB 4.0+

### Dependencies

The following dependencies are used in the project:

- Spring Boot Starter Web
- Spring Boot Starter Data MongoDB
- Spring Boot Starter Test

---

## API Endpoints

### 1. **Get All Users**

**Endpoint:** `GET /users`

**Description:** Retrieve a list of all registered users, sorted by score in descending order.



### 2. **Get User by ID**

**Endpoint:** `GET /users/{userId}`

**Description:** Retrieve details of a specific user by their unique ID.



### 3. **Register a New User**

**Endpoint:** `POST /users`

**Description:** Register a new user with an initial score of 0 and no badges.

**Request Body:**

```json
{
    "userId": "123",
    "username": "JohnDoe"
}
```



### 4. **Update User Score**

**Endpoint:** `PUT /users/{userId}`

**Description:** Update a user’s score and dynamically assign badges based on the score.

**Request Parameter:**

- `score`: Integer value (0-100).



### 5. **Delete User**

**Endpoint:** `DELETE /users/{userId}`

**Description:** Deregister a specific user from the contest.

**Response:**

- `204 No Content`

---

## Validation Rules

- **Score:** Must be between 0 and 100 (inclusive).
- **User Registration:** Requires both `userId` and `username`.
- **Badges:**
  - Awarded based on score:
    - `1 <= Score < 30`: Code Ninja
    - `30 <= Score < 60`: Code Champ
    - `60 <= Score <= 100`: Code Master
  - Maximum of three unique badges per user.

---

## Error Handling

| HTTP Code | Message               | Scenario                    |
| --------- | --------------------- | --------------------------- |
| 400       | Invalid Request       | Invalid input data.         |
| 404       | User not found        | User ID does not exist.     |
| 500       | Internal Server Error | Unexpected server behavior. |

---

## Running the Application

1. Clone the repository.
2. Update the MongoDB configuration in `application.properties`:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/leaderboard
   ```
3. Build and run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
4. The API will be available at `http://localhost:8080`
