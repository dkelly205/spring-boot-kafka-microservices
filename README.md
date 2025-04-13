# Java Microservices 
This is still a work in progress.

## Description
Tutorial of microservices using Apache Kafka to send events from one microservice (order service)
to multiple consumers (stock service & email service)

## Technology used
- Apache Kafka
- Java 17
- Spring Boot
- Spring Web
- Lombok


### Sequence Diagram:

```mermaid
sequenceDiagram
  participant Client
  box "Order service"
    participant OrderController
    participant OrderProducer
  end
  participant Kafka
  participant EmailService
  participant StockService
  Client->>OrderController: POST /api/v1/orders
  OrderController->>OrderProducer: sendMessage
  OrderProducer->>Kafka: send
  OrderController-->>Client: ResponseEntity, 201 status code
  Kafka->>EmailService: consume
  Kafka->>StockService: consume

```
