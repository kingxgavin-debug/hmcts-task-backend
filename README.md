# HMCTS Task Manager Backend

This is a Spring Boot backend API for managing tasks, built as part of the HMCTS Developer Challenge.

## Features

- Create / Read / Update / Delete tasks
- MySQL database integration
- RESTful API endpoints
- API skeleton with placeholder logic
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
4. Run the project using:

```bash
mvn spring-boot:run