# 🌊 Flood Guard API

A Spring Boot application for flood monitoring and alert management.

---

## 📋 Prerequisites

Before you start, make sure you have these installed on your computer:

- **Java 17** or higher ([Download here](https://www.oracle.com/java/technologies/downloads/))
- **Maven** ([Download here](https://maven.apache.org/download.cgi))
- **PostgreSQL** database ([Download here](https://www.postgresql.org/download/))
- **Git** ([Download here](https://git-scm.com/downloads))

---

## 🚀 Getting Started

### Step 1: Clone the Project

```bash
git clone <your-repository-url>
cd flood-guard
```

### Step 2: Set Up the Database

1. Open PostgreSQL and create a new database:
```sql
CREATE DATABASE floodguard;
```

2. Create a user (optional):
```sql
CREATE USER floodguard_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE floodguard TO floodguard_user;
```

### Step 3: Configure Environment Variables

Create a `.env` file in the project root directory:

```properties
# Database Configuration
DB_URL=jdbc:postgresql://localhost:5432/floodguard
DB_USERNAME=your_database_username
DB_PASSWORD=your_database_password

# JWT Secret (use a strong random string)
JWT_SECRET=your-super-secret-jwt-key-change-this-in-production
JWT_ACCESS_TOKEN_EXPIRATION=900000
JWT_REFRESH_TOKEN_EXPIRATION=604800000

# Admin Seeding (optional - creates default admin user)
APP_SEED_ADMIN_ENABLED=true
APP_SEED_ADMIN_USERNAME=admin
APP_SEED_ADMIN_EMAIL=admin@floodguard.com
APP_SEED_ADMIN_PASSWORD=Admin123!

# CORS (for frontend)
CORS_ALLOWED_ORIGINS=http://localhost:4200,http://localhost:3000
```

### Step 4: Install Dependencies

```bash
mvn clean install
```

### Step 5: Run the Application

```bash
mvn spring-boot:run
```

The application will start on **http://localhost:8080**

---

## 📚 API Documentation

Once the application is running, you can access the interactive API documentation:

- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **API Docs (JSON)**: http://localhost:8080/v3/api-docs

---

## 🔑 Authentication

This API uses **JWT (JSON Web Tokens)** for authentication.

### Register a New User

**POST** `/api/auth/register`

```json
{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "SecurePass123!"
}
```

### Login

**POST** `/api/auth/login`

```json
{
  "username": "john_doe",
  "password": "SecurePass123!"
}
```

**Response:**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer"
}
```

### Using the Token

Add the token to your request headers:

```
Authorization: Bearer <your-access-token>
```

---

## 🛠️ Main Features & Endpoints

### 🔐 Authentication (`/api/auth`)
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login
- `POST /api/auth/refreshtoken` - Refresh access token

### 🚨 Alerts (`/api/alerts`)
- `GET /api/alerts` - Get all alerts (paginated)
- `POST /api/alerts` - Create new alert
- `GET /api/alerts/threshold` - Get alert threshold
- `PUT /api/alerts/threshold` - Update alert threshold

### 🌊 Flood Events (`/api/flood-events`)
- `GET /api/flood-events` - Get all flood events
- `POST /api/flood-events` - Create flood event
- `GET /api/flood-events/{id}` - Get specific flood event
- `PUT /api/flood-events/{id}` - Update flood event
- `DELETE /api/flood-events/{id}` - Delete flood event

### 👤 Users (`/api/users`)
- `GET /api/users/profile` - Get current user profile
- `PUT /api/users/profile` - Update user profile

### 🎯 Simulations (`/api/simulations`)
- `POST /api/simulations` - Run flood simulation
- `GET /api/simulations` - Get all simulations
- `GET /api/simulations/{id}` - Get specific simulation

---

## 🧪 Running Tests

```bash
mvn test
```

---

## 📁 Project Structure

```
flood-guard/
├── src/
│   ├── main/
│   │   ├── java/com/floodguard/
│   │   │   ├── config/          # Configuration classes
│   │   │   ├── controller/      # REST API endpoints
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── entity/          # Database entities
│   │   │   ├── exception/       # Custom exceptions
│   │   │   ├── repository/      # Database repositories
│   │   │   ├── security/        # Security & JWT
│   │   │   └── service/         # Business logic
│   │   └── resources/
│   │       └── application.properties
│   └── test/                    # Unit tests
├── .env                         # Environment variables
├── pom.xml                      # Maven dependencies
└── README.md
```

---

## 🐛 Common Issues & Solutions

### Issue: "Port 8080 is already in use"

**Solution:** Change the port in `application.properties`:
```properties
server.port=8081
```

### Issue: "Database connection failed"

**Solution:** 
1. Make sure PostgreSQL is running
2. Check your `.env` file has correct database credentials
3. Verify the database exists

### Issue: "JWT token expired"

**Solution:** Login again to get a new token

### Issue: "Access Denied / 403 Error"

**Solution:** 
1. Make sure you're logged in
2. Check if your token is included in the Authorization header
3. Verify your user has the required role/permissions

---

## 🔧 Technologies Used

- **Spring Boot 4.0.2** - Backend framework
- **Spring Security** - Authentication & Authorization
- **JWT** - Token-based authentication
- **PostgreSQL** - Database
- **Hibernate/JPA** - ORM
- **Lombok** - Reduce boilerplate code
- **MapStruct** - Object mapping
- **SpringDoc OpenAPI** - API documentation
- **JUnit 5 & Mockito** - Testing

---

## 📝 Environment Variables Reference

| Variable | Description | Example |
|----------|-------------|---------|
| `DB_URL` | Database connection URL | `jdbc:postgresql://localhost:5432/floodguard` |
| `DB_USERNAME` | Database username | `postgres` |
| `DB_PASSWORD` | Database password | `your_password` |
| `JWT_SECRET` | Secret key for JWT signing | `your-secret-key` |
| `JWT_ACCESS_TOKEN_EXPIRATION` | Access token expiration (ms) | `900000` (15 min) |
| `JWT_REFRESH_TOKEN_EXPIRATION` | Refresh token expiration (ms) | `604800000` (7 days) |
| `APP_SEED_ADMIN_ENABLED` | Create default admin user | `true` or `false` |
| `CORS_ALLOWED_ORIGINS` | Allowed frontend origins | `http://localhost:4200` |

---

## 📞 Need Help?

- Check the [Swagger UI](http://localhost:8080/swagger-ui/index.html) for detailed API documentation
- Review the code comments in the source files
- Check application logs for error messages

---

## 📄 License

This project is for educational purposes.

---

**Happy Coding! 🚀**
