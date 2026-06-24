# Quick Start Guide - Simple URL Shortener

## 🚀 Quick Start (5 minutes)

### Prerequisites
- Java 17+
- Maven (or use included mvnw)

### 1. Build the Project
```bash
cd backend
./mvnw clean package -DskipTests
```

### 2. Run the Application
```bash
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

Server starts on: **http://localhost:8080**

### 3. Test the API

#### Health Check
```bash
curl http://localhost:8080/api/urls/health
```

#### Shorten a URL
```bash
curl -X POST http://localhost:8080/api/urls/shorten \
  -H "Content-Type: application/json" \
  -d '{"originalUrl":"https://www.google.com"}'
```

**Response:**
```json
{
  "shortCode": "abc123",
  "shortUrl": "http://localhost:8080/api/urls/abc123",
  "originalUrl": "https://www.google.com",
  "clicks": 0,
  "createdAt": "2026-06-24 23:25:10"
}
```

#### Get URL Info
```bash
curl http://localhost:8080/api/urls/abc123/info
```

#### Redirect to Original URL
```bash
curl -i http://localhost:8080/api/urls/abc123
```

---

## 📁 Project Structure

```
backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/janavani/backend/
│   │   │       ├── BackendApplication.java (Entry point)
│   │   │       ├── config/ (Configuration)
│   │   │       ├── controller/ (REST endpoints)
│   │   │       ├── service/ (Business logic)
│   │   │       ├── repository/ (Database)
│   │   │       ├── model/ (Entity classes)
│   │   │       ├── dto/ (Data transfer objects)
│   │   │       └── exception/ (Error handling)
│   │   └── resources/
│   │       └── application.properties (Configuration)
│   └── test/
│       └── java/ (Unit tests)
├── pom.xml (Maven configuration)
└── mvnw (Maven wrapper for Unix/Mac)
```

---

## 🔧 Configuration

Edit `src/main/resources/application.properties`:

```properties
# Server
server.port=8080

# Database (H2 - Development)
spring.datasource.url=jdbc:h2:mem:urlshortenerdb

# Database (MySQL - Production)
# spring.datasource.url=jdbc:mysql://localhost:3306/url_shortener
# spring.datasource.username=root
# spring.datasource.password=password
# spring.jpa.hibernate.ddl-auto=update

# Logging
logging.level.com.janavani.backend=DEBUG
```

---

## 📊 API Endpoints Summary

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/urls/health` | Health check |
| POST | `/api/urls/shorten` | Shorten URL |
| GET | `/api/urls/{code}` | Redirect |
| GET | `/api/urls/{code}/info` | URL info |

---

## 🗄️ Database Console (Development Only)

Access H2 Console:
- **URL**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:urlshortenerdb`
- **Username**: `sa`
- **Password**: (empty)

---

## 🐛 Troubleshooting

### Port Already in Use
```bash
# Use different port
java -jar target/backend-0.0.1-SNAPSHOT.jar --server.port=9090
```

### Build Fails
```bash
# Clean and rebuild
./mvnw clean package
```

### Database Issues
```bash
# Reset database (H2 will recreate on startup)
# Just restart the application
```

---

## 📦 Built With

- **Java 17** - Programming language
- **Spring Boot 3.5.13** - Framework
- **Spring Data JPA** - ORM
- **H2 Database** - Embedded database
- **Maven** - Build tool
- **Lombok** - Code generation

---

## 🚢 Deployment

### Docker
```dockerfile
FROM eclipse-temurin:17-jdk
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

Build:
```bash
docker build -t url-shortener:1.0 .
docker run -p 8080:8080 url-shortener:1.0
```

### Cloud Deployment
- **AWS**: Deploy to EC2 or Elastic Beanstalk
- **Azure**: Deploy to App Service
- **Google Cloud**: Deploy to Cloud Run
- **Heroku**: Push to Heroku dyno

---

## 📝 Development Commands

### Run in Development Mode
```bash
./mvnw spring-boot:run
```

### Run Tests
```bash
./mvnw test
```

### Build Jar
```bash
./mvnw package
```

### Clean Build
```bash
./mvnw clean package
```

### Skip Tests
```bash
./mvnw package -DskipTests
```

---

## 🎯 Next Steps

1. ✅ Build and run the application
2. ✅ Test API endpoints
3. ✅ Explore H2 database console
4. ✅ Customize configuration
5. ✅ Add frontend UI
6. ✅ Deploy to cloud
7. ✅ Add more features

---

## 📚 Documentation Files

- **README.md** - Full project documentation
- **API_RESPONSES.md** - API response examples
- **GIT_SETUP.md** - GitHub setup guide
- **DEPLOYMENT.md** - Deployment guide (this file)

---

## 🆘 Need Help?

1. Check API_RESPONSES.md for example requests/responses
2. Review README.md for detailed documentation
3. Check application logs: `./mvnw spring-boot:run`
4. Use H2 console to inspect database

---

**Happy coding! 🎉**
