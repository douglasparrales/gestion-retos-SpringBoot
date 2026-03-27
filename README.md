# 🏆 Challenge API – Challenge Management System

![Java](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.x-brightgreen?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=for-the-badge&logo=postgresql)
![Maven](https://img.shields.io/badge/Maven-3.x-red?style=for-the-badge&logo=apache-maven)

A robust REST API developed with **Spring Boot** for managing interactive challenges. The system allows users to register, participate in time-limited challenges, and climb a global ranking by earning points.

---

## 🚀 Technologies Used

* **Language:** Java 25
* **Framework:** Spring Boot 4.x
* **Persistence:** Spring Data JPA / Hibernate
* **Database:** PostgreSQL
* **Dependency Management:** Maven
* **Productivity:** Lombok
* **Validation:** Jakarta Bean Validation

---

## 🧠 Technical Objectives Achieved

1.  **RESTful API Design:** Proper use of HTTP verbs and status codes.
2.  **Layered Architecture:** Strict separation of concerns (Controller, Service, Repository).
3.  **Relationship Modeling:** Implementation of `@ManyToOne` and `@ManyToMany` relationships using JPA.
4.  **Decoupled Business Logic:** Critical rule validation within the service layer.
5.  **Global Exception Handling:** Centralized error management using `@RestControllerAdvice` for standardized responses.

---

## 🏗 System Architecture

The project follows a unidirectional data flow to ensure maintainability:

`Client ↔ Controller ↔ Service ↔ Repository ↔ Database`

### Main Layers
* **Controller:** Defines endpoints and manages DTO mapping.
* **Service:** The core of the application where business rules are validated.
* **Repository:** Data access abstraction using Spring Data JPA interfaces.
* **DTO (Data Transfer Objects):** Ensures security and decoupling from internal entities.

---

## 📊 Data Model (E-R)

* **User:** Manages credentials and accumulated score (`totalPoints`).
* **Challenge:** Contains challenge configuration, validity dates, and point rewards.
* **Enrollment:** Intermediate entity that tracks a user's progress within a specific challenge.

---

## 🗺️ Entity-Relationship Diagram (ERD)
![ERD Diagram](./backend-springBoot/src/main/resources/ERD%20Gestion_de_Retos.png)

---

## 🌐 Main Endpoints

### 👤 Users
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/users` | Register a new user. |
| `GET` | `/users/{id}` | Retrieve profile and points. |
| `GET` | `/users/ranking` | List top users by score. |

### 🎯 Challenges
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/challenges` | Create a new challenge (Admin). |
| `GET` | `/challenges` | List active and past challenges. |
| `GET` | `/challenges/{id}` | View challenge details and requirements. |

### 📝 Enrollments
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/challenges/{cId}/enroll/{uId}` | Join an active challenge. |
| `PUT` | `/challenges/{cId}/complete/{uId}` | Complete a challenge and claim points. |

---

## ⚙️ Implemented Business Rules

* ✅ **Uniqueness:** A user cannot enroll in the same challenge twice.
* ✅ **Integrity:** Only enrolled users can mark a challenge as "completed."
* ✅ **Time Validation:** Enrollments are not allowed for challenges that have already expired.
* ✅ **Reward System:** Upon completion, points are automatically and atomically added to the user's profile.

---

## 📦 Installation and Execution

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-user/your-repo.git](https://github.com/your-user/your-repo.git)
    cd your-repo
    ```

2.  **Database Configuration:**
    Update `src/main/resources/application.properties` with your PostgreSQL credentials:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/db_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

3.  **Run with Maven:**
    ```bash
    mvn spring-boot:run
    ```

---

## 🔮 Future Improvements

* [ ] Implement security with **Spring Security & JWT**.
* [incompatible for SpringBoot 4x] Interactive documentation with **Swagger / OpenAPI**.
* [ ] Unit testing coverage with **JUnit 5 & Mockito**.
* [ ] Containerization with **Docker**.
* [ ] Advanced pagination and filtering for challenge lists.

---