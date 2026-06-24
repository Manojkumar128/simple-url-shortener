# Contributing to Simple URL Shortener

Thank you for your interest in contributing to the Simple URL Shortener project! This document provides guidelines and instructions for contributing.

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Git
- GitHub account

### Fork and Clone
1. Fork the repository on GitHub
2. Clone your fork:
   ```bash
   git clone https://github.com/YOUR_USERNAME/simple-url-shortener.git
   cd simple-url-shortener/backend
   ```
3. Add upstream remote:
   ```bash
   git remote add upstream https://github.com/ORIGINAL_OWNER/simple-url-shortener.git
   ```

---

## 🔄 Contribution Workflow

### 1. Create a Feature Branch
```bash
git checkout -b feature/your-feature-name
```

Branch naming convention:
- `feature/` for new features
- `fix/` for bug fixes
- `docs/` for documentation
- `refactor/` for code refactoring
- `test/` for tests

### 2. Make Your Changes
- Write clean, readable code
- Follow existing code style
- Add tests for new features
- Update documentation if needed

### 3. Commit Your Changes
```bash
git add .
git commit -m "feat: add your feature description"
```

#### Commit Message Format
```
<type>: <description>

<body>

<footer>
```

**Types**:
- `feat`: A new feature
- `fix`: A bug fix
- `docs`: Documentation only change
- `style`: Code style changes (formatting, missing semicolons, etc.)
- `refactor`: Code refactoring without feature changes
- `perf`: Performance improvements
- `test`: Adding or updating tests
- `chore`: Changes to build process or dependencies

**Example**:
```
feat: add custom URL alias support

Allow users to create custom short codes instead of auto-generated ones.
This feature includes validation and collision detection.

Fixes #42
```

### 4. Push to Your Fork
```bash
git push origin feature/your-feature-name
```

### 5. Create a Pull Request
1. Go to GitHub repository
2. Click "New Pull Request"
3. Select your branch
4. Add description of changes
5. Reference related issues (#42)
6. Submit PR

---

## 📝 Pull Request Template

When creating a PR, use this template:

```markdown
## Description
Brief description of the changes.

## Type of Change
- [ ] New feature
- [ ] Bug fix
- [ ] Documentation update
- [ ] Code refactoring

## Related Issue
Fixes #(issue number)

## How to Test
Steps to test the changes:
1. 
2. 
3. 

## Testing Checklist
- [ ] Tests pass locally
- [ ] No new warnings
- [ ] Documentation updated
- [ ] Code reviewed by myself

## Screenshots (if applicable)
Add screenshots for UI changes.
```

---

## 📋 Code Style Guidelines

### Java Naming Conventions
```java
// Classes: PascalCase
public class UrlShorteningService {}

// Methods/Variables: camelCase
public String generateShortCode() {}
private String shortUrl = "";

// Constants: UPPER_SNAKE_CASE
private static final int SHORT_CODE_LENGTH = 6;
```

### Formatting
- **Indentation**: 4 spaces (or 1 tab)
- **Line Length**: Max 120 characters
- **Braces**: Opening on same line
- **Imports**: Sort and remove unused imports

### Example Class Structure
```java
package com.janavani.backend.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyService {
    
    // Constants
    private static final String CONSTANT = "value";
    
    // Dependency injection
    private final MyRepository repository;
    
    // Constructors (if needed)
    
    // Public methods
    public void publicMethod() {}
    
    // Private methods
    private void privateMethod() {}
}
```

### Comments
```java
// Use meaningful comments
// Explain WHY, not WHAT (code shows what)

/**
 * Generates a unique short code for URL shortening.
 * 
 * @return A unique 6-character alphanumeric code
 */
public String generateShortCode() {
    // Implementation
}
```

---

## 🧪 Testing Requirements

### Unit Tests
- Write tests for new features
- Maintain test coverage > 70%
- Use meaningful test names

### Test File Location
```
src/test/java/com/janavani/backend/...
```

### Running Tests
```bash
./mvnw test
```

### Test Example
```java
@SpringBootTest
public class UrlShorteningServiceTest {
    
    @Autowired
    private UrlShorteningService service;
    
    @Test
    public void testGenerateShortCode() {
        // Arrange
        String originalUrl = "https://example.com";
        
        // Act
        ShortenUrlResponse response = service.shortenUrl(
            new ShortenUrlRequest(originalUrl), 
            "http://localhost:8080"
        );
        
        // Assert
        assertNotNull(response);
        assertEquals(6, response.getShortCode().length());
        assertEquals(originalUrl, response.getOriginalUrl());
    }
}
```

---

## 📚 Documentation Updates

### When to Update Docs
- Adding new API endpoint → Update README.md & API_RESPONSES.md
- Changing configuration → Update DEPLOYMENT.md
- New feature → Update README.md & PROJECT_SUMMARY.md
- Bug fix → Consider updating relevant doc

### Documentation Files
- `README.md` - Main documentation
- `API_RESPONSES.md` - API examples
- `DEPLOYMENT.md` - Deployment guide
- `PROJECT_SUMMARY.md` - Project status
- `CONTRIBUTING.md` - This file

---

## 🐛 Bug Reports

### How to Report Bugs
1. Check existing issues first
2. Use "Bug" label when creating issue
3. Include:
   - Description of the bug
   - Steps to reproduce
   - Expected behavior
   - Actual behavior
   - Screenshots (if applicable)
   - Environment (Java version, OS, etc.)

### Example Bug Report
```markdown
## Description
API returns 500 error when URL contains special characters

## Steps to Reproduce
1. Call POST /api/urls/shorten
2. Pass URL: `https://example.com/search?q=hello&world`
3. Observe error response

## Expected
Should return 201 with shortened URL

## Actual
Returns 500 Internal Server Error

## Environment
- Java: 17
- OS: Windows 10
```

---

## ✨ Feature Requests

### How to Request Features
1. Check existing issues first
2. Use "Enhancement" label
3. Include:
   - Feature description
   - Use case / motivation
   - Proposed implementation (optional)
   - Examples (optional)

### Example Feature Request
```markdown
## Feature: Custom URL Aliases

### Description
Allow users to create custom short codes instead of auto-generated ones.

### Use Case
Marketing campaigns where short URLs need to match brand messaging.

### Example
- Long URL: `https://my-company.com/products/widget`
- Custom alias: `my-widget`
- Short URL: `http://short.url/my-widget`

### Implementation Notes
- Add alias field to UrlMapping entity
- Add alias parameter to POST /api/urls/shorten
- Validate alias format (alphanumeric + dash)
- Check for duplicate aliases
```

---

## 🔍 Review Process

### What Reviewers Look For
- ✅ Code follows style guidelines
- ✅ Sufficient test coverage
- ✅ Documentation is updated
- ✅ No breaking changes
- ✅ Performance is acceptable
- ✅ Security best practices
- ✅ Clear commit messages

### Getting a Review
1. Self-review your code first
2. Submit PR with clear description
3. Link related issues
4. Wait for maintainer review
5. Address feedback
6. Request re-review

---

## 🚨 Code Review Checklist

Before submitting PR, verify:

- [ ] Code compiles without errors
- [ ] All tests pass: `./mvnw test`
- [ ] No code style violations
- [ ] New tests written and passing
- [ ] Documentation updated
- [ ] No commented-out code
- [ ] No debug statements
- [ ] No hardcoded values
- [ ] Error handling is proper
- [ ] Follows Spring Boot patterns

---

## 📦 Project Structure

Understanding the project structure:

```
backend/
├── src/main/java/com/janavani/backend/
│   ├── controller/       # REST endpoints
│   ├── service/          # Business logic
│   ├── repository/       # Database access
│   ├── model/            # Entity classes
│   ├── dto/              # Data transfer objects
│   ├── config/           # Configuration classes
│   ├── exception/        # Exception classes
│   └── BackendApplication.java  # Entry point
├── src/main/resources/
│   └── application.properties    # Configuration
├── src/test/java/
│   └── com/janavani/backend/     # Tests
└── pom.xml              # Maven configuration
```

### Adding New Features
1. **Entity** → `model/`
2. **Repository** → `repository/`
3. **Service** → `service/`
4. **Controller** → `controller/`
5. **DTOs** → `dto/`
6. **Tests** → `src/test/`

---

## 🎓 Development Tips

### Build Commands
```bash
# Full build with tests
./mvnw clean package

# Skip tests (faster)
./mvnw clean package -DskipTests

# Run application
./mvnw spring-boot:run

# Run only tests
./mvnw test

# Check code style
./mvnw checkstyle:check
```

### Debugging
```bash
# Run with debug enabled
./mvnw spring-boot:run -Dspring-boot.run.arguments="--debug"

# Or use IDE debugger
# Set breakpoints and run in debug mode
```

### Database Access
```
H2 Console: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:urlshortenerdb
Username: sa
Password: (empty)
```

---

## 📞 Getting Help

### Questions?
1. Check existing documentation
2. Look at existing code examples
3. Search GitHub issues
4. Comment on related PR/issue
5. Reach out to maintainers

### Contact
- GitHub Issues: For bug reports & feature requests
- PR Comments: For code review discussions
- Discussions: For general questions

---

## 🎉 Thank You

Thank you for contributing to this project! Your efforts help make it better for everyone.

### Recognition
Contributors will be recognized in:
- CONTRIBUTORS.md file
- GitHub contributors page
- Release notes (for significant contributions)

---

## 📋 Common Contribution Types

### 1. Bug Fix
```bash
git checkout -b fix/url-validation-bug
# Fix the bug
# Add test case
# Update docs if needed
git push origin fix/url-validation-bug
```

### 2. New Feature
```bash
git checkout -b feature/api-key-support
# Implement feature
# Add tests
# Update documentation
git push origin feature/api-key-support
```

### 3. Documentation
```bash
git checkout -b docs/add-docker-guide
# Add documentation
git push origin docs/add-docker-guide
```

### 4. Code Refactoring
```bash
git checkout -b refactor/service-optimization
# Refactor code
# Ensure tests still pass
# Update if needed
git push origin refactor/service-optimization
```

---

## ⚖️ Code of Conduct

- Be respectful and inclusive
- Avoid offensive language
- Welcome diverse perspectives
- Provide constructive feedback
- Focus on the code, not the person

---

## 📜 License

By contributing, you agree that your contributions will be licensed under the MIT License.

---

**Happy contributing!** 🚀

Questions? Open an issue or start a discussion on GitHub.
