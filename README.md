# NeurixLearn - Microservices Architecture

> Plataforma educativa construida con arquitectura de microservicios usando Spring Boot y Spring Cloud

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Compose-blue.svg)](https://www.docker.com/)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)

---

## Arquitectura de Microservicios

Este proyecto implementa una arquitectura de microservicios completa con los siguientes componentes:

### Servicios

#### Discovery Server (Eureka)
- **Puerto:** 8761
- **Función:** Registro y descubrimiento de servicios
- **Tecnología:** Netflix Eureka Server
- **URL:** http://localhost:8761

#### API Gateway
- **Puerto:** 8080
- **Función:** Punto de entrada único, enrutamiento y balanceo de carga
- **Tecnología:** Spring Cloud Gateway
- **Características:**
  - Enrutamiento dinámico
  - Load balancing
  - Circuit breaker
  - Rate limiting

#### Learne Service
- **Función:** Gestión de cursos y contenido educativo
- **Base de datos:** MySQL (Puerto 3307)
- **Endpoints principales:**
  - `/api/v1/courses` - Gestión de cursos
  - `/api/v1/sections` - Secciones de cursos
  - `/api/v1/units` - Unidades de aprendizaje
  - `/api/v1/materials` - Materiales educativos
  - `/api/v1/exams` - Exámenes y evaluaciones
  - `/api/v1/questions` - Preguntas de exámenes
  - `/api/v1/enrollments` - Inscripciones a cursos
  - `/api/v1/notes` - Notas de estudiantes
  - `/api/v1/tutorials` - Tutorías
- **Swagger:** http://localhost:8080/learne/swagger-ui/index.html

#### Profile Service
- **Función:** Gestión de usuarios y autenticación
- **Base de datos:** MySQL (Puerto 3308)
- **Endpoints principales:**
  - `/api/v1/users` - Gestión de usuarios
  - `/api/v1/auth/login` - Autenticación
  - `/api/v1/auth/register` - Registro de usuarios
- **Swagger:** http://localhost:8080/profile/swagger-ui/index.html
- **Integración:** Publica eventos a Kafka al crear usuarios

#### Notification Service
- **Función:** Envío de notificaciones por email
- **Tecnología:** Apache Kafka
- **Características:**
  - Consumidor de eventos de Kafka
  - Envío asíncrono de emails
  - Notificaciones de bienvenida a nuevos usuarios

### Infraestructura

#### MySQL Databases
- **learne-db:** Puerto 3307
- **profile-db:** Puerto 3308
- **Versión:** MySQL 8.0

#### Apache Kafka
- **Puerto:** 9092
- **Función:** Message broker para comunicación asíncrona
- **Tópicos:**
  - `user-created` - Eventos de creación de usuarios

#### Zookeeper
- **Puerto:** 2181
- **Función:** Coordinación de Kafka

---

## Inicio Rápido

### Prerrequisitos
- Docker y Docker Compose
- Java 17+
- Maven 3.8+

### Iniciar el Proyecto

```bash
# Opción 1: Iniciar servicios (usando JARs pre-compilados)
./start.sh

# Opción 2: Compilar y ejecutar
./build-and-run.sh
```

Espera 1-2 minutos para que todos los servicios inicien.

### Verificar Servicios

```bash
./check-services.sh
```

### URLs de Acceso
- **Eureka Dashboard:** http://localhost:8761
- **API Gateway:** http://localhost:8080
- **Swagger Learne Service:** http://localhost:8080/learne/swagger-ui/index.html
- **Swagger Profile Service:** http://localhost:8080/profile/swagger-ui/index.html

---

## Scripts de Gestión

| Script | Descripción | Tiempo estimado |
|--------|-------------|-----------------|
| `./start.sh` | Iniciar todos los servicios | 1-2 min |
| `./stop.sh` | Detener todos los servicios | 10 seg |
| `./restart.sh` | Reiniciar servicios | 1-2 min |
| `./build-and-run.sh` | Compilar y ejecutar (después de cambios) | 3-5 min |
| `./check-services.sh` | Verificar estado de servicios | Inmediato |

---

## Stack Tecnológico

### Backend
- **Java 17+**
- **Spring Boot 3.2.5**
- **Spring Cloud Gateway** - API Gateway
- **Netflix Eureka** - Service Discovery
- **Spring Data JPA** - Persistencia
- **Hibernate** - ORM
- **Spring Kafka** - Mensajería asíncrona

### Base de Datos
- **MySQL 8.0** - Base de datos relacional

### Mensajería
- **Apache Kafka** - Message broker
- **Zookeeper** - Coordinación de Kafka

### Documentación
- **Swagger/OpenAPI 3.0** - Documentación interactiva de APIs

### Contenedores
- **Docker** - Containerización
- **Docker Compose** - Orquestación de servicios

### Testing
- **JUnit 5** - Unit testing
- **Mockito** - Mocking
- **Cucumber** - BDD testing

---

## Seguridad y Autenticación

### Autenticación JWT
Las APIs requieren autenticación mediante JWT Bearer Token:

```bash
# 1. Registrar usuario
curl -X POST http://localhost:8080/profile/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Juan Pérez",
    "email": "juan@example.com",
    "password": "password123"
  }'

# 2. Obtener token
curl -X POST http://localhost:8080/profile/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "juan@example.com",
    "password": "password123"
  }'

# 3. Usar token en requests
curl http://localhost:8080/learne/api/v1/courses \
  -H "Authorization: Bearer {tu-token-jwt}"
```

### CORS
Configurado para permitir requests desde:
- `http://localhost:3000` (React)
- `http://localhost:4200` (Angular)
- `http://localhost:5173` (Vite)
- `http://localhost:8081` (Otros frontends)

---

## Patrones de Arquitectura Implementados

### Microservices Patterns
- ✅ **Service Discovery** - Eureka para registro automático
- ✅ **API Gateway** - Punto de entrada único
- ✅ **Database per Service** - Cada servicio tiene su propia BD
- ✅ **Event-Driven Architecture** - Kafka para comunicación asíncrona
- ✅ **Circuit Breaker** - Resiliencia en comunicaciones
- ✅ **Centralized Configuration** - Configuración por servicio

### Domain-Driven Design (DDD)
- ✅ **Aggregates** - Entidades raíz de dominio
- ✅ **Value Objects** - EmailAddress, etc.
- ✅ **Commands** - CreateCourseCommand, UpdateUserCommand
- ✅ **Queries** - GetCourseByIdQuery, GetAllUsersQuery
- ✅ **Command/Query Separation (CQRS)** - Separación de escritura/lectura
- ✅ **Domain Services** - Lógica de negocio encapsulada

### Otros Patrones
- ✅ **Repository Pattern** - Acceso a datos
- ✅ **DTO Pattern** - Transferencia de datos
- ✅ **Builder Pattern** - Construcción de objetos
- ✅ **Strategy Pattern** - Naming strategies

---

## Estructura del Proyecto

```
NeurixLearn-Microservices/
├── discovery-server/          # Eureka Server
├── api-gateway/              # Spring Cloud Gateway
├── learne-service/           # Servicio de cursos
│   ├── domain/              # Capa de dominio (DDD)
│   ├── application/         # Casos de uso
│   ├── infrastructure/      # Persistencia y configuración
│   └── interfaces/          # Controllers y DTOs
├── profile-service/          # Servicio de usuarios
│   ├── domain/
│   ├── application/
│   ├── infrastructure/
│   └── interfaces/
├── notification-service/     # Servicio de notificaciones
├── docker-compose.yml        # Orquestación de servicios
├── pom.xml                  # POM padre
└── scripts/                 # Scripts de gestión
    ├── start.sh
    ├── stop.sh
    └── restart.sh
```

---

## Desarrollo

### Compilar el Proyecto
```bash
mvn clean install
```

### Compilar un Servicio Específico
```bash
cd learne-service
mvn clean package
```

### Ejecutar Tests
```bash
mvn test
```

### Ver Logs
```bash
# Todos los servicios
docker-compose logs -f

# Servicio específico
docker-compose logs -f learne-service
```

---

## Configuración

### Variables de Entorno
Cada servicio puede configurarse mediante variables de entorno. Ver `application.properties` en cada servicio.

### Puertos Configurables
| Servicio | Puerto por Defecto | Variable |
|----------|-------------------|----------|
| Discovery Server | 8761 | `SERVER_PORT` |
| API Gateway | 8080 | `SERVER_PORT` |
| MySQL Learne | 3307 | - |
| MySQL Profile | 3308 | - |
| Kafka | 9092 | - |

---

## Solución de Problemas

### Los servicios no inician
```bash
./restart.sh
```

### Error de puerto en uso
```bash
# Detener todo
./stop.sh
docker-compose down -v

# Verificar puertos
lsof -i :8080
lsof -i :8761

# Reiniciar
./start.sh
```

### Resetear base de datos
```bash
docker-compose down -v
./build-and-run.sh
```

### Ver estado de servicios
```bash
docker-compose ps
./check-services.sh
```

---

## Roadmap

- [x] Arquitectura de microservicios básica
- [x] Service Discovery con Eureka
- [x] API Gateway
- [x] Documentación Swagger
- [x] Mensajería con Kafka
- [x] CQRS Pattern
- [ ] Config Server centralizado
- [ ] Distributed Tracing (Zipkin/Sleuth)
- [ ] Monitoring (Prometheus/Grafana)
- [ ] API Rate Limiting
- [ ] OAuth2/JWT avanzado

---

## Contribución

Este proyecto sigue las mejores prácticas de microservicios:
- Cada servicio es independiente
- Base de datos por servicio
- Comunicación asíncrona mediante eventos
- Configuración externalizada
- Documentación completa

---

## Licencia

Apache License 2.0

---

## Autores

Desarrollado por el equipo de Arquitectura de Software

---

**Versión:** 1.0.0  
**Última actualización:** Julio 2026

