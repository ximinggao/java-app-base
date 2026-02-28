# AGENTS.md

This file contains conventions and commands for agentic coding agents working in this repository.

## Project Overview

Spring Boot 4.0.3 application with OpenTelemetry observability integration.
- **Language**: Java 25
- **Build System**: Gradle
- **Package**: `com.cgp.example.demo`
- **Key Dependencies**: Spring Boot OpenTelemetry, Lombok, JUnit 5

## Build Commands

### Essential Commands
```bash
# Build the project
./gradlew build

# Clean and build
./gradlew clean build

# Run the application (development)
./gradlew bootRun

# Run all tests
./gradlew test

# Clean build artifacts
./gradlew clean
```

### Running Tests

```bash
# Run all tests
./gradlew test

# Run tests in a specific class
./gradlew test --tests DemoApplicationTests

# Run a single test method
./gradlew test --tests DemoApplicationTests.contextLoads

# Run tests with detailed output
./gradlew test --info

# Run tests and continue on failures
./gradlew test --continue
```

### Docker Compose Support
```bash
# Start observability stack (Grafana LGTM)
docker compose up

# Start in detached mode
docker compose up -d

# Stop services
docker compose down
```

**Note**: Grafana LGTM is exposed on port 3000, with OpenTelemetry receiver ports 4317 (gRPC) and 4318 (HTTP).

## Code Style Guidelines

### Imports
- Use standard Java imports
- Sort imports alphabetically
- Use fully qualified imports for clarity
- Prefer `java.*` and `org.*` over `com.*` where applicable

### Formatting
- **Indentation**: Use tabs (project convention observed in existing files)
- **Bracing Style**: K&R style (opening brace on same line as statement)
- **Line Length**: Recommended 120 characters (soft limit)
- **Blank Lines**: One blank line between methods, two between class members

### Naming Conventions
- **Classes**: PascalCase (e.g., `DemoApplication`, `UserService`)
- **Methods**: camelCase (e.g., `contextLoads`, `getUserById`)
- **Variables**: camelCase (e.g., `userName`, `isActive`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `MAX_RETRIES`, `DEFAULT_TIMEOUT`)
- **Packages**: lowercase with dots (e.g., `com.cgp.example.demo`)
- **Test Classes**: `{TargetClass}Tests` (e.g., `DemoApplicationTests`)

### Type Guidelines
- **Java Toolchain**: Java 25 enforced via Gradle toolchain
- **Strong Typing**: Avoid raw types, use generics with proper bounds
- **Null Safety**: Consider using `@NonNull`/`@Nullable` annotations where appropriate
- **Optional**: Use `Optional<T>` for return values that may be absent, not for method parameters

### Annotations
- **Lombok**: Use for boilerplate reduction (`@Data`, `@Builder`, `@RequiredArgsConstructor`)
- **Spring**: Standard Spring Boot annotations (`@SpringBootApplication`, `@RestController`, `@Service`, `@Repository`)
- **Testing**: JUnit 5 annotations (`@Test`, `@BeforeEach`, `@ParameterizedTest`)

### Error Handling
- **Exceptions**: Use custom exception classes extending `RuntimeException` for business logic errors
- **Logging**: Use SLF4J with `@Slf4j` annotation (Lombok)
- **Validation**: Use Bean Validation (`@Valid`, `@NotNull`, `@Size`) for API inputs
- **Try-Catch**: Prefer specific exception catching; avoid broad `catch (Exception e)`

### Testing Guidelines
- **Framework**: JUnit 5 (Jupiter)
- **Spring Test**: Use `@SpringBootTest` for integration tests
- **Test Naming**: Descriptive method names indicating what is tested (e.g., `contextLoads`, `shouldCreateUser`)
- **Assertions**: Use JUnit 5 `Assertions` class or AssertJ if added to dependencies
- **Test Isolation**: Each test should be independent; use `@BeforeEach` for setup

### Project Structure
The project structure aligns with the [Hexagonal architecture](https://alistair.cockburn.us/hexagonal-architecture):
```
src/
├── main/
│   ├── java/com/cgp/example/demo/    # Application code
│   │   ├── adapter                   # Adapters
│   │   ├── application               # Application logic
│   │   │   ├── port                  # Ports
│   │   │   └── service               # Application services
│   │   └── domain                    # Domain logic
│   │       ├── model                 # Domain models
│   │       └── service               # Domain services
│   └── resources/
│       └── application.yaml          # Configuration
└── test/
    ├── java/com/cgp/example/demo/    # Test code
    └── resources/                    # Test resources
```

### Configuration
- **Application Config**: `src/main/resources/application.yaml`
- **Environment**: Spring Boot supports profile-specific configs (e.g., `application-dev.yaml`)
- **Observability**: OpenTelemetry auto-configured via Spring Boot starter

### Dependencies Management
- Add dependencies in `build.gradle` under `dependencies` block
- Prefer Spring Boot starters (e.g., `spring-boot-starter-webmvc`)
- Development dependencies use `developmentOnly` scope
- Use `annotationProcessor` for annotation processors (Lombok)

## Additional Notes

- **Java Version**: Java 25 (enforced via Gradle toolchain)
- **Git Hooks**: No pre-commit hooks configured
- **CI/CD**: No CI configuration detected
- **Code Quality**: No Checkstyle, SpotBugs, or PMD configured
- **Documentation**: HELP.md contains Spring Boot reference links
- **IDE**: Project supports IntelliJ IDEA, Eclipse, VS Code (see .gitignore)

## Development Workflow

1. Make changes to source code
2. Run `./gradlew test` to verify
3. Run `./gradlew build` to package
4. Run `./gradlew bootRun` for local development
5. Start observability stack with `docker compose up` for metrics/tracing

When adding new features:
- Follow package structure: `com.cgp.example.demo.{feature}`
- Create corresponding test class: `{Feature}Tests`
- Update `application.yaml` for new configuration properties
- Add dependencies to `build.gradle` with appropriate scope
