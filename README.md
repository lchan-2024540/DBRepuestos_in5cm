Sistema de Gestión de Empleados - Repuestos Automotrices
API REST desarrollada con Spring Boot para la gestión de empleados en una empresa de repuestos automotrices.
📋 Tabla de Contenidos

Características
Tecnologías Utilizadas
Requisitos Previos
Instalación
Configuración
Estructura del Proyecto
API Endpoints
Uso con Postman
Modelo de Datos
Autor

✨ Características

✅ CRUD completo de empleados
✅ Validación de datos
✅ Arquitectura en capas (Controller, Service, Repository)
✅ Persistencia con MySQL
✅ API RESTful
✅ Manejo de errores

🛠️ Tecnologías Utilizadas

Java 21
Spring Boot 4.0.2-SNAPSHOT
Spring Data JPA
MySQL 8
Maven
Hibernate
Lombok

📦 Requisitos Previos
Antes de comenzar, asegúrate de tener instalado:

Java JDK 21
MySQL 8.0+
Maven 3.6+
Postman (opcional, para probar la API)
IDE recomendado: IntelliJ IDEA

🚀 Instalación
1. Clonar el repositorio
bashgit clone <url-del-repositorio>
cd Ejemplo
2. Crear la base de datos
Abre MySQL Workbench o tu cliente MySQL y ejecuta:
sqlCREATE DATABASE dbrepuestosautomotriz_in5cm;
3. Configurar la base de datos
Edita el archivo src/main/resources/application.properties:
propertiesspring.application.name=Repuestos y Automotrices
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/dbrepuestosautomotriz_in5cm
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD_AQUI
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

⚠️ Importante: Reemplaza TU_PASSWORD_AQUI con tu contraseña de MySQL.

4. Compilar el proyecto
bashmvn clean install
5. Ejecutar la aplicación
bashmvn spring-boot:run
O desde tu IDE, ejecuta la clase EjemploApplication.java
La aplicación estará disponible en: http://localhost:8080
⚙️ Configuración
Variables de configuración principales
PropiedadValorDescripciónserver.port8080Puerto del servidorspring.datasource.urljdbc:mysql://localhost:3306/dbrepuestosautomotriz_in5cmURL de conexión a MySQLspring.jpa.hibernate.ddl-autoupdateActualiza automáticamente el esquema de BDspring.jpa.show-sqltrueMuestra las consultas SQL en consola
📁 Estructura del Proyecto
Ejemplo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/Luischan/RepuestosAutomotrices/
│   │   │       ├── controller/
│   │   │       │   └── EmpleadoController.java
│   │   │       ├── model/
│   │   │       │   └── Empleado.java
│   │   │       ├── repository/
│   │   │       │   └── EmpleadoRepository.java
│   │   │       ├── service/
│   │   │       │   ├── EmpleadoService.java
│   │   │       │   └── EmpleadoServiceImplements.java
│   │   │       └── EjemploApplication.java
│   │   └── resources/
│   │       └── application.properties
└── pom.xml
Descripción de componentes

Controller: Maneja las peticiones HTTP
Service: Contiene la lógica de negocio
Repository: Acceso a datos (JPA)
Model: Entidades de la base de datos

🌐 API Endpoints
Base URL
http://localhost:8080/api/empleados
Endpoints disponibles
MétodoEndpointDescripciónGET/api/empleadosObtener todos los empleadosGET/api/empleados/{id}Obtener empleado por IDPOST/api/empleadosCrear nuevo empleadoPUT/api/empleados/{id}Actualizar empleado existenteDELETE/api/empleados/{id}Eliminar empleado
📮 Uso con Postman
1. Listar todos los empleados
GET http://localhost:8080/api/empleados
Respuesta:
json[
  {
    "idEmpleado": 1,
    "nombreEmpleado": "Juan",
    "apellidoEmpleado": "Pérez",
    "puestoEmpleado": "Mecánico",
    "emailEmpleado": "juan.perez@ejemplo.com"
  }
]
2. Obtener empleado por ID
GET http://localhost:8080/api/empleados/1
Respuesta:
json{
  "idEmpleado": 1,
  "nombreEmpleado": "Juan",
  "apellidoEmpleado": "Pérez",
  "puestoEmpleado": "Mecánico",
  "emailEmpleado": "juan.perez@ejemplo.com"
}
3. Crear nuevo empleado
POST http://localhost:8080/api/empleados
Headers:
Content-Type: application/json
Body:
json{
  "nombreEmpleado": "María",
  "apellidoEmpleado": "García",
  "puestoEmpleado": "Vendedora",
  "emailEmpleado": "maria.garcia@ejemplo.com"
}
Respuesta:
json{
  "idEmpleado": 2,
  "nombreEmpleado": "María",
  "apellidoEmpleado": "García",
  "puestoEmpleado": "Vendedora",
  "emailEmpleado": "maria.garcia@ejemplo.com"
}
4. Actualizar empleado
PUT http://localhost:8080/api/empleados/1
Headers:
Content-Type: application/json
Body:
json{
  "nombreEmpleado": "Juan Carlos",
  "apellidoEmpleado": "Pérez Martínez",
  "puestoEmpleado": "Jefe de Mecánicos",
  "emailEmpleado": "juan.perez@ejemplo.com"
}
Respuesta:
json{
  "idEmpleado": 1,
  "nombreEmpleado": "Juan Carlos",
  "apellidoEmpleado": "Pérez Martínez",
  "puestoEmpleado": "Jefe de Mecánicos",
  "emailEmpleado": "juan.perez@ejemplo.com"
}
5. Eliminar empleado
DELETE http://localhost:8080/api/empleados/1
Respuesta:
204 No Content
📊 Modelo de Datos
Tabla: Empleados
CampoTipoRestriccionesDescripciónid_empleadoINTEGERPRIMARY KEY, AUTO_INCREMENTIdentificador úniconombre_empleadoVARCHAR(100)NOT NULLNombre del empleadoapellido_empleadoVARCHAR(100)NOT NULLApellido del empleadopuesto_empleadoVARCHAR(100)NOT NULLCargo o puestoemail_empleadoVARCHAR(100)NOT NULLCorreo electrónico
Ejemplo de registro
sqlINSERT INTO Empleados (nombre_empleado, apellido_empleado, puesto_empleado, email_empleado)
VALUES ('Juan', 'Pérez', 'Mecánico', 'juan.perez@ejemplo.com');
🔧 Solución de Problemas
Error: "Access denied for user"
Problema: La aplicación no puede conectarse a MySQL.
Solución:

Verifica que MySQL esté corriendo
Confirma que el usuario y contraseña en application.properties sean correctos
Verifica que la base de datos exista:

sqlSHOW DATABASES;
Error: "Port 8080 already in use"
Problema: El puerto 8080 está ocupado.
Solución:
Cambia el puerto en application.properties:
propertiesserver.port=8081
La aplicación no inicia
Solución:

Limpia y recompila:

bashmvn clean install

Verifica que Java 21 esté instalado:

bashjava -version
📝 Notas Adicionales

La aplicación crea automáticamente la tabla Empleados al iniciar (gracias a ddl-auto=update)
Todos los endpoints devuelven JSON
La validación de datos está implementada con @Valid
Los IDs se generan automáticamente

👤 Autor
Luis Chan

Proyecto: Repuestos Automotrices
Package: com.Luischan.RepuestosAutomotrices