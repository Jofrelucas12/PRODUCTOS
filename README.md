# PRODUCTOS
Repositorio del proyecto PRODUCTOS.
Es un proyecto Spring Boot que usa Gradle como herramienta de construcción en Java 17.

Servicio que permite gestionar productos.

## Instalación

Para clonar el proyecto utilizar el comando:
```bash
git clone https://github.com/Jofrelucas12/PRODUCTOS.git
```
## Perfiles de ejecucion (profiles)
Por defecto se activa el profile de Spring 'local', configuración pensada para ejecución en la pc de manera local.
Esta configuración puede modificarse en el archivo 'application.properties'.
De momento es el unico perfil creado.

## Swagger
El proyecto expone la interfaz swagger de manera automática.
En la siguiente tabla se especifica cómo acceder a cada elemento expuesto:

|   Recurso  |  URL   |  Ejemplo   |
|-----|-----|-----|
| Swagger UI | /swagger-ui.html | http://localhost:8080/swagger-ui.html |

## H2
El proyecto cuenta con una base de datos local en memoria. 
Utiliza H2 como sistema administrador de la base.
En la siguiente tabla se especifica cómo acceder al sistema:


|   Recurso  | URL |  Ejemplo   |
|-----|-----|-----|
| Swagger UI | /h2 |http://localhost:8080/h2 |