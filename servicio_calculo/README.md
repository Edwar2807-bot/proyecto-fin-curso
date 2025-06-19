# Servicio de Cálculo Científico

Microservicio Spring Boot para operaciones de cálculo científico.

## Configuración

- **Puerto:** 8082
- **Framework:** Spring Boot 3.2.5
- **Java:** 17

## Ejecutar el servicio

### Con Maven
```bash
mvn spring-boot:run
```

### Con Docker
```bash
# Construir la imagen
mvn clean package
docker build -t servicio-calculo .

# Ejecutar el contenedor
docker run -p 8082:8082 servicio-calculo
```

### Con Docker Compose
```bash
mvn clean package
docker-compose up --build
```

## Endpoints disponibles

- `GET /api/calculo/status` - Verificar estado del servicio

## Estructura del proyecto

```
src/
├── main/
│   ├── java/
│   │   └── org/saul_cordoba/
│   │       ├── CalculoServiceApplication.java
│   │       ├── controller/
│   │       ├── service/
│   │       ├── dto/
│   │       └── entity/
│   └── resources/
│       └── application.properties
└── test/
    └── java/
``` 