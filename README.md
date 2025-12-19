# Movie REST API 🎬

A comprehensive RESTful API for managing movies, built with Spring Boot. This project implements full CRUD operations with an in-memory H2 database and features interactive API documentation using Swagger/OpenAPI.

> **📌 Internship Project:** This project was developed as **Task 1** for a Java Development Internship at **InternIntelligence**.

---

## ✨ Features

- ✅ **Full CRUD Operations** - Create, Read, Update, and Delete movies
- ✅ **RESTful Architecture** - Clean and standard REST endpoints
- ✅ **In-Memory Database** - H2 database for quick setup and testing
- ✅ **Interactive API Docs** - Swagger UI for easy API exploration
- ✅ **Spring Boot** - Modern Java framework for rapid development
- ✅ **JPA/Hibernate** - Simplified database operations with ORM

---

## 🛠️ Tech Stack

| Technology | Purpose |
|-----------|---------|
| **Java** | Programming language |
| **Spring Boot** | Application framework |
| **Spring Data JPA** | Data access layer |
| **H2 Database** | In-memory database |
| **SpringDoc OpenAPI** | API documentation (Swagger) |
| **Maven** | Dependency & build management |

---

## 📊 Movie Model

Each movie contains the following attributes:

| Field | Type | Description |
|-------|------|-------------|
| `id` | Long | Unique identifier (auto-generated) |
| `title` | String | Movie title |
| `director` | String | Director name |
| `releaseYear` | int | Year of release |
| `genre` | String | Movie genre |
| `imdbRating` | double | IMDb rating (0.0 - 10.0) |

---

## 🚀 API Endpoints

**Base URL:** `http://localhost:8080/api/movies`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/movies` | Retrieve all movies |
| `GET` | `/api/movies/{id}` | Retrieve a specific movie by ID |
| `POST` | `/api/movies` | Create a new movie |
| `PUT` | `/api/movies/{id}` | Update an existing movie |
| `DELETE` | `/api/movies/{id}` | Delete a movie by ID |

### 📝 Example Requests

**Create a Movie (POST):**

```json
POST /api/movies
Content-Type: application/json

{
  "title": "The Shawshank Redemption",
  "director": "Frank Darabont",
  "releaseYear": 1994,
  "genre": "Drama",
  "imdbRating": 9.3
}
```

**Response:**

```json
{
  "id": 1,
  "title": "The Shawshank Redemption",
  "director": "Frank Darabont",
  "releaseYear": 1994,
  "genre": "Drama",
  "imdbRating": 9.3
}
```

---

## 🏁 Getting Started

### Prerequisites

- ☕ Java 17 or higher
- 📦 Maven 3.6+

### Installation & Running

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd InternIntelligence_MovieAPI-main
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run tests (optional)**
   ```bash
   mvn test
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the application**
   
   Once running, access these endpoints:
   
   | Service | URL |
   |---------|-----|
   | **API Base** | `http://localhost:8080/api/movies` |
   | **Swagger UI** | `http://localhost:8080/swagger-ui.html` |
   | **H2 Console** | `http://localhost:8080/h2-console` |
   
   **H2 Database Credentials:**
   - JDBC URL: `jdbc:h2:mem:movie_db`
   - Username: `sa`
   - Password: *(leave empty)*

---

## 📁 Project Structure

```
InternIntelligence_MovieAPI-main/
│
├── src/
│   ├── main/
│   │   ├── java/com/internintelligence/movieapi/
│   │   │   ├── controller/
│   │   │   │   └── MovieController.java      # REST API endpoints
│   │   │   ├── model/
│   │   │   │   └── Movie.java                # Entity/Domain model
│   │   │   ├── repository/
│   │   │   │   └── MovieRepository.java      # Data access layer
│   │   │   ├── service/
│   │   │   │   └── MovieService.java         # Business logic layer
│   │   │   └── MovieApiApplication.java      # Main Spring Boot app
│   │   │
│   │   └── resources/
│   │       └── application.properties         # App configuration
│   │
│   └── test/
│       ├── java/com/internintelligence/movieapi/
│       │   └── controller/
│       │       └── MovieControllerTest.java   # Unit tests
│       └── resources/
│           └── application-test.properties    # Test configuration
│
├── .gitignore                                 # Git ignore rules
├── LICENSE                                    # MIT License
├── pom.xml                                    # Maven dependencies
└── README.md                                  # This file
```

---

## ⚙️ Configuration

The application is configured in `src/main/resources/application.properties`:

```properties
# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:movie_db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true

# JPA/Hibernate Configuration
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Swagger/OpenAPI Configuration
springdoc.api-docs.enabled=true
springdoc.swagger-ui.path=/swagger-ui.html
```

---

## 🧪 Testing the API

### Using Swagger UI (Recommended)

1. Start the application
2. Navigate to `http://localhost:8080/swagger-ui.html`
3. Explore and test all endpoints interactively with a beautiful UI

### Using cURL

**Get all movies:**
```bash
curl http://localhost:8080/api/movies
```

**Get a specific movie:**
```bash
curl http://localhost:8080/api/movies/1
```

**Create a movie:**
```bash
curl -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Inception",
    "director": "Christopher Nolan",
    "releaseYear": 2010,
    "genre": "Sci-Fi",
    "imdbRating": 8.8
  }'
```

**Update a movie:**
```bash
curl -X PUT http://localhost:8080/api/movies/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Inception",
    "director": "Christopher Nolan",
    "releaseYear": 2010,
    "genre": "Science Fiction",
    "imdbRating": 8.9
  }'
```

**Delete a movie:**
```bash
curl -X DELETE http://localhost:8080/api/movies/1
```

### Using Postman

Import the API endpoints into Postman using the OpenAPI specification available at:
`http://localhost:8080/v3/api-docs`

---

## 💡 Future Enhancements

Some potential improvements for this project:

- [ ] Add input validation and comprehensive error handling
- [ ] Implement pagination and sorting for movie lists
- [ ] Add search and filter capabilities (by genre, year, rating)
- [ ] Integrate with external movie APIs (OMDB, TMDB)
- [ ] Add unit and integration tests
- [ ] Implement authentication and authorization
- [ ] Add rate limiting for API endpoints
- [ ] Migrate to a persistent database (MySQL/PostgreSQL)
- [ ] Add caching for improved performance
- [ ] Implement API versioning

---

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

This project was created for educational purposes as part of the InternIntelligence internship program.

---

## 👤 Author

Developed by **Karim Fahmy** for **InternIntelligence** as part of the Java Development Internship program.

---

## 🤝 Acknowledgments

- **InternIntelligence** - For providing this internship opportunity and project requirements
- **Spring Boot Community** - For excellent documentation and tools
- **H2 Database** - For providing a lightweight database solution

---

**Happy Coding! 🚀**
