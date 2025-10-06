## QuoraApp – Reactive Spring Boot Backend

A lightweight Quora-like backend built with Spring Boot WebFlux and Reactive MongoDB. It supports creating and browsing questions, managing tags, and full CRUD for answers. The app also emits and consumes Kafka events to track question view counts.

### Features
- Reactive, non-blocking stack (WebFlux + Reactive MongoDB)
- Questions: create, read, list with cursor, search, delete
- Tags: associate questions with tags
- Answers: full CRUD using DTOs
- Kafka integration:
  - Producer sends `ViewCountEvent` when a question is fetched
  - Consumer updates question view counts from `view-count-topic`

### Tech Stack
- Java 21, Spring Boot 3.5.x
- Spring WebFlux, Spring Data MongoDB Reactive
- Spring for Apache Kafka
- MongoDB (local)
- Docker Compose (Kafka in KRaft mode)
- Gradle

## Getting Started

### Prerequisites
- Java 21 (e.g., Amazon Corretto 21)
- Docker Desktop (for Kafka)
- MongoDB running locally on `localhost:27017` (database: `quora_db`)

### Clone
```bash
git clone <your-repo-url>
cd QuoraApp
```

### Start Kafka (Docker)
```bash
docker compose -f /Users/sharvansamala/learn/QuoraApp/docker-compose.yml up -d
```

Create the topic (if auto-creation is disabled):
```bash
docker exec -it kafka kafka-topics.sh --create \
  --topic view-count-topic \
  --bootstrap-server localhost:9092 \
  --partitions 1 \
  --replication-factor 1
```

### Configuration
Application config lives in `src/main/resources/application.yml`.

Kafka bootstrap servers are set via:
```yaml
kafka:
  bootstrap-servers: localhost:9092
```

MongoDB (default):
```yaml
spring:
  data:
    mongodb:
      host: localhost
      port: 27017
      database: quora_db
      auto-index-creation: true
```

### Run the App
```bash
./gradlew bootRun
```

## API Overview

### Questions
- POST `/api/questions` – create question
- GET `/api/questions/{id}` – get by id (publishes a view-count event)
- GET `/api/questions?cursor={cursor}&size={size}` – list with cursor support
- GET `/api/questions/search?query={q}&page={p}&size={s}` – search by title/content
- DELETE `/api/questions/{id}` – delete question

### Answers
- POST `/answers` – create answer
- GET `/answers/{id}` – get by id
- GET `/answers?questionId={qid}` – list answers for a question
- PUT `/answers/{id}` – update answer content
- DELETE `/answers/{id}` – delete answer

## Sample Requests

Create a question:
```bash
curl -X POST http://localhost:8080/api/questions \
  -H "Content-Type: application/json" \
  -d '{
    "title":"Test question title",
    "content":"This is a sample content that is certainly long enough.",
    "tags":[{"name":"tag1"},{"name":"tag2"}]
  }'
```

Create an answer (replace `<qid>`):
```bash
curl -X POST http://localhost:8080/answers \
  -H "Content-Type: application/json" \
  -d '{
    "content":"This is a valid answer content",
    "questionId":"<qid>"
  }'
```

## Troubleshooting
- Port 8080 in use: stop the existing process or run with `--server.port=8081`.
- Kafka not reachable: ensure Docker stack is up and `kafka.bootstrap-servers` points to `localhost:9092`.
- MongoDB connection errors: ensure MongoDB is running on `localhost:27017`.
- Null views on questions: initialize views to 0 or guard against null before incrementing.

## Notes
- This project uses reactive types (`Mono`, `Flux`). Avoid blocking calls in services and controllers.
- Topic used by the app: `view-count-topic`.
