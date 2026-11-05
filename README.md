# Laboratorio 22: Gestión de órdenes de producción

## Descripción

Sistema compuesto por tres microservicios para administrar productos, materiales y órdenes de producción. Cada servicio tiene su propio proyecto Maven, API REST y base de datos MySQL.

## Requisitos

- JDK 25 o posterior.
- IntelliJ IDEA o Maven.
- MySQL.
- Postman para probar las API.

## Servicios y bases de datos

| Microservicio | Carpeta del proyecto | Puerto | Base de datos |
|---|---|---:|---|
| producto-service | producto-service | 8081 | bd_producto |
| material-service | material-service | 8082 | bd_material |
| orden-produccion-service | orden-service | 8083 | bd_orden_produccion |

Las tres bases de datos deben existir en MySQL antes de iniciar los servicios. Las tablas se crean o actualizan a partir de las entidades Java porque Hibernate está configurado con ddl-auto: update.

## Estructura

Cada proyecto tiene las capas model, dto, repository, services, controller y exception. El microservicio de órdenes incluye OrdenProduccion y DetalleMaterial.

La relación JPA es entre una orden y sus detalles: una orden puede tener varios detalles y cada detalle pertenece a una orden. idProducto e idMaterial se guardan como identificadores de referencia; no son relaciones JPA entre bases de datos distintas.

## Perfiles y variables de entorno

Cada microservicio contiene tres archivos YAML:

- application.yml: nombre de aplicación y perfil predeterminado dev.
- application-dev.yml: configuración local, muestra las consultas SQL y tiene valores por defecto para MySQL local.
- application-prod.yml: toma la URL y credenciales de MySQL desde variables de entorno y no muestra las consultas SQL.

Para elegir el perfil, define SPRING_PROFILES_ACTIVE como dev o prod. En IntelliJ, configura las variables en Run > Edit Configurations > Environment variables. Crea una configuración separada por microservicio; sus puertos y bases no deben mezclarse.

### Variables para desarrollo local

Producto:

~~~text
SPRING_PROFILES_ACTIVE=dev;SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/bd_producto;SPRING_DATASOURCE_USERNAME=root;SPRING_DATASOURCE_PASSWORD=;SERVER_PORT=8081
~~~

Material:

~~~text
SPRING_PROFILES_ACTIVE=dev;SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/bd_material;SPRING_DATASOURCE_USERNAME=root;SPRING_DATASOURCE_PASSWORD=;SERVER_PORT=8082
~~~

Órdenes:

~~~text
SPRING_PROFILES_ACTIVE=dev;SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/bd_orden_produccion;SPRING_DATASOURCE_USERNAME=root;SPRING_DATASOURCE_PASSWORD=;SERVER_PORT=8083
~~~

Si MySQL tiene una contraseña, reemplaza el valor vacío de SPRING_DATASOURCE_PASSWORD. En producción configura las mismas variables con el host, usuario y contraseña del servidor de producción y usa SPRING_PROFILES_ACTIVE=prod.

## Cómo iniciar

En IntelliJ, ejecuta la clase principal de cada servicio:

- Producto: com.tecsup.producto_service.ProductoServiceApplication
- Material: com.tecsup.material_service.MaterialServiceApplication
- Órdenes: com.tecsup.orden_service.OrdenServiceApplication

Confirma que cada aplicación inicie en su puerto asignado y que la consola muestre que Spring Boot arrancó sin errores.

También puedes compilar cada proyecto desde su propia carpeta:

~~~powershell
.\mvnw.cmd -DskipTests compile
~~~

Para que JPA cree las tablas, inicia cada servicio con la base de datos correspondiente disponible y las variables de conexión correctas.

## API REST

| Servicio | URL base | Operaciones |
|---|---|---|
| Productos | http://localhost:8081/api/productos | GET y POST en la ruta base; GET, PUT y DELETE con /{id} |
| Materiales | http://localhost:8082/api/materiales | GET y POST en la ruta base; GET, PUT y DELETE con /{id} |
| Órdenes | http://localhost:8083/api/ordenes | GET y POST en la ruta base; GET, PUT y DELETE con /{id} |

En Postman selecciona el método correspondiente y, para POST o PUT, usa Body > raw > JSON.

### Ejemplo para registrar un producto

~~~json
{
  "nombre": "Mesa de comedor",
  "descripcion": "Mesa de madera para seis personas",
  "unidadMedida": "unidad",
  "estado": "A"
}
~~~

### Ejemplo para registrar un material

~~~json
{
  "nombre": "Madera de pino",
  "descripcion": "Tablas secas para fabricar muebles",
  "unidadMedida": "tabla",
  "cantidadDisponible": 100,
  "estado": "A"
}
~~~

### Ejemplo de orden con dos materiales

Crea primero el producto y los materiales. Sustituye los IDs del ejemplo por los que devuelvan sus respectivas API.

~~~json
{
  "idProducto": 1,
  "fecha": "2026-11-10",
  "cantidadPlanificada": 5,
  "estado": "PENDIENTE",
  "detalles": [
    {
      "idMaterial": 1,
      "cantidadRequerida": 20
    },
    {
      "idMaterial": 2,
      "cantidadRequerida": 40
    }
  ]
}
~~~

La respuesta de la orden incluye la lista de detalles anidada. Las cantidades planificadas y requeridas deben ser positivas.

## Errores

- Los datos inválidos, como cantidades negativas o campos obligatorios vacíos, responden con HTTP 400 y mensajes de validación.
- Si se solicita un producto, material u orden que no existe, la API responde con HTTP 404.

## Evidencias de Postman

Agrega capturas de una creación exitosa y de una solicitud inválida. Puedes guardarlas en una carpeta evidencias dentro del proyecto y adjuntarlas junto con el código fuente. No se incluyeron imágenes en este README.

## Conclusiones

1. Separé el sistema en tres microservicios para que productos, materiales y órdenes tengan responsabilidades claras y bases de datos independientes.
2. JPA relaciona cada orden con sus detalles y permite persistirlos juntos.
3. Las cantidades y campos obligatorios se validan antes de guardar la información.
4. Las API REST permiten consultar y modificar los datos de cada servicio desde Postman.
5. El manejo de errores informa al cliente cuando envía datos inválidos o solicita recursos que no existen.

## Sustentación

Separé el sistema en tres microservicios porque cada uno administra una parte del negocio y usa su propia base de datos. JPA persiste los datos de las entidades en MySQL y relaciona una orden con sus detalles. Cuando la API recibe información inválida, las validaciones la rechazan y devuelven HTTP 400 con los campos que deben corregirse.