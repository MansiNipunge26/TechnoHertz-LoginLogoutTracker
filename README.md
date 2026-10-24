# TechnoHertz LoginLogoutTracker — Backend

The backend service for TechnoHertz LoginLogoutTracker, a full-stack employee login and logout tracking application built using Java, Spring Boot, Spring Data JPA, Hibernate, and MySQL.

## 🌐 Deployment

- **Backend:** https://technohertz-loginlogouttracker-1.onrender.com
- **Frontend:** https://techno-hertz-login-logout-tracker-f.vercel.app
- **Backend Repository:** https://github.com/MansiNipunge26/TechnoHertz-LoginLogoutTracker
- **Frontend Repository:** https://github.com/MansiNipunge26/TechnoHertz-LoginLogoutTracker-Frontend

## ✨ Features

- Employee login validation
- Login session creation
- Active-session conflict handling
- Employee logout and session closure
- Session duration calculation
- Session history retrieval
- MySQL database integration
- REST API endpoints for frontend integration

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java 17 | Backend programming |
| Spring Boot | Application framework |
| Spring Web | REST API development |
| Spring Data JPA | Database access |
| Hibernate | Object-relational mapping |
| MySQL | Persistent data storage |
| Maven | Dependency and build management |
| Render | Backend deployment |
| Aiven | Cloud MySQL hosting |

## 🏗️ Architecture

```text
React Frontend
      |
      | HTTP / REST API
      v
Spring Boot Controllers
      |
      v
Service Layer
      |
      v
Spring Data JPA Repositories
      |
      v
Hibernate ORM
      |
      v
MySQL Database (Aiven)
```

## 🔌 API Endpoints

Base URL:

```text
https://technohertz-loginlogouttracker-1.onrender.com
```

| HTTP Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/auth/login` | Validates credentials and creates a login session |
| POST | `/api/auth/logout/{sessionId}` | Closes the specified login session |
| GET | `/api/auth/history/{userId}` | Retrieves session history for a user |

### Login

```http
POST /api/auth/login
```

The current implementation accepts `username` and `password` as request parameters.

### Logout

```http
POST /api/auth/logout/{sessionId}
```

Provide the session ID in the URL to identify the session to close.

### Session History

```http
GET /api/auth/history/{userId}
```

Provide the user ID to retrieve the corresponding session history.

## 💻 Local Setup

### Prerequisites

- JDK 17
- MySQL
- Maven or Maven Wrapper
- Git
- An IDE such as Visual Studio Code or Eclipse

### 1. Clone the repository

```bash
git clone https://github.com/MansiNipunge26/TechnoHertz-LoginLogoutTracker.git
cd TechnoHertz-LoginLogoutTracker
```

### 2. Configure the database

Set the following environment variables using your own MySQL database connection details:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

Example `application.properties` configuration:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Configure any required SSL settings for your database provider. Never commit actual passwords or secrets to GitHub.

### 3. Run the application

```bash
mvn spring-boot:run
```

If the project contains a Maven Wrapper, you can use it instead of installing Maven.

The default local Spring Boot URL is:

```text
http://localhost:8080
```

### 4. Test the APIs

Use Postman to test the login, logout, and session-history endpoints. Check the request parameters and response format against the current controller implementation.

## 🗄️ Database

The application uses MySQL to store employee information and login-session records.

The session table includes fields such as:

- `id`
- `user_id`
- `login_time`
- `logout_time`
- `duration_seconds`
- `status`

The application uses session statuses such as `ACTIVE` and `LOGGED_OUT` in the current implementation.

## ☁️ Deployment

The backend is deployed on Render and connects to the Aiven MySQL database.

Keep the database connection settings in Render environment variables rather than hardcoding credentials in the source code.

## 🔒 Security Notes

- Do not commit database credentials.
- Validate credentials on the backend.
- Review password storage and authentication security before production use.
- Handle invalid credentials and active-session conflicts appropriately.
- Use HTTPS for deployed API traffic.

## 🚀 Future Enhancements

- Improved password hashing and authentication
- Role-based access control
- Automated unit and integration tests
- API documentation using OpenAPI/Swagger
- Logging and monitoring
- Employee attendance reports

## 👩‍💻 Author

**Mansi Nipunge**

GitHub: https://github.com/MansiNipunge26

LinkedIn: https://www.linkedin.com/in/mansi-nipunge-5aa361376/

## 📄 License

Created for educational, portfolio, and project demonstration purposes. Add a license file if you intend to distribute the project under specific licensing terms.
