Drop database if exists DBRepuestosAutomotriz_in5cm; 
create database DBRepuestosAutomotriz_in5cm; 
use DBRepuestosAutomotriz_in5cm; 

-- Entidades --
create table Proveedores( 
id_proveedor int auto_increment not null, 
nombre_proveedor varchar(100) not null, 
telefono_proveedor int not null, 
direccion varchar(100) not null, 
email_proveedor varchar(100) not null, 
primary key PK_id_proveedor(id_proveedor) 
); 

create table Empleados( 
id_empleado int auto_increment not null, 
nombre_empleado varchar(100) not null, 
apellido_empleado varchar(100) not null, 
puesto_empleado varchar(100) null, 
email_empleado varchar(100) not null, 
primary key PK_id_empleado(id_empleado) 
);
 
create table Repuestos( 
id_repuesto int auto_increment not null, 
nombre_repuesto varchar(100) not null, 
categoria_repuesto varchar(100) not null, 
precio_compra double not null, 
precio_venta double not null, 
id_proveedor int not null, 
primary key PK_id_repuesto(id_repuesto), 
constraint FK_repuesto_proveedor foreign key (id_proveedor)  
references proveedores(id_proveedor) on delete cascade 
);
 
create table Ventas( 
id_venta int auto_increment not null, 
fecha_venta date not null, 
cantidad int not null, 
total double not null, 
id_empleado int not null, 
id_repuesto int not null, 
primary key PK_id_venta(id_venta), 
constraint FK_ventas_empleado foreign key (id_empleado)  
references Empleados(id_empleado) on delete cascade, 
constraint FK_ventas_repuestos foreign key (id_repuesto)  
references Repuestos(id_repuesto) on delete cascade 
);

-- Procedimientos almacenados --
Delimiter $$
Create Procedure spAgregarProveedor(
    in pNombre varchar(100),
    in pTelefono int,
    in pDireccion varchar (100),
    in pEmail varchar (100)
)
Begin
    insert into Proveedores  (nombre_proveedor, telefono_proveedor, direccion, email_proveedor)
    values (pNombre, pTelefono, pDireccion, pEmail);
End$$
Delimiter ;

Delimiter $$
Create procedure spVerProveedores()
Begin
    Select * From Proveedores Order By id_proveedor;
End$$
Delimiter ;

DELIMITER $$
CREATE PROCEDURE spBuscarProveedorPorId(
    IN pId INT
)
BEGIN
    SELECT * FROM Proveedores WHERE id_proveedor = pId;
END$$
DELIMITER ;

Delimiter $$
Create procedure spActualizarProveedor(
	in pId int,
    in PNombre varchar(100),
    in pTelefono int,
    in pDireccion varchar(100),
    in pEmail varchar(100)
)
Begin
	Update Proveedores
    Set nombre_proveedor = pNombre,
		telefono_proveedor = pTelefono,
        direccion = pDireccion,
        email_proveedor = pEmail
	Where id_proveedor = pId;
End$$
Delimiter ;

Delimiter $$
Create procedure spEliminarProveedor(
	in pId int
)
Begin
	Delete From Proveedores Where id_proveedor = pId;
End$$
Delimiter ;

DELIMITER $$
CREATE PROCEDURE spAgregarEmpleado(
    IN pNombre VARCHAR(60),
    IN pApellido VARCHAR(60),
    IN pPuesto VARCHAR(20),
    IN pEmail VARCHAR(100)
)
BEGIN
    INSERT INTO Empleados (nombre_empleado, apellido_empleado, puesto_empleado, email_empleado)
    VALUES (pNombre, pApellido, pPuesto, pEmail);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE spVerEmpleados()
BEGIN
    SELECT * FROM Empleados ORDER BY id_empleado;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE spBuscarEmpleadoPorId(
    IN pId INT
)
BEGIN
    SELECT * FROM Empleados WHERE id_empleado = pId;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE spActualizarEmpleado(
    IN pId INT,
    IN pNombre VARCHAR(60),
    IN pApellido VARCHAR(60),
    IN pPuesto VARCHAR(20),
    IN pEmail VARCHAR(100)
)
BEGIN
    UPDATE Empleados 
    SET nombre_empleado = pNombre,
        apellido_empleado = pApellido,
        puesto_empleado = pPuesto,
        email_empleado = pEmail
    WHERE id_empleado = pId;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE spEliminarEmpleado(
    IN pId INT
)
BEGIN
    DELETE FROM Empleados WHERE id_empleado = pId;
END$$
DELIMITER ;

Delimiter $$
Create procedure spAgregarRepuesto(
	in pNombre varchar(100),
    in pCategoria varchar (100),
    in pPrecioCompra double,
    in pPrecioVenta double,
    in pIdProveedor int
)
Begin
	insert into Repuestos (nombre_repuesto, categoria_repuesto, precio_compra, precio_venta, id_proveedor)
    values (pNombre, pCategoria, pPrecioCompra, pPrecioVenta, pIdProveedor);
End$$
Delimiter ;

Delimiter $$
Create procedure spVerRepuestos()
Begin
	Select r.*, p.nombre_proveedor
    From Repuestos r
    Inner Join Proveedores p On r.id_proveedor = p.id_proveedor
    Order by r.id_repuesto;
End$$
Delimiter ;

DELIMITER $$
CREATE PROCEDURE spBuscarRepuestoPorId(
    IN pId INT
)
BEGIN
    SELECT r.*, p.nombre_proveedor
    FROM Repuestos r
    INNER JOIN Proveedores p ON r.id_proveedor = p.id_proveedor
    WHERE r.id_repuesto = pId;
END$$
DELIMITER ;

Delimiter $$
Create procedure spActualizarRepuesto(
	in pId int,
    in pNombre varchar(100),
    in pCategoria varchar(100),
    in pPrecioCompra double,
    in pPrecioVenta double,
    in pIdProveedor int
)
Begin
	Update Repuestos
    Set nombre_repuesto = pNombre,
		categoria_repuesto = pCategoria,
        precio_compra = pPrecioCompra,
        precio_venta = pPrecioVenta,
        id_proveedor = pIdProveedor
	Where id_repuesto = pId;
End$$
Delimiter ;

Delimiter $$
Create procedure spEliminarRepuesto(
	in pId int
)
Begin
	Delete From Repuestos Where id_repuesto = pId;
End$$
Delimiter ;

DELIMITER $$
CREATE PROCEDURE spAgregarVenta(
    IN pFecha DATE,
    IN pCantidad INT,
    IN pTotal DOUBLE,
    IN pIdEmpleado INT,
    IN pIdRepuesto INT
)
BEGIN
    INSERT INTO Ventas (fecha_venta, cantidad, total, id_empleado, id_repuesto)
    VALUES (pFecha, pCantidad, pTotal, pIdEmpleado, pIdRepuesto);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE spVerVentas()
BEGIN
    SELECT v.*, 
           CONCAT(e.nombre_empleado, ' ', e.apellido_empleado) AS empleado,
           r.nombre_repuesto
    FROM Ventas v
    INNER JOIN Empleados e ON v.id_empleado = e.id_empleado
    INNER JOIN Repuestos r ON v.id_repuesto = r.id_repuesto
    ORDER BY v.fecha_venta DESC;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE spActualizarVenta(
    IN pId INT,
    IN pFecha DATE,
    IN pCantidad INT,
    IN pTotal DOUBLE,
    IN pIdEmpleado INT,
    IN pIdRepuesto INT
)
BEGIN
    UPDATE Ventas 
    SET fecha_venta = pFecha,
        cantidad = pCantidad,
        total = pTotal,
        id_empleado = pIdEmpleado,
        id_repuesto = pIdRepuesto
    WHERE id_venta = pId;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE spEliminarVenta(
    IN pId INT
)
BEGIN
    DELETE FROM Ventas WHERE id_venta = pId;
END$$
DELIMITER ;

-- Registros usando procedimientos almacenados --

-- Proveedores
CALL spAgregarProveedor('AutoPartes Guatemala S.A.', 22345678, 'Zona 10, Ciudad de Guatemala', 'ventas@autopartes.com.gt');
CALL spAgregarProveedor('Repuestos El Motor', 23456789, 'Zona 1, Ciudad de Guatemala', 'info@elmotor.com');
CALL spAgregarProveedor('Distribuidora Central', 24567890, 'Zona 12, Ciudad de Guatemala', 'contacto@distcentral.com');
CALL spAgregarProveedor('Importadora Universal', 25678901, 'Villa Nueva, Guatemala', 'ventas@universal.com.gt');
CALL spAgregarProveedor('Frenos y Suspensiones GT', 26789012, 'Mixco, Guatemala', 'info@frenosgt.com');
CALL spAgregarProveedor('Lubricantes del Pacífico', 27890123, 'Escuintla, Guatemala', 'ventas@lubpacifico.com');
CALL spAgregarProveedor('Accesorios Premium Auto', 28901234, 'Zona 9, Ciudad de Guatemala', 'premium@autoacc.com');
CALL spAgregarProveedor('Baterías y Electricidad SA', 29012345, 'Zona 7, Ciudad de Guatemala', 'contacto@bateriasgt.com');
CALL spAgregarProveedor('Llantas Continental GT', 30123456, 'Carretera a El Salvador', 'info@llantasgt.com');
CALL spAgregarProveedor('Filtros Industriales', 31234567, 'Zona 11, Ciudad de Guatemala', 'ventas@filtrosind.com');

-- Empleados
CALL spAgregarEmpleado('Carlos', 'Méndez', 'Gerente', 'carlos.mendez@empresa.com');
CALL spAgregarEmpleado('María', 'López', 'Vendedor', 'maria.lopez@empresa.com');
CALL spAgregarEmpleado('José', 'Hernández', 'Vendedor', 'jose.hernandez@empresa.com');
CALL spAgregarEmpleado('Ana', 'García', 'Cajero', 'ana.garcia@empresa.com');
CALL spAgregarEmpleado('Luis', 'Rodríguez', 'Vendedor', 'luis.rodriguez@empresa.com');
CALL spAgregarEmpleado('Carmen', 'Martínez', 'Supervisor', 'carmen.martinez@empresa.com');
CALL spAgregarEmpleado('Pedro', 'González', 'Vendedor', 'pedro.gonzalez@empresa.com');
CALL spAgregarEmpleado('Sofía', 'Pérez', 'Cajero', 'sofia.perez@empresa.com');
CALL spAgregarEmpleado('Miguel', 'Ramírez', 'Vendedor', 'miguel.ramirez@empresa.com');
CALL spAgregarEmpleado('Laura', 'Torres', 'Vendedor', 'laura.torres@empresa.com');

-- Repuestos
CALL spAgregarRepuesto('Filtro de Aceite Toyota', 'Filtros', 25.50, 45.00, 1);
CALL spAgregarRepuesto('Pastillas de Freno Delanteras', 'Frenos', 120.00, 180.00, 5);
CALL spAgregarRepuesto('Batería 12V 45AH', 'Eléctrico', 350.00, 500.00, 8);
CALL spAgregarRepuesto('Aceite Motor 10W-40', 'Lubricantes', 75.00, 110.00, 6);
CALL spAgregarRepuesto('Llanta 185/65 R15', 'Llantas', 280.00, 420.00, 9);
CALL spAgregarRepuesto('Amortiguador Delantero', 'Suspensión', 200.00, 320.00, 5);
CALL spAgregarRepuesto('Bujías NGK (set 4)', 'Encendido', 45.00, 75.00, 2);
CALL spAgregarRepuesto('Filtro de Aire Honda', 'Filtros', 18.00, 35.00, 10);
CALL spAgregarRepuesto('Espejo Retrovisor Lateral', 'Carrocería', 85.00, 140.00, 7);
CALL spAgregarRepuesto('Bomba de Agua', 'Sistema de Enfriamiento', 150.00, 240.00, 4);

-- Ventas
CALL spAgregarVenta('2025-01-15', 2, 90.00, 2, 1);
CALL spAgregarVenta('2025-01-16', 1, 180.00, 3, 2);
CALL spAgregarVenta('2025-01-17', 1, 500.00, 5, 3);
CALL spAgregarVenta('2025-01-18', 3, 330.00, 7, 4);
CALL spAgregarVenta('2025-01-19', 4, 1680.00, 2, 5);
CALL spAgregarVenta('2025-01-20', 2, 640.00, 9, 6);
CALL spAgregarVenta('2025-01-21', 1, 75.00, 10, 7);
CALL spAgregarVenta('2025-01-22', 2, 70.00, 3, 8);
CALL spAgregarVenta('2025-01-23', 1, 140.00, 5, 9);
CALL spAgregarVenta('2025-01-24', 2, 480.00, 7, 10);