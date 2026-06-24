# Simple URL Shortener

A Spring Boot REST API that converts long URLs into short, shareable URLs and redirects users to the original destination while tracking click counts.

## Features

-  **URL Shortening** - Convert long URLs into short, memorable codes
-  **URL Redirection** - Redirect from short URL to original destination
-  **Click Tracking** - Track number of times each shortened URL is accessed
-  **REST APIs** - Clean, RESTful API endpoints
-  **H2 Database** - In-memory database for development (easily switchable to MySQL/PostgreSQL)
-  **Spring Boot** - Modern, production-ready Spring Boot application

## Technologies Used

- **Java 17** - Latest long-term support Java version
- **Spring Boot 3.5.13** - Spring framework for rapid development
- **Spring Data JPA** - ORM for database operations
- **H2 Database** - Embedded database for development
- **Apache Maven** - Build automation tool
- **Lombok** - Reduce boilerplate code
- **Spring Security** - Application security

## Project Structure

```
simple-url-shortener/
├── src/
│   ├── main/
│   │   ├── java/com/janavani/backend/
│   │   │   ├── BackendApplication.java
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── controller/
│   │   │   │   └── UrlController.java
│   │   │   ├── dto/
│   │   │   │   ├── ShortenUrlRequest.java
│   │   │   │   └── ShortenUrlResponse.java
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   └── ResourceNotFoundException.java
│   │   │   ├── model/
│   │   │   │   └── UrlMapping.java
│   │   │   ├── repository/
│   │   │   │   └── UrlMappingRepository.java
│   │   │   └── service/
│   │   │       └── UrlShorteningService.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/janavani/backend/
│           └── BackendApplicationTests.java
├── target/                          (ignore)
├── .mvn/
├── mvnw
├── mvnw.cmd
├── pom.xml
├── .gitignore
├── README.md
└── screenshots/
    ├── health-api.png
    ├── shorten-url.png
    └── url-info.png
```

## API Endpoints

| Method | Endpoint | Description | Status Code |
|--------|----------|-------------|------------|
| GET | `/api/urls/health` | Health check | 200 |
| POST | `/api/urls/shorten` | Create shortened URL | 201 |
| GET | `/api/urls/{shortCode}` | Redirect to original URL | 301 |
| GET | `/api/urls/{shortCode}/info` | Get URL information | 200 |

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/simple-url-shortener.git
   cd simple-url-shortener/backend
   ```

2. **Build the project**
   ```bash
   ./mvnw clean package
   ```

3. **Run the application**
   ```bash
   java -jar target/backend-0.0.1-SNAPSHOT.jar
   ```

   Or using Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the application**
   - API Base URL: `http://localhost:8080`
   - H2 Console: `http://localhost:8080/h2-console`

## Usage Examples

### 1. Health Check

```bash
curl http://localhost:8080/api/urls/health
```

**Response:**
```json
{
  "status": "UP",
  "message": "URL Shortener service is running"
}
```

### 2. Shorten a URL

```bash
curl -X POST http://localhost:8080/api/urls/shorten \
  -H "Content-Type: application/json" \
  -d '{"originalUrl":"https://www.github.com/microsoft/vscode"}'
```

**Request Body:**
```json
{
  "originalUrl": "https://www.github.com/microsoft/vscode"
}
```

**Response (HTTP 201):**
```json
{
  "shortCode": "VMYVUd",
  "shortUrl": "http://localhost:8080/api/urls/VMYVUd",
  "originalUrl": "https://www.github.com/microsoft/vscode",
  "clicks": 0,
  "createdAt": "2026-06-24 23:25:10"
}
```

### 3. Get URL Information

```bash
curl http://localhost:8080/api/urls/VMYVUd/info
```

**Response:**
```json
{
  "shortCode": "VMYVUd",
  "shortUrl": "http://localhost:8080/api/urls/VMYVUd",
  "originalUrl": "https://www.github.com/microsoft/vscode",
  "clicks": 2,
  "createdAt": "2026-06-24 23:25:10"
}
```

### 4. Access Shortened URL

```bash
curl -i http://localhost:8080/api/urls/VMYVUd
```

**Response:** Redirects to the original URL with HTTP 302 status and increments click count.

## How It Works

1. **URL Shortening**
   - User submits a long URL via POST request
   - Service generates a unique 6-character alphanumeric short code
   - Mapping stored in H2 database with creation timestamp
   - Short URL returned to user

2. **Redirection**
   - User accesses short URL via GET request
   - Service retrieves original URL from database
   - Increments click counter
   - Redirects user to original destination (HTTP 302)

3. **Click Tracking**
   - Every access to the short URL increments the clicks counter
   - User can query `/info` endpoint to view click statistics

## Database Schema

### UrlMapping Table

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique identifier |
| short_code | VARCHAR(255) | UNIQUE, NOT NULL | 6-character short code |
| original_url | VARCHAR(2048) | NOT NULL | Original long URL |
| created_at | TIMESTAMP | NOT NULL | Creation timestamp |
| clicks | INT | DEFAULT 0 | Click count |

## Error Handling

The application provides meaningful error responses:

### 404 - Not Found
```json
{
  "status": "error",
  "message": "URL not found with short code: invalid",
  "code": "NOT_FOUND"
}
```

### 400 - Validation Error
```json
{
  "status": "error",
  "message": "Validation failed",
  "code": "VALIDATION_ERROR",
  "errors": {
    "originalUrl": "URL cannot be blank"
  }
}
```

## Configuration

Application properties in `application.properties`:

```properties
server.port=8080
spring.datasource.url=jdbc:h2:mem:urlshortenerdb
spring.jpa.hibernate.ddl-auto=create-drop
spring.h2.console.enabled=true
logging.level.com.janavani.backend=DEBUG
```

## Future Enhancements

-  **User Authentication** - User accounts and API keys
-  **Custom Aliases** - Allow users to create custom short codes
-  **URL Expiration** - Set expiration dates on shortened URLs
-  **Analytics Dashboard** - Visual statistics and insights
-  **Docker Deployment** - Containerized deployment
-  **Persistent Database** - MySQL/PostgreSQL integration
-  **Frontend UI** - Web interface for URL shortening
-  **Bulk Operations** - Shorten multiple URLs at once
-  **QR Code Generation** - Generate QR codes for short URLs
-  **Custom Domain** - Use custom domain for shortened URLs

## Key Design Decisions

### 1. Short Code Generation
- **Algorithm**: Random 6-character alphanumeric code (a-z, A-Z, 0-9)
- **Uniqueness**: Database uniqueness constraint ensures no collisions
- **Retry Logic**: If collision detected, generates new code automatically

### 2. Database Choice
- **Development**: H2 in-memory database (no setup required)
- **Production**: Can easily switch to MySQL/PostgreSQL
- **ORM**: Spring Data JPA for database abstraction

### 3. API Design
- **REST Conventions**: Follows REST principles
- **Status Codes**: Proper HTTP status codes (200, 201, 302, 400, 404, 500)
- **JSON Format**: Consistent JSON request/response format
- **CORS Enabled**: Cross-Origin Resource Sharing enabled for frontend integration

### 4. Security
- **CORS Configuration**: Allows requests from all origins (configurable)
- **CSRF Protection**: Disabled for stateless API (can be enabled)
- **Input Validation**: URL validation at DTO level

## Testing

The project includes unit tests. Run tests with:

```bash
./mvnw test
```

## Building for Production

1. **Create fat JAR**
   ```bash
   ./mvnw clean package -DskipTests
   ```

2. **Run with production database**
   - Update `application-prod.properties`
   - Set environment variables for database connection
   - Run with: `java -jar backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod`

## Deployment Options

- **Docker**: Create Dockerfile for containerized deployment
- **Cloud Platforms**: Deploy to AWS, Azure, Google Cloud
- **Traditional Servers**: Deploy to any server with Java 17 installed

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Author

**Janavani** - Initial work

## Acknowledgments

- Spring Boot documentation
- Stack Overflow community
- GitHub documentation

---

## Screenshots

### Health Check API
![Health API Response](screenshots/health-api.png)

### URL Shortening
![Shorten URL Response](screenshots/shorten-url.png)

### URL Information
![URL Info Response](screenshots/url-info.png)

---

**Last Updated**: June 24, 2026

For more information, visit the [project repository](https://github.com/yourusername/simple-url-shortener).
