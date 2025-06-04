# 📚 Library Management System – Spring Boot + JWT Security

This is a secure RESTful API built with Spring Boot for managing library books and users, with **JWT-based authentication** and **role-based access** (`ADMIN`, `MEMBER`).

---

## 🚀 Features

- JWT-based login authentication
- Role-based authorization (`ADMIN` vs `MEMBER`)
- Secure API endpoints
- Spring Security integration
- MySQL or PostgreSQL DB support
- DTO-based login and token responses

---

## 🛠️ Setup Instructions

### 1. Prerequisites

- Java 17+
- Maven 3.6+
- IDE (IntelliJ / VS Code / Eclipse)
- Postman or `curl`

---

### 2. Clone the Project

```bash
git clone <your-repo-url>
cd your-project-folder

##  Configure the Database and Application.properties
spring.application.name=Diatoz-java-Assesment
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
spring.datasource.url=jdbc:mysql://localhost:3306/yourdatabase name  
spring.datasource.username=your username
spring.datasource.password= your password
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=true


#  JWT Authentication

## Registration Endpoint

POST/api/auth/register - for  registring  new User admin or Member


body json
{
  "username": "member",
  "password": "admin123",
  "role": "MEMBER"
}


## Login Endpoint
POST /api/auth/login

Sample Request
{
  "username": "admin",
  "password": "admin"
}

response:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
Save this token and use it in the header to access secured endpoints.

🔸 Using Token in Requests
Include the token in the Authorization header for all protected routes:
curl -H "Authorization: Bearer <your-token>" http://localhost:8080/api/books
Go to Authorization → Bearer Token, and paste your JWT.


## Sample Endpoints

localhost port =8080

 GET /api/books – View available books 
● POST /api/books/borrow/{bookId} – Borrow book (member) 
● POST /api/books/return/{bookId} – Return book (member) 
● POST /api/books – Add book (admin)
● PUT /api/books{BookId} – Update book (admin)
● DELETE /api/books{BookId} – delete book (admin)
● GET/api/history- borrow and return books history (admin)



