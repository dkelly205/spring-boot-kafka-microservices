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
- Spring Validation
- Spring JPA
- Docker
- H2
- Maildev
- Lombok


#### Order Service Sequence Diagram:
```mermaid
sequenceDiagram
  participant Client
  box "Order service"
    participant OrderController
    participant OrderService
    participant OrderMapper
    participant OrderRepository
    participant OutboxRepository
    participant OutboxPoller
    participant OrderProducer
   
  end
  participant Kafka
  Client->>OrderController: POST /api/v1/orders
  OrderController->>OrderService: create
  OrderService->>OrderMapper: mapToEntity
  OrderMapper->>OrderService: cartOrder
  OrderService->>OrderRepository: save
  OrderRepository->>OrderService: savedOrder
  OrderService->>OutboxRepository: save
  OrderService->>OrderMapper: mapToResponse
  OrderMapper-->>OrderService: OrderResponse
  OrderService-->>OrderController: OrderResponse
  OrderController-->>Client: ResponseEntity, 201 status code
  OutboxPoller->>OutboxRepository: findAllByStatusAndAttemptsLessThan
  OutboxPoller->>OrderProducer: sendMessage
  OrderProducer->>Kafka: send OrderCreatedEvent
```


#### Email Service Sequence Diagram:
```mermaid
sequenceDiagram
  participant Kafka
  box "Email service"
    participant OrderConsumer
    participant EmailService
    participant JavaMailSender  
  end
  OrderConsumer->>Kafka: consume OrderCreatedEvent
  OrderConsumer->>EmailService: sendEmail
  EmailService->>JavaMailSender: send
```
