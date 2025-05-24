# 📦 API de Gestión de Pedidos – Spring Boot, DDD, SQL Server

Este proyecto implementa una API RESTful para la gestión de pedidos de clientes, siguiendo principios de **Domain-Driven Design (DDD)**, buenas prácticas de diseño de software y una arquitectura moderna basada en **Spring Boot 3**, **Spring Cloud 3.4.5** y **OpenJDK 21**.

---

## 🧭 Objetivo Funcional

El objetivo principal es facilitar la administración eficiente de **pedidos** que pueden incluir tanto **artículos** como **servicios**, permitiendo a las áreas de ventas, logística o administración:

- Crear nuevos pedidos con múltiples ítems
- Consultar pedidos existentes
- Modificar pedidos realizados
- Eliminar pedidos erróneos o cancelados

Este sistema se alinea con estructuras comunes de ERP como **SAP Business One**, modelando cabecera y detalle del pedido, y permitiendo su extensión y adaptación a necesidades específicas del negocio.

---

## 🧑‍💼 Valor de Negocio

- ✅ **Agilidad operativa:** simplifica la carga y gestión de pedidos multicanal.
- ✅ **Escalabilidad:** arquitectura preparada para crecer modularmente.
- ✅ **Compatibilidad ERP:** estructura de datos similar a SAP B1, permitiendo futuras integraciones.
- ✅ **Claridad y mantenimiento:** código limpio, documentado y separado por responsabilidad.

---

## 🧱 Arquitectura de Diseño

La API está organizada bajo el patrón **DDD (Domain Driven Design)**, estructurada en capas:

```
com.ejemplo.pedidos
├── application         # Servicios de aplicación (lógica de negocio)
├── domain              # Modelo de dominio y repositorios
│   ├── model.order     # Entidades: Order y OrderItem
│   └── repository      # Interfaces de repositorio JPA
├── infrastructure      # Persistencia personalizada (opcional)
├── interfaces          # Controladores REST y DTOs
├── shared              # Excepciones, mapeadores y utilidades
```

---

## 🔧 Stack Tecnológico

| Componente               | Descripción                                     |
|--------------------------|-------------------------------------------------|
| Spring Boot 3.4.5        | Framework base para microservicios              |
| Spring Data JPA          | Acceso a base de datos SQL con ORM              |
| SQL Server               | Base de datos relacional empresarial            |
| Lombok                   | Generación automática de getters/setters        |
| Jakarta Validation       | Validaciones en DTOs                            |
| OpenJDK 21               | Runtime moderno y estable para producción       |

---

## 📄 Especificación de la API

### Pedido (`Order`)
- `id`: Identificador único del pedido
- `customerCode`: Código del cliente
- `customerName`: Nombre del cliente
- `orderDate`: Fecha del pedido
- `items`: Lista de ítems del pedido

### Ítem del Pedido (`OrderItem`)
- `itemCode`: Código del artículo o servicio
- `description`: Descripción
- `type`: Enum { `ITEM`, `SERVICE` }
- `quantity`: Cantidad
- `price`: Precio unitario

---

## 🔌 Endpoints REST

| Método | Ruta                   | Descripción                  |
|--------|------------------------|------------------------------|
| POST   | `/api/orders`          | Crear un nuevo pedido        |
| GET    | `/api/orders`          | Obtener todos los pedidos    |
| GET    | `/api/orders/{id}`     | Obtener pedido por ID        |
| PUT    | `/api/orders/{id}`     | Actualizar pedido por ID     |
| DELETE | `/api/orders/{id}`     | Eliminar pedido por ID       |

---

## ⚠️ Manejo de Errores

El sistema incluye un manejador global de excepciones que responde con:

- `400 Bad Request`: Errores de validación
- `404 Not Found`: Recursos inexistentes
- `500 Internal Server Error`: Errores no controlados

---

## 🧪 Ejemplo de Uso

```http
POST /api/orders
Content-Type: application/json

{
  "customerCode": "C0001",
  "customerName": "Cliente Ejemplo S.A.",
  "orderDate": "2025-05-24",
  "items": [
    {
      "itemCode": "A1001",
      "description": "Artículo de prueba",
      "type": "ITEM",
      "quantity": 5,
      "price": 100.50
    },
    {
      "itemCode": "S2001",
      "description": "Servicio de instalación",
      "type": "SERVICE",
      "quantity": 1,
      "price": 250.00
    }
  ]
}
```

---

## ⚙️ Configuración (`application.properties`)

```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=pedidos
spring.datasource.username=sa
spring.datasource.password=12345
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🚀 Cómo Ejecutar

1. Asegúrate de tener SQL Server activo en `localhost:1433`
2. Crea la base de datos `pedidos`
3. Clona el repositorio y ejecuta con:

```bash
./mvnw spring-boot:run
```

4. Abre Postman y prueba con los endpoints `/api/orders`

---

## 📚 Futuras Extensiones

- Autenticación con Spring Security y JWT
- Integración con módulos ERP
- Gestión de stock y facturación
- Exportación a PDF/Excel