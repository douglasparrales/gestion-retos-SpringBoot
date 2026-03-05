# DIAGRAMA ENTIDAD-RELACION DEL SISTEMA DE GESTION DE RETOS
![ERD](./src/main/resources/ERD%20Gestion_de_Retos.png)

# 🏆 Challenge Management API

API REST para la gestión de **retos** donde los usuarios pueden inscribirse, completarlos y acumular puntos.

Este proyecto está diseñado como práctica de backend usando **Spring Boot**, arquitectura por capas y buenas prácticas de desarrollo.

---

# 📦 Entidades principales

## 👤 User

Representa a los usuarios del sistema.

Atributos:

* `id`
* `username` (único)
* `email`
* `password`
* `totalPoints`
* `registrationDate`

---

## 🏆 Challenge

Representa un reto que los usuarios pueden completar.

Atributos:

* `id`
* `title`
* `description`
* `points`
* `startDate`
* `endDate`
* `creator` (User)

---

## 📌 Inscription

Tabla intermedia que representa la inscripción de un usuario a un reto.

Atributos:

* `id`
* `user`
* `challenge`
* `inscriptionDate`
* `completed` (boolean)

---

# 🌐 Endpoints mínimos

## 🔐 Users

### Registrar usuario

```
POST /users
```

### Ver perfil de usuario

```
GET /users/{id}
```

### Ranking de usuarios por puntos

```
GET /users/ranking
```

---

## 🏆 Challenges

### Crear reto

```
POST /challenges
```

### Listar retos activos

```
GET /challenges
```

### Ver detalle de un reto

```
GET /challenges/{id}
```

---

## 📌 Inscription

### Inscribirse a un reto

```
POST /challenges/{id}/inscription/{userId}
```

### Completar un reto

```
PUT /challenges/{id}/complete/{userId}
```

---

# ⚙️ Reglas de negocio

El sistema debe cumplir las siguientes reglas:

* Un usuario **no puede inscribirse dos veces** al mismo reto.
* Un usuario **no puede completar un reto si no está inscrito**.
* Al completar un reto **se suman los puntos al usuario**.
* No se puede inscribir a un reto **si ya está vencido**.
* Las fechas del reto deben ser **validadas correctamente**.

---

# ⭐ Nivel Extra (Opcional Avanzado)

Para aumentar la complejidad del proyecto se pueden implementar las siguientes mejoras:

### 🔐 Seguridad

* Autenticación con JWT.

### 📊 Funcionalidades avanzadas

* Paginación y ordenamiento.
* Filtros dinámicos (por fecha, puntos o estado del reto).

### 🧪 Calidad de código

* Tests con JUnit y Mockito.

### 📦 Documentación

* Documentación automática con Swagger.

### 🐳 Infraestructura

* Dockerizar la aplicación.

### 🔄 Arquitectura

* Usar MapStruct para mapear DTOs.

---

# 🚀 Objetivo del proyecto

Practicar conceptos clave de desarrollo backend:

* Diseño de APIs REST
* Arquitectura por capas
* Manejo de DTOs
* Reglas de negocio
* Relaciones en base de datos
* Buenas prácticas en proyectos con Spring Boot
