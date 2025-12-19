# 62 Rosas Tattoo – Puesta en marcha

Este repositorio contiene el proyecto **62 Rosas Tattoo** con:

* Backend (API REST)
* Base de datos
* Despliegue local mediante **Docker Compose**

Este README explica **cómo poner en marcha el proyecto en local**.

---

## Requisitos

* **Docker Desktop** instalado y en funcionamiento
* **Docker Compose** (incluido con Docker Desktop)
* Postman para probar endpoints

---

## Arranque con Docker

### 1) Clonar el repositorio o descargar el .zip del proyecto

### 2) Levantar los contenedores

Abrimos el Terminal en la carpeta donde esté el docker-compose.dev.yaml y ejecutamos el comando:

*docker-compose -f docker-compose up -d*

Esto:

* construye la imagen del backend
* levanta la base de datos

Después, abrimos el Terminal en la carpeta raíz del proyecto y ejecutamos el comando:

*mvn spring-boot:run*

Esto:

* levanta la API

## Base de datos

La base de datos MariaDB se levanta automáticamente con Docker. Es un volumen persistente en el que los datos se guardan aunque apaguemos el contenedor.
Se puede conectar a DBeaver para tener una interfaz.


## API REST

Cuando el backend esté levantado, la API estará disponible en el host/puerto configurado. En este caso:

* http://localhost:8080


## 🧪 Probar el proyecto

1. Asegúrate de que los contenedores están arriba con el comando: docker compose ps
2. Abre Postman.
3. Prueba endpoints (por ejemplo, listar clientes/profesionales).

La colección de Postman con todos los endpoints también está disponible.
