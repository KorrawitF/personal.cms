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
| Forms | `/api/forms` | Dynamic form definitions (labels, copy, nested fields) |
| Media | `/api/media/{key}` | Streams a media file (e.g. image) from S3 by object key |

Each resource exposes standard CRUD operations:

- `GET /api/{resource}` — list all
- `GET /api/{resource}/{id}` — get by id
- `POST /api/{resource}` — create
- `PUT /api/{resource}/{id}` — update
- `DELETE /api/{resource}/{id}` — delete

### Form submissions (mail)

`POST /api/forms/{slug}/submissions` validates the posted `fieldKey -> value` map against
that form's fields (required / max length) and emails the result to the site owner via SMTP.

- Body: `{ "fieldKey": "value", ... }`
- `201 Created`: `{ "id": "...", "to": "...", "deliveredAt": "..." }`
- `400 Bad Request`: `{ "errors": { "fieldKey": "message" } }` on validation failure
- `502 Bad Gateway` if the mail could not be sent

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
| `MAIL_HOST` | `smtp.gmail.com` | SMTP host used to send form-submission mail |
| `MAIL_PORT` | `587` | SMTP port |
| `MAIL_USERNAME` | *(empty)* | SMTP auth username |
| `MAIL_PASSWORD` | *(empty)* | SMTP auth password (an app password for Gmail) |
| `MAIL_FROM` | value of `MAIL_USERNAME` | Sender address on outgoing mail |
| `S3_BUCKET` | *(empty)* | S3 bucket that media files are read from |
| `S3_REGION` | `us-east-1` | AWS region of the bucket |
| `S3_ENDPOINT` | *(empty)* | Optional endpoint override, for S3-compatible services (e.g. MinIO) |
| `S3_ACCESS_KEY` | *(empty)* | Access key; if empty, falls back to the default AWS credentials chain |
| `S3_SECRET_KEY` | *(empty)* | Secret key, used together with `S3_ACCESS_KEY` |
| `S3_PATH_STYLE_ACCESS` | `false` | Enable path-style bucket access (usually required for S3-compatible services) |

### Media

`GET /api/media/{key}` streams a file straight from the configured S3 bucket by its object key
(e.g. `GET /api/media/images/hero.png` for a stored key of `images/hero.png`).

- `200 OK` with the raw file bytes and its stored `Content-Type`
- `404 Not Found` if no object exists under that key
- `502 Bad Gateway` if S3 could not be reached

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
