## API Response Examples

### 1. Health Check Endpoint
**Request:**
```
GET http://localhost:8080/api/urls/health
```

**Response (HTTP 200):**
```json
{
  "message": "URL Shortener service is running",
  "status": "UP"
}
```

---

### 2. URL Shortening Endpoint
**Request:**
```
POST http://localhost:8080/api/urls/shorten
Content-Type: application/json

{
  "originalUrl": "https://github.com/microsoft/vscode"
}
```

**Response (HTTP 201):**
```json
{
  "shortCode": "UVig8c",
  "shortUrl": "http://localhost:8080/api/urls/UVig8c",
  "originalUrl": "https://github.com/microsoft/vscode",
  "clicks": 0,
  "createdAt": "2026-06-24 23:43:51"
}
```

---

### 3. URL Information Endpoint
**Request:**
```
GET http://localhost:8080/api/urls/UVig8c/info
```

**Response (HTTP 200):**
```json
{
  "shortCode": "UVig8c",
  "shortUrl": "http://localhost:8080/api/urls/UVig8c",
  "originalUrl": "https://github.com/microsoft/vscode",
  "clicks": 0,
  "createdAt": "2026-06-24 23:43:51"
}
```

---

### 4. URL Redirect Endpoint
**Request:**
```
GET http://localhost:8080/api/urls/UVig8c
```

**Response (HTTP 302):**
Redirects to: `https://github.com/microsoft/vscode`
Location Header: `https://github.com/microsoft/vscode`
Click count incremented in database.

---

### 5. Error Response - URL Not Found
**Request:**
```
GET http://localhost:8080/api/urls/invalid123/info
```

**Response (HTTP 404):**
```json
{
  "status": "error",
  "message": "URL not found with short code: invalid123",
  "code": "NOT_FOUND"
}
```

---

### 6. Error Response - Validation Error
**Request:**
```
POST http://localhost:8080/api/urls/shorten
Content-Type: application/json

{
  "originalUrl": ""
}
```

**Response (HTTP 400):**
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
