# Microservices Lab – Event-Driven Architecture with Kafka (KRaft Mode)

## Overview
This project demonstrates an **event-driven microservices architecture** using **Spring Boot, Apache Kafka (KRaft mode), Docker, and Spring Cloud Gateway**.

The system contains three microservices:

- **Order Service** – Receives order requests and publishes events to Kafka
- **Inventory Service** – Consumes order events and updates inventory
- **Billing Service** – Consumes order events and generates invoices

All requests are routed through an **API Gateway**, and the services communicate asynchronously using **Kafka events**.

---

# Architecture


Client (Postman)
│
▼
API Gateway (Port 8080)
│
▼
Order Service (8081)
│
Publishes Event → Kafka Topic (order-topic)
│
▼
┌─────────────────────┐
│ │
▼ ▼
Inventory Service Billing Service
(Consumes Event) (Consumes Event)
(Port 8082) (Port 8083)


---

# Technologies Used

- Java 17
- Spring Boot
- Spring Cloud Gateway
- Apache Kafka (KRaft Mode)
- Docker & Docker Compose
- Maven
- Postman

---

# Project Structure


backend/
│
├── docker-compose.yml
│
├── order-service
│ ├── pom.xml
│ └── src
│
├── inventory-service
│ ├── pom.xml
│ └── src
│
├── billing-service
│ ├── pom.xml
│ └── src
│
└── api-gateway
├── pom.xml
└── src


---

# Services and Ports

| Service | Port |
|------|------|
| API Gateway | 8080 |
| Order Service | 8081 |
| Inventory Service | 8082 |
| Billing Service | 8083 |
| Kafka | 9092 |

---

# System Workflow

1. Client sends a request to create an order.
2. Request is received by the **API Gateway**.
3. Gateway routes the request to the **Order Service**.
4. Order Service publishes an **OrderCreated event** to Kafka topic `order-topic`.
5. **Inventory Service** consumes the event and updates stock.
6. **Billing Service** consumes the event and generates an invoice.

---

# Running the Project

## 1. Start Kafka (KRaft Mode)

Navigate to the project root where `docker-compose.yml` exists.

```bash
docker compose up -d

Verify Kafka container is running:

docker ps
2. Start Microservices

Open separate terminals and run each service.

Order Service
cd backend/order-service
mvn spring-boot:run

Runs on:

http://localhost:8081
Inventory Service
cd backend/inventory-service
mvn spring-boot:run

Runs on:

http://localhost:8082
Billing Service
cd backend/billing-service
mvn spring-boot:run

Runs on:

http://localhost:8083
API Gateway
cd backend/api-gateway
mvn spring-boot:run

Runs on:

http://localhost:8080
Testing Using Postman
Create Order
Request

POST

http://localhost:8080/orders
Headers
Content-Type: application/json
Body
{
  "orderId": "ORD-1001",
  "item": "Laptop",
  "quantity": 1
}
Expected Response
Order Created & Event Published
Expected Logs
Order Service
Order Created: ORD-1001
Inventory Service
Inventory Service received order: ORD-1001
Updating stock for item: Laptop quantity: 1
Billing Service
Billing Service received order: ORD-1001
Generating invoice for item: Laptop quantity: 1
Kafka Configuration

Kafka runs in KRaft mode, which eliminates the need for ZooKeeper.

Key configuration used in docker-compose.yml:

Kafka broker and controller combined

Single-node cluster

Auto topic creation enabled

Kafka port:

9092
Deliverables

The project includes:

3 working Spring Boot microservices

API Gateway using Spring Cloud Gateway

Kafka running in KRaft mode using Docker

Event-driven communication between services

Docker Compose configuration

Postman API testing

Service logs demonstrating event flow

Key Concepts Demonstrated

Microservices architecture

Event-driven communication

Apache Kafka messaging

API Gateway routing

Asynchronous service interaction

Docker containerization
