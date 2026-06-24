## Git Repository Setup Guide

### Project: Simple URL Shortener

---

## Files to Include in Repository

### ✅ Include These Files:

```
backend/
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
├── .mvn/                          (Maven wrapper files)
├── mvnw                           (Maven wrapper for Unix/Mac)
├── mvnw.cmd                       (Maven wrapper for Windows)
├── pom.xml                        (Maven configuration)
├── .gitignore                     (Git ignore rules)
├── README.md                      (Project documentation)
├── API_RESPONSES.md               (API response examples)
├── CONTRIBUTING.md                (Contribution guidelines)
└── screenshots/
    ├── health-api.png
    ├── shorten-url.png
    └── url-info.png
```

### ❌ Exclude These Files (via .gitignore):

```
# Build Output
target/
bin/
out/
*.class

# IDE Files
.idea/
.vscode/
.settings/
.project
.classpath
*.iml

# Logs
*.log
logs/

# OS Files
.DS_Store
Thumbs.db
```

---

## Git Commands for Initial Setup

### 1. Initialize Git Repository (if not already done)
```bash
cd simple-url-shortener
git init
```

### 2. Add All Files
```bash
git add .
```

### 3. Create Initial Commit
```bash
git commit -m "Initial commit - Simple URL Shortener backend with Spring Boot"
```

### 4. Create GitHub Repository
- Go to https://github.com/new
- Repository name: `simple-url-shortener`
- Description: "A Spring Boot REST API that converts long URLs into short, shareable URLs with click tracking"
- Choose: Public or Private
- Do NOT initialize with README (we already have one)

### 5. Add Remote and Push
```bash
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/simple-url-shortener.git
git push -u origin main
```

---

## Commit Message Convention

Use conventional commit messages:

```
feat: Add URL shortening feature
fix: Fix click counter increment bug
docs: Update README with API examples
test: Add unit tests for UrlShorteningService
refactor: Extract configuration to separate class
chore: Update Maven dependencies
```

---

## Future Commits

### After Building New Features:
```bash
git add .
git commit -m "feat: Add custom alias support"
git push origin main
```

### Branch Strategy for Larger Features:
```bash
# Create feature branch
git checkout -b feature/custom-aliases

# Make changes and commit
git add .
git commit -m "feat: Add custom URL aliases"

# Push to GitHub
git push origin feature/custom-aliases

# Create Pull Request on GitHub
# After review, merge to main
```

---

## Recommended GitHub Settings

### 1. Repository Settings
- Enable "Require a pull request review before merging"
- Enable "Automatically delete head branches"
- Set default branch to `main`

### 2. Add Topics (Tags)
- `spring-boot`
- `url-shortener`
- `rest-api`
- `java`
- `maven`
- `h2-database`

### 3. Add Repository Topics
Helps with GitHub discovery and categorization.

---

## Additional Files to Consider

### 1. CONTRIBUTING.md
Guidelines for contributors:
```markdown
# Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Commit with meaningful messages
5. Push to your fork
6. Create a Pull Request
```

### 2. LICENSE
Add an MIT License file:
```
MIT License

Copyright (c) 2026 Janavani

Permission is hereby granted, free of charge...
```

### 3. CHANGELOG.md
Track version changes:
```markdown
# Changelog

## [1.0.0] - 2026-06-24
### Added
- Initial release
- URL shortening API
- Click tracking
- H2 database integration
```

### 4. .github/workflows/maven.yml
CI/CD Pipeline (GitHub Actions):
```yaml
name: Java CI with Maven

on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
    - uses: actions/setup-java@v3
      with:
        java-version: '17'
    - run: mvn clean package
```

---

## Verification Before Pushing

### ✅ Check Before Git Push:

1. **Verify .gitignore is correct**
   ```bash
   git status
   ```
   Ensure `target/`, `.idea/`, and other build files are not listed.

2. **Check commit message**
   ```bash
   git log --oneline -1
   ```

3. **Verify file structure**
   ```bash
   git ls-files
   ```

4. **Build project successfully**
   ```bash
   ./mvnw clean package -DskipTests
   ```

5. **No sensitive data**
   - No API keys
   - No passwords
   - No personal information

---

## After Pushing to GitHub

### 1. Verify on GitHub
- Go to your repository
- Check that all files are visible
- Verify README renders correctly

### 2. Update About Section
- Add description: "Spring Boot URL Shortener REST API"
- Add website URL if available
- Add topics

### 3. Enable GitHub Pages (Optional)
- Create `docs/` folder
- Add project documentation
- Enable GitHub Pages in Settings

### 4. Create Release (Optional)
```bash
git tag -a v1.0.0 -m "Initial release"
git push origin v1.0.0
```

---

## .gitignore Content Verification

Your `.gitignore` should contain:

```
# Build
target/
bin/
out/
*.class

# IDE
.idea/
.vscode/
.settings/
.project
.classpath
*.iml

# Logs
*.log
logs/

# OS
.DS_Store
Thumbs.db

# Maven wrapper (optional, include for reproducible builds)
# .mvn/wrapper/maven-wrapper.jar
```

---

## Quick Start for First Push

```bash
# Navigate to project
cd simple-url-shortener

# Initialize if needed
git init

# Add all files
git add .

# Create commit
git commit -m "Initial commit - Simple URL Shortener backend with Spring Boot REST API"

# Add remote
git remote add origin https://github.com/YOUR_USERNAME/simple-url-shortener.git

# Set main branch and push
git branch -M main
git push -u origin main
```

---

## Troubleshooting

### Issue: Large files in target/
**Solution**: Run `git clean -fd` and ensure `.gitignore` is working

### Issue: Files not uploading
**Solution**: Check `git status` - files may be in `.gitignore`

### Issue: Push rejected
**Solution**: Pull first: `git pull origin main`, resolve conflicts, then push

---

## Project Statistics

After pushing, GitHub shows:

- **Language**: Java (100%)
- **Lines of Code**: ~500+ (implementation files)
- **Commits**: Will track over time
- **Contributors**: You (initially)
- **License**: MIT (once added)

---

**Ready to push!** 🚀

Follow the commands above to get your Simple URL Shortener project on GitHub.
