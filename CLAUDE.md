# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Stack

- Java 17, Spring Boot 4.0.6, Gradle (Groovy DSL)
- Group: `app.iandis`, base package: `com.example.demo`

## Commands

```bash
# Build
./gradlew build

# Run app
./gradlew bootRun

# Run all tests
./gradlew test

# Run single test class
./gradlew test --tests "com.example.demo.DemoApplicationTests"

# Run single test method
./gradlew test --tests "com.example.demo.DemoApplicationTests.contextLoads"
```

## Structure

Minimal Spring Boot scaffold — one `@SpringBootApplication` entry point at `src/main/java/com/example/demo/DemoApplication.java`. No controllers, services, or repositories yet. `application.properties` has only `spring.application.name=demo`.

New features go under `src/main/java/com/example/demo/`. Tests mirror that path under `src/test/`.