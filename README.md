# Microservices Architecture - Banking System

A production-ready microservices architecture implementing a comprehensive banking system using Spring Boot, Spring Cloud, and cloud-native observability tools.

## 🏗️ Architecture Overview

This project demonstrates a complete microservices ecosystem with enterprise-grade patterns including service discovery, API gateway, distributed tracing, centralized configuration, and comprehensive observability.

### Core Business Services

- **Accounts Service** (Port 8020) - Customer account management with card and loan aggregation
- **Cards Service** (Port 8040) - Credit/debit card operations and lifecycle management
- **Loans Service** (Port 8030) - Loan application processing and management

### Infrastructure Services

- **Config Server** (Port 8010) - Centralized configuration with Git backend and encryption
- **Eureka Server** (Port 8761) - Service discovery and registration with health monitoring
- **Gateway Server** (Port 8050) - API Gateway with routing, resilience patterns, and rate limiting

### Observability Stack

- **Grafana** (Port 3000) - Unified observability dashboard
- **Prometheus** (Port 9090) - Metrics collection and time-series database
- **Tempo** (Port 3110) - Distributed tracing backend
- **Loki** - Centralized log aggregation
- **Promtail** - Log shipping agent

## 🛠️ Technology Stack

### Core Technologies
- **Framework**: Spring Boot 3.5.x
- **Microservices**: Spring Cloud 2025.0.0
- **Java Version**: 17
- **Build Tool**: Maven 3.9+

### Spring Cloud Components
- **Service Discovery**: Netflix Eureka
- **API Gateway**: Spring Cloud Gateway (WebFlux)
- **Configuration**: Spring Cloud Config Server
- **Message Bus**: Spring Cloud Bus with RabbitMQ
- **Circuit Breaker**: Resilience4j
- **Client Communication**: OpenFeign

### Data & Caching
- **Database**: MySQL 8.x (separate DB per service)
- **Caching**: Redis 7.x (Gateway rate limiting)
- **Object Storage**: MinIO (Loki data persistence)

### Observability & Monitoring
- **Metrics**: Micrometer + Prometheus
- **Tracing**: OpenTelemetry (OTLP) + Tempo
- **Logging**: Loki + Promtail
- **Visualization**: Grafana

### DevOps & Deployment
- **Containerization**: Docker + Jib Maven Plugin
- **Orchestration**: Docker Compose
- **Message Broker**: RabbitMQ 4.x

## 📁 Project Structure

```
microservices/
├── accounts/               # Account management service
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── cards/                  # Card management service
│   ├── src/
│   └── pom.xml
├── loans/                  # Loan management service
│   ├── src/
│   └── pom.xml
├── configserver/          # Centralized configuration
│   ├── src/
│   └── pom.xml
├── eurekaserver/          # Service registry
│   ├── src/
│   └── pom.xml
├── gatewayserver/         # API Gateway
│   ├── src/
│   └── pom.xml
└── docker-compose/
    ├── default/           # Development environment
    │   ├── docker-compose.yml
    │   └── common-config.yml
    ├── qa/               # QA environment (Port 7xxx)
    │   ├── docker-compose.yml
    │   └── common-config.yml
    ├── prod/             # Production environment (Port 9xxx)
    │   ├── docker-compose.yml
    │   └── common-config.yml
    └── observability/    # Monitoring configurations
        ├── grafana/
        ├── prometheus/
        ├── tempo/
        ├── loki/
        └── promtail/
```

## 🚀 Getting Started

### Prerequisites

- **Docker Desktop** 4.x+ (for containerized deployment)
- **Docker Compose** v2.x+

### Quick Start

1. **Clone the repository**
   ```bash
   git clone https://github.com/Abderrahimself/microservices-journey.git
   cd microservices-journey
   ```

2. **Switch to the working branch**
   ```bash
   git checkout abdo-dev
   ```

3. **Start all services**
   ```bash
   cd docker-compose/default
   docker-compose up -d
   ```

   This will pull all pre-built images from Docker Hub and start:
   - All microservices (accounts, cards, loans)
   - Infrastructure services (eureka, config server, gateway)
   - Databases (MySQL instances)
   - Observability stack (Grafana, Prometheus, Tempo, Loki)
   - Supporting services (RabbitMQ, Redis, MinIO)

## 🌐 Service Endpoints

### Core Services
| Service | Direct Access | Via Gateway |
|---------|--------------|-------------|
| Accounts | http://localhost:8020 | http://localhost:8050/abderrahimself/accounts/** |
| Cards | http://localhost:8040 | http://localhost:8050/abderrahimself/cards/** |
| Loans | http://localhost:8030 | http://localhost:8050/abderrahimself/loans/** |

### Infrastructure Services
- **Eureka Dashboard**: http://localhost:8761
- **Config Server**: http://localhost:8010
- **RabbitMQ Management**: http://localhost:15672 (guest/guest)

### Observability
- **Grafana**: http://localhost:3000 (admin/admin)
- **Prometheus**: http://localhost:9090
- **Tempo**: http://localhost:3110

### Database Management
- **Accounts DB**: http://localhost:8110 (phpMyAdmin)
- **Cards DB**: http://localhost:8130 (phpMyAdmin)
- **Loans DB**: http://localhost:8120 (phpMyAdmin)
- **Redis Commander**: http://localhost:8140 (admin/admin)

## 🔧 Key Features

### Observability & Monitoring

#### Metrics (Prometheus + Micrometer)
- Application metrics (JVM, HTTP, custom)
- Service-level metrics
- Database connection pool metrics
- Gateway routing metrics

#### Distributed Tracing (Tempo + OpenTelemetry)
- End-to-end request tracing
- Service dependency mapping
- Performance bottleneck identification
- Automatic span creation and propagation

#### Logging (Loki + Promtail)
- Centralized log aggregation
- Structured logging with correlation IDs
- Log pattern: `[service-name,trace_id,span_id]`
- Container log collection

#### Dashboards (Grafana)
- Pre-configured datasources (Prometheus, Loki, Tempo)
- Trace-to-log correlation
- Service health monitoring
- Custom metric visualization

## 🐳 Docker Deployment

All services are pre-built and available on Docker Hub. Simply use Docker Compose to start the entire stack.

```bash
cd docker-compose/default
docker-compose up -d

# View running services
docker-compose ps

# View logs
docker-compose logs -f accounts

# Stop all services
docker-compose down

# Stop and remove volumes
docker-compose down -v
```

## 🔄 API Request Flow

Example: Fetching customer details with aggregated data from multiple services

```
Client Request
      ↓
Gateway Server (Port 8050)
      ├─ RequestTraceFilter: Generate/Extract Correlation ID
      ├─ Rate Limiting: Check Redis for request count
      ├─ Circuit Breaker: Check service health
      └─ Route to: lb://ACCOUNTS
            ↓
Accounts Service (Port 8020)
      ├─ Receive correlation-id header
      ├─ Fetch account data from MySQL
      ├─ OpenFeign Call → Cards Service (Port 8040)
      │   └─ Forward correlation-id header
      ├─ OpenFeign Call → Loans Service (Port 8030)
      │   └─ Forward correlation-id header
      └─ Aggregate all data (Account + Cards + Loans)
            ↓
Response with Correlation ID header
      ↓
Gateway Server
      ├─ ResponseTraceFilter: Add correlation-id to response headers
      └─ Return to client
            ↓
Client receives aggregated customer data
```

## 📝 Configuration Examples

Dynamic configuration updates and testing patterns will be documented here.

## 🧪 Testing

### Complete End-to-End Test Scenario

This scenario creates a complete customer profile with account, card, and loan:

```bash
# 1. Create new customer with mobile 8888888888
curl -X POST "http://localhost:8020/api/create" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Complete Test User",
    "email": "complete@example.com",
    "mobileNumber": "8888888888"
  }'

# 2. Create card for this customer
curl -X POST "http://localhost:8040/api/create?mobileNumber=8888888888" \
  -H "abderrahimself-correlation-id: complete-test"

# 3. Create loan for this customer
curl -X POST "http://localhost:8030/api/create?mobileNumber=8888888888"

# 4. Test aggregated endpoint for new customer
curl -X GET "http://localhost:8020/api/fetchCustomerDetails?mobileNumber=8888888888" \
  -H "abderrahimself-correlation-id: complete-customer-test"
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📚 Additional Resources

- [Spring Cloud Documentation](https://spring.io/projects/spring-cloud)
- [Resilience4j Guide](https://resilience4j.readme.io/)
- [OpenTelemetry Java](https://opentelemetry.io/docs/languages/java/)
- [Grafana Loki Documentation](https://grafana.com/docs/loki/)
- [Docker Compose Reference](https://docs.docker.com/compose/)

---

**⭐ If you find this project helpful, please consider giving it a star!**

**Author**: Abderrahim Mabrouk
**Repository**: https://github.com/Abderrahimself/microservices-journey
