# 🏥 Auth Backend Module - Local Setup Guide

## Prerequisites

- Java 17+
- Maven
- MySQL (running locally)
- Postman or any API testing tool

---

## Database Setup

### 1. Create Database

Login to MySQL and run:

```sql
CREATE DATABASE vms_db;
```

### 2. Configure Database Connection

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/vms_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

Replace `YOUR_PASSWORD` with your MySQL password.

---

## Running the Application

### Using Terminal

```bash
mvn clean install
mvn spring-boot:run
```

### Using IntelliJ IDEA

1. Open the project
2. Open `BackendApplication.java`
3. Click **Run**

**✅ Application runs on:** `http://localhost:8080`

---

## Testing the APIs

### 1. Signup

**POST** `http://localhost:8080/auth/signup`

```json
{
  "name": "John Doe",
  "email": "john@test.com",
  "password": "password123",
  "role": "USER"
}
```

### 2. Login

**POST** `http://localhost:8080/auth/login`

```json
{
  "email": "john@test.com",
  "password": "password123"
}
```

**Response:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "user": {
    "id": 1,
    "name": "John Doe",
    "email": "john@test.com",
    "role": "USER"
  }
}
```

Copy the `token` value.

### 3. Test Protected Endpoint

**GET** `http://localhost:8080/test`

**Authorization Header:**

```
Authorization: Bearer <PASTE_TOKEN_HERE>
```

**Expected Response:**

```
JWT is working!
```

---

## Quick Test Flow

1. Create a user via signup
2. Login with those credentials
3. Copy the JWT token from response
4. Add token to Authorization header
5. Call the protected `/test` endpoint
