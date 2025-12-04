## QuoraApp – Reactive Spring Boot Backend

A lightweight Quora-like backend built with Spring Boot WebFlux and Reactive MongoDB. It supports creating and browsing questions, managing tags, full CRUD for answers, and search capabilities using Elasticsearch. The app also emits and consumes Kafka events to track question view counts.

### Features
- Reactive, non-blocking stack (WebFlux + Reactive MongoDB)
- Questions: create, read, list with cursor, search, delete
- Tags: create tags and retrieve questions by tag
- Answers: full CRUD using DTOs
- Search: full-text search using Elasticsearch
- Kafka integration:
  - Producer sends `ViewCountEvent` when a question is fetched
  - Consumer updates question view counts from `view-count-topic`

### Tech Stack
- Java 21, Spring Boot 3.5.4
- Spring WebFlux, Spring Data MongoDB Reactive
- Spring for Apache Kafka
- Spring Data Elasticsearch
- MongoDB (local)
- Elasticsearch 8.11.0
- Apache Kafka 3.7.0 (KRaft mode)
- Docker Compose
- Gradle

## Getting Started

### Prerequisites
- Java 21 (e.g., Amazon Corretto 21)
- Docker Desktop (for Kafka and Elasticsearch)
- MongoDB running locally on `localhost:27017` (database: `quora_db`)

### Clone
```bash
git clone <your-repo-url>
cd QuoraApp
```

### Start Services (Docker)
Start Kafka and Elasticsearch using Docker Compose:
```bash
docker compose up -d
```

This will start:
- Kafka on port `9092` (KRaft mode, auto-creates topics)
- Elasticsearch on port `9200`

Verify services are running:
```bash
# Check Kafka
docker exec -it kafka kafka-topics.sh --list --bootstrap-server localhost:9092

# Check Elasticsearch
curl http://localhost:9200
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
    elasticsearch:
      uris: http://localhost:9200
```

Elasticsearch:
```yaml
elasticsearch:
  host: localhost
  port: 9200
  scheme: http
```

### Run the App
```bash
./gradlew bootRun
```

## API Overview

### Questions
- `POST /api/questions` – create question
- `GET /api/questions/{id}` – get by id (publishes a view-count event)
- `GET /api/questions?cursor={cursor}&size={size}` – list with cursor support
- `GET /api/questions/search?query={q}&page={p}&size={s}` – search by title/content
- `GET /api/questions/search/elasticsearch?query={q}` – full-text search using Elasticsearch
- `GET /api/questions/tag/{tag}?page={p}&size={s}` – get questions by tag (not implemented)
- `DELETE /api/questions/{id}` – delete question

### Answers
- `POST /answers` – create answer
- `GET /answers/{id}` – get by id
- `GET /answers?questionId={qid}` – list answers for a question
- `PUT /answers/{id}` – update answer content
- `DELETE /answers/{id}` – delete answer

### Tags
- `POST /api/tags/create` – create a tag
- `GET /api/tags?tagName={name}` – get questions by tag name

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
- **Port 8080 in use**: stop the existing process or run with `--server.port=8081`.
- **Kafka not reachable**: ensure Docker stack is up (`docker compose ps`) and `kafka.bootstrap-servers` points to `localhost:9092`.
- **MongoDB connection errors**: ensure MongoDB is running on `localhost:27017`.
- **Elasticsearch connection errors**: ensure Elasticsearch container is running (`docker ps`) and accessible at `http://localhost:9200`.
- **Null views on questions**: initialize views to 0 or guard against null before incrementing.

## Notes
- This project uses reactive types (`Mono`, `Flux`). Avoid blocking calls in services and controllers.
- Kafka topic used by the app: `view-count-topic` (auto-created if `KAFKA_AUTO_CREATE_TOPICS_ENABLE` is true).
- Elasticsearch is used for full-text search capabilities on questions.
- The application uses Lombok for reducing boilerplate code.
