# 🔐 Auth Microservice

Este microservicio se encarga de la autenticación y autorización de usuarios mediante JWT y Refresh Tokens. Forma parte de una arquitectura de microservicios y está desarrollado con Spring Boot.

---

## 📦 Tecnologías

- Java 17+
- Spring Boot 3
- Spring Security
- JWT (JSON Web Token)
- PostgreSQL
- Maven
- Docker (opcional)
- Lombok

---

## ⚙️ Configuración

### Variables de entorno requeridas

Asegúrate de definir las siguientes variables de entorno:

| Variable         | Descripción                             | Ejemplo                                      |
|------------------|------------------------------------------|----------------------------------------------|
| `DB_URL`         | URL de conexión a la base de datos       | `jdbc:postgresql://localhost:5432/authdb`    |
| `DB_USERNAME`    | Usuario de la base de datos              | `postgres`                                   |
| `DB_PASSWORD`    | Contraseña de la base de datos           | `1234`                                       |

---

## 🚀 Endpoints principales

| Método | Endpoint            | Descripción                    |
|--------|---------------------|--------------------------------|
| POST   | `/users/`   | crear usuarios|
| GET   | `/users/{id}` | obtener usuario|
| DELETE   | `/users/{id}` | eliminar usuario|
| PATCH   | `/users/{id}` | actualizar usuario|

