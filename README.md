# personal.cms

A lightweight headless CMS backend for managing personal portfolio content — projects, skill domains, and work experience — built with Spring Boot and plain JDBC/Hibernate persistence.

## Tech Stack

- **Java 17**
- **Spring Boot 4.1.1** (Web MVC)
- **Hibernate ORM 7** (session-based, configured manually — no Spring Data JPA)
- **PostgreSQL** with **HikariCP** connection pooling

## Architecture

The codebase follows a layered, hexagonal-ish structure that separates domain models from persistence models:

```
controller/     REST endpoints (Spring MVC)
service/        Business logic
domain/
  entity/       Domain model classes used by controllers/services
  repository/   Repository interfaces (domain-facing contracts)
infrastructure/
  persistence/  Hibernate implementations of the repository interfaces
    model/      JPA/Hibernate-mapped entities (persistence models)
    mapper/     Converters between domain entities and persistence models
config/         DataSource (HikariCP) and Hibernate SessionFactory configuration
error/          Centralized exception handling
```

Domain entities and persistence models are kept separate on purpose, with mapper classes translating between the two layers.

## Features

| Resource | Endpoint | Description |
|---|---|---|
| Projects | `/api/projects` | Portfolio projects (tech stack, highlights, links, status, dates) |
| Skill Domains | `/api/skill-domains` | Grouped skill categories, each with nested skills |
| Work Experiences | `/api/work-experiences` | Job history (title, company, tech stack, dates) |

Each resource exposes standard CRUD operations:

- `GET /api/{resource}` — list all
- `GET /api/{resource}/{id}` — get by id
- `POST /api/{resource}` — create
- `PUT /api/{resource}/{id}` — update
- `DELETE /api/{resource}/{id}` — delete

A Postman collection is available under [`postman/`](postman/personal-cms.postman_collection.json) for exploring and testing the API.

## Getting Started

### Prerequisites

- Java 17+
- PostgreSQL instance

### Configuration

The application reads database settings from environment variables (or a `.env` file at the project root):

| Variable | Default | Description |
|---|---|---|
| `DATABASE_HOST` | `localhost` | PostgreSQL host |
| `DATABASE_PORT` | `5432` | PostgreSQL port |
| `DATABASE_USER` | `postgres` | Database username |
| `DATABASE_PASS` | *(empty)* | Database password |
| `DATABASE_NAME` | `cms` | Database name |
| `DATABASE_MODE` | `disable` | SSL mode |
| `DATABASE_SSL` | `false` | Enable SSL |

### Run

```bash
./mvnw spring-boot:run
```

### Build

```bash
./mvnw clean package
```

### Test

```bash
./mvnw test
```

By default the app starts on `http://localhost:8080`.
