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


# Guía Completa de las  Pruebas  de Cobertura 

Este proyecto utiliza **JaCoCo** para generar reportes de cobertura de pruebas. A continuación 
se explican los pasos necesarios para ejecutar las pruebas y visualizar el reporte generado.

---

### 🔹 Paso 1: Limpiar el proyecto y ejecutar pruebas

> mvn clean test

Este comando realiza una limpieza del proyecto y luego ejecuta todas las pruebas configuradas:

- clean: Elimina archivos temporales y la carpeta target para asegurar una compilación limpia.

- test: Ejecuta todas las pruebas unitarias e integrales definidas en el proyecto.

### 🔹  Paso 2: Preparar JaCoCo e instalar el proyecto

> mvn org.jacoco:jacoco-maven-plugin:0.8.11:prepare-agent install

Este comando configura el agente de JaCoCo y compila el proyecto:

- prepare-agent: Inicializa el agente de JaCoCo que recolectará los datos de cobertura.

- install: Compila e instala el proyecto en el repositorio local de Maven, 
ejecutando nuevamente las pruebas con el agente activado.

### 🔹 Paso 3: Generar el reporte de cobertura
> mvn org.jacoco:jacoco-maven-plugin:0.8.11:report

Este comando genera un informe detallado de cobertura de código basado en las pruebas ejecutadas anteriormente.

### 🔹📊 Visualizar el reporte de cobertura

1. Una vez ejecutados los comandos anteriores, navega hasta la 
siguiente ruta del proyecto:

target/site/jacoco/

2. Dentro de esta carpeta, ubica el archivo:

index.html

3. Arrastra este archivo a tu navegador o ábrelo directamente con doble clic.

Esto te permitirá visualizar un informe completo con métricas como cobertura por clase, 
línea, rama, etc., generado por JaCoCo.

