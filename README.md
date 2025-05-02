# 🔐 Microservicio de Gestión de Usuarios

Este microservicio se encarga de la **gestión de usuarios**, permitiendo crear, consultar, actualizar y eliminar información de usuarios.  
Forma parte de una arquitectura de microservicios y está desarrollado con **Spring Boot**.  
La **autenticación y autorización** se delega a un microservicio independiente de autenticación mediante **JWT**.

---

## 📦 Tecnologías

- Java 17+  
- Spring Boot 3  
- Spring Web  
- Spring Data JPA  
- PostgreSQL  
- Maven  
- Docker 
- Lombok  

---

## ⚙️ Configuración

### Variables de entorno requeridas

Asegúrate de definir las siguientes variables de entorno:


| Variable   | Descripción                             | Ejemplo                                                                 |
|------------|-----------------------------------------|-------------------------------------------------------------------------|
| DB_URL     | URL de conexión a la base de datos Neon | [jdbc:postgresql://ep-xyz.us-east-2.aws.neon.tech/productdb](https://console.neon.tech/app/projects/orange-bread-10311465) |


---

## 🚀 Endpoints principales

| Método | Endpoint         | Descripción             |
|--------|------------------|-------------------------|
| POST   | `/users/Save User/{id}`        | Crear usuario           |
| GET    | `/users/Get User/{id}`    | Obtener usuario por ID  |
| PATCH  | `/users/Uptade User/{id}`    | Actualizar usuario      |
| DELETE | `/users/Delete User/{id}`    | Eliminar usuario por ID |

**Base URL por defecto:** `http://localhost:8080`

---

## 🛡️ Seguridad

> Este microservicio **no implementa seguridad directamente**.  
> Depende de un **microservicio de autenticación (Auth)** que emite y valida tokens **JWT**.

- Los endpoints deben ser protegidos a través de una **API Gateway** o mediante filtros externos.
- Los tokens deben enviarse en la cabecera:  
  `Authorization: Bearer <token>`
- El control de acceso (roles, permisos) debe ser gestionado desde el microservicio de autenticación.

---

