# HMCTS Task Manager Backend

This is a Spring Boot backend API for managing tasks, built as part of the HMCTS Developer Challenge.

## Features

- Create / Read / Update / Delete tasks
- MySQL database integration
- RESTful API endpoints
- SQL script included for manual database setup

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL

## Setup Instructions

1. Create a MySQL database named `taskdb`
2. Run the SQL script in `src/main/resources/schema.sql` to create the `tasks` table
3. Update `application.properties` with your MySQL credentials
4. Run unit test
mvn test
Unit tests are located under `src/test/java`.  
Controller tests use `@WebMvcTest` and `MockMvc` to validate API behavior without starting the full application.

5. Clone the project:
git clone https://github.com/kingxgavin-debug/hmcts-task-backend.git
cd hmcts-task-backend

6. Build the project using:
mvn clean install

7. Run the project using:
mvn spring-boot:run


## Data Model

Each task contains the following fields:

- `title` (String): required
- `description` (String): optional
- `status` (String): one of `pending`, `active`, `done`
- `dueDate` (LocalDateTime): must be a future date


## API Endpoints

All task management endpoints are under `/api/tasks`. The API supports creating, retrieving, updating, and deleting tasks.

---

### Create Task

**POST `/api/tasks`**
Create a new task.

**Request Body:**

```json
{
  "title": "Task Title",
  "description": "Description (Optional)",
  "status": "active",
  "dueDate": "2025-09-15T10:00"
}
```

**Response: `200 OK`**

```json
{
  "title": "Task Title",
  "description": "Description (Optional)",
  "status": "active",
  "dueDate": "2025-09-15T10:00"
}
```

---

### Get All Tasks

**GET `/api/tasks`**   
Retrieve all tasks.

**Response: `200 OK`**

```json
[
  {
    "title": "Task 1",
    "description": "desc1",
    "status": "active",
    "dueDate": "2025-09-16T09:00"
  },
  {
    "title": "Task 2",
    "description": "desc2",
    "status": "done",
    "dueDate": "2025-09-17T14:00"
  }
]
```

---

### Get Task by ID

**GET `/api/tasks/{id}`**
Retrieve a task by its ID.

**Response: `200 OK`**

```json
{
  "title": "Task A",
  "description": "descA",
  "status": "active",
  "dueDate": "2025-09-18T12:00"
}
```

---

### Update Task

**PUT `/api/tasks/{id}`  **
Update a task's status.

**Request Body:**

```json
{
  "title": "Task A",
  "description": "descA",
  "status": "active",
  "dueDate": "2025-09-18T12:00"
}
```

**Response: `200 OK`**

```json
{
  "title": "Task A",
  "description": "descA",
  "status": "active",
  "dueDate": "2025-09-18T12:00"
}
```

---

### Delete Task

**DELETE `/api/tasks/{id}`  **
Delete a task by its ID.

**Response: `204 No Content`**

---

### Notes

- All dates use LocalDateTime format (e.g. `2025-09-15T10:00`)
- `status` values: `pending`, `active`, `done`
- All request bodies must be `application/json`
- Input validation is handled by spring-boot-starter-validation via @Valid annotations in the controller

