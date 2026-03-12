# 🏆 Challenge API – Sistema de Gestión de Retos

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=for-the-badge&logo=postgresql)
![Maven](https://img.shields.io/badge/Maven-3.x-red?style=for-the-badge&logo=apache-maven)

API REST robusta desarrollada con **Spring Boot** para la gestión de retos interactivos. El sistema permite a los usuarios registrarse, participar en desafíos temporales y escalar en un ranking global mediante la obtención de puntos.

---

## 🚀 Tecnologías Utilizadas

* **Lenguaje:** Java 17
* **Framework:** Spring Boot 3.x
* **Persistencia:** Spring Data JPA / Hibernate
* **Base de Datos:** PostgreSQL
* **Gestión de Dependencias:** Maven
* **Productividad:** Lombok
* **Validación:** Jakarta Bean Validation

---

## 🧠 Objetivos Técnicos Alcanzados

Para este desarrollo, me enfoqué en implementar conceptos clave de un entorno backend profesional:

1.  **Diseño de API RESTful:** Uso correcto de verbos HTTP y códigos de estado.
2.  **Arquitectura por Capas:** Separación estricta de responsabilidades (Controller, Service, Repository).
3.  **Modelado de Relaciones:** Implementación de relaciones `@ManyToOne` y `@ManyToMany` con JPA.
4.  **Lógica de Negocio Descentralizada:** Validación de reglas críticas en la capa de servicios.
5.  **Manejo Global de Excepciones:** Uso de `@RestControllerAdvice` para respuestas de error estandarizadas.

---

## 🏗 Arquitectura del Sistema

El proyecto sigue un flujo de datos unidireccional para garantizar la mantenibilidad:

`Client ↔ Controller ↔ Service ↔ Repository ↔ Database`

### Capas Principales
* **Controller:** Define los endpoints y gestiona el mapeo de los DTOs.
* **Service:** Núcleo de la aplicación donde se validan las reglas de negocio.
* **Repository:** Abstracción de acceso a datos mediante interfaces de Spring Data JPA.
* **DTO (Data Transfer Objects):** Garantiza la seguridad y el desacoplamiento de las entidades internas.

---

## 📊 Modelo de Datos (E-R)

* **Usuario:** Gestiona credenciales y el acumulado de puntos (`puntosTotales`).
* **Reto:** Contiene la configuración del desafío, fechas de vigencia y recompensa en puntos.
* **Inscripción:** Entidad intermedia que registra el estado del progreso del usuario en un reto específico.

---

## 🌐 Endpoints Principales

### 👤 Usuarios
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/usuarios` | Registrar un nuevo usuario. |
| `GET` | `/usuarios/{id}` | Consultar perfil y puntos. |
| `GET` | `/usuarios/ranking` | Listar el TOP de usuarios por puntaje. |

### 🎯 Retos
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/retos` | Crear un nuevo reto (Admin). |
| `GET` | `/retos` | Listar retos activos y pasados. |
| `GET` | `/retos/{id}` | Ver detalles y requisitos del reto. |

### 📝 Inscripciones
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/retos/{rId}/inscribirse/{uId}` | Unirse a un reto vigente. |
| `PUT` | `/retos/{rId}/completar/{uId}` | Finalizar reto y reclamar puntos. |

---

## ⚙️ Reglas de Negocio Implementadas

* ✅ **Unicidad:** Un usuario no puede inscribirse dos veces al mismo reto.
* ✅ **Integridad:** Solo los usuarios inscritos pueden marcar un reto como "completado".
* ✅ **Validación Temporal:** No se permiten inscripciones a retos cuya fecha de fin haya expirado.
* ✅ **Recompensa:** Al completar un reto, los puntos se suman automáticamente al perfil del usuario de forma atómica.

---

## 📦 Instalación y Ejecución

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/tu-usuario/tu-repositorio.git](https://github.com/tu-usuario/tu-repositorio.git)
    cd tu-repositorio
    ```

2.  **Configurar base de datos:**
    Ajusta el archivo `src/main/resources/application.properties` con tus credenciales de PostgreSQL:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/nombre_db
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    ```

3.  **Ejecutar con Maven:**
    ```bash
    mvn spring-boot:run
    ```

---

## 🔮 Roadmap y Próximas Mejoras

* [ ] Implementar seguridad con **Spring Security & JWT**.
* [ ] Documentación interactiva con **Swagger / OpenAPI**.
* [ ] Cobertura de tests unitarios con **JUnit 5 & Mockito**.
* [ ] Contenerización con **Docker**.
* [ ] Paginación y filtros avanzados en la lista de retos.

---

## 👨‍💻 Autor
*Si este proyecto te parece interesante o útil, ¡no dudes en darle una ⭐!*