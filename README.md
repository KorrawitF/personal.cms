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
| Media | `/api/media` | Media records — DB metadata that map an id to an S3 object key |

Each resource exposes standard CRUD operations:

- `GET /api/{resource}` — list all
- `GET /api/{resource}/{id}` — get by id
- `POST /api/{resource}` — create
- `PUT /api/{resource}/{id}` — update
- `DELETE /api/{resource}/{id}` — delete

### Form submissions (mail)

`POST /api/forms/{slug}/submissions` validates the posted `fieldKey -> value` map against
that form's fields (required / max length) and emails the result to the site owner via
Outlook/Microsoft 365 SMTP, authenticating with OAuth2 (XOAUTH2) instead of a username/password.

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
| `MAIL_HOST` | `smtp-mail.outlook.com` | SMTP host used to send form-submission mail |
| `MAIL_PORT` | `587` | SMTP port |
| `MAIL_CLIENT_ID` | *(empty)* | Azure app registration's client (application) ID |
| `MAIL_REFRESH_TOKEN` | *(empty)* | OAuth2 refresh token for `MAIL_FROM`'s mailbox (see below) |
| `MAIL_FROM` | *(empty)* | Sender address on outgoing mail; must match the mailbox the refresh token was issued for |
| `MAIL_SCOPE` | `https://outlook.office.com/SMTP.Send offline_access` | OAuth2 scopes requested when refreshing the access token |
| `MAIL_TOKEN_URL` | `https://login.microsoftonline.com/consumers/oauth2/v2.0/token` | Microsoft identity platform token endpoint; use a tenant-specific or `organizations`/`common` URL for a Microsoft 365 work/school account instead of a personal Outlook.com account |
| `S3_BUCKET` | *(empty)* | S3 bucket that media files are read from |
| `S3_REGION` | `us-east-1` | AWS region of the bucket |
| `S3_ENDPOINT` | *(empty)* | Optional endpoint override, for S3-compatible services (e.g. MinIO) |
| `S3_ACCESS_KEY` | *(empty)* | Access key; if empty, falls back to the default AWS credentials chain |
| `S3_SECRET_KEY` | *(empty)* | Secret key, used together with `S3_ACCESS_KEY` |
| `S3_PATH_STYLE_ACCESS` | `false` | Enable path-style bucket access (usually required for S3-compatible services) |

`MAIL_CLIENT_ID` and `MAIL_REFRESH_TOKEN` come from a one-time OAuth2 setup, not a per-deploy
secret you type in: register a public client app in Azure (Microsoft Entra ID) for
`MAIL_FROM`'s mailbox, grant it the `SMTP.Send` and `offline_access` scopes, then run an
interactive device-code or authorization-code flow (e.g. via Postman) once to obtain a refresh
token. Store the resulting client ID and refresh token as `MAIL_CLIENT_ID` / `MAIL_REFRESH_TOKEN`;
the app exchanges the refresh token for a fresh access token automatically before each send.

### Media

The API never accepts a raw S3 key from a client. Media is modeled as a two-step, air-gapped
lookup: a `media_records` DB row maps an opaque numeric id to an S3 object key, and only the
server-side lookup of that row is allowed to read the key and reach into S3.

- `GET /api/media` / `GET /api/media/{id}` — list/get media record metadata (`key`, `fileName`, `contentType`, `size`)
- `POST /api/media` / `PUT /api/media/{id}` — register or update a record pointing at an S3 object
  (the object itself is expected to already exist in the bucket — this API does not upload to S3)
- `DELETE /api/media/{id}` — removes the DB record only; the underlying S3 object is untouched
- `GET /api/media/{id}/file` — resolves the id to its DB record, then streams the file bytes from
  S3 using the stored key
  - `200 OK` with the raw bytes and the record's `Content-Type` (falls back to the object's own content type)
  - `404 Not Found` if the record doesn't exist, or its key has no matching object in S3
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
