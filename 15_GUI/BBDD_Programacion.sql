-- CREAMOS LA BASE DE DATOS (Y LA BORRAMOS EN CASO DE QUE EXISTIERA)
DROP DATABASE IF EXISTS  Mi_Aucorsa;
CREATE DATABASE Mi_Aucorsa;
USE Mi_Aucorsa;

-- Tabla Bus
CREATE TABLE Bus (
    Id_B VARCHAR(10) PRIMARY KEY,
    Tipo VARCHAR(20),
    Licencia VARCHAR(20)
);

-- Tabla Conductor
CREATE TABLE Conductor (
    Id_C INT PRIMARY KEY,
    Nombre VARCHAR(50),
    Apellido VARCHAR(50)
);

-- Tabla Lugar
CREATE TABLE Lugar (
    Id_L INT PRIMARY KEY,
    Cod_Postal VARCHAR(10),
    Ciudad VARCHAR(50),
    Ubicacion VARCHAR(50)
);

-- Tabla intermedia B-C-L (relación ternaria)
CREATE TABLE BCL (
    Id_B VARCHAR(10),
    Id_C INT,
    Id_L INT,
    Dia VARCHAR(15),
    PRIMARY KEY (Id_B, Id_C, Id_L),
    FOREIGN KEY (Id_B) REFERENCES Bus(Id_B),
    FOREIGN KEY (Id_C) REFERENCES Conductor(Id_C),
    FOREIGN KEY (Id_L) REFERENCES Lugar(Id_L)
);

-- Insertamos buses dentro de la Tabla Bus
INSERT INTO Bus VALUES
('B001', 'Urbano', 'LIC001'),
('B002', 'Interurbano', 'LIC002'),
('B003', 'Turismo', 'LIC003'),
('B004', 'Escolar', 'LIC004'),
('B005', 'Urbano', 'LIC005'),
('B006', 'Turismo', 'LIC006'),
('B007', 'Interurbano', 'LIC007'),
('B008', 'Urbano', 'LIC008'),
('B009', 'Escolar', 'LIC009'),
('B010', 'Turismo', 'LIC010');

-- Insertamos conductores dentro de la Tabla Conductor
INSERT INTO Conductor VALUES
(101, 'Carlos', 'García'),
(102, 'Lucía', 'Pérez'),
(103, 'Manuel', 'Martín'),
(104, 'Laura', 'López'),
(105, 'Javier', 'Sánchez'),
(106, 'Marta', 'Fernández'),
(107, 'David', 'Ruiz'),
(108, 'Ana', 'Díaz'),
(109, 'Pablo', 'Gómez'),
(110, 'Elena', 'Navarro');

-- Insertamos lugares dentro de la Tabla Lugar
INSERT INTO Lugar VALUES
(1, '14001', 'Córdoba', 'Centro'),
(2, '28013', 'Madrid', 'Sol'),
(3, '41001', 'Sevilla', 'Triana'),
(4, '08001', 'Barcelona', 'Gótico'),
(5, '46001', 'Valencia', 'Carmen'),
(6, '29001', 'Málaga', 'Soho'),
(7, '03001', 'Alicante', 'Explanada'),
(8, '35001', 'Las Palmas', 'Vegueta'),
(9, '07001', 'Palma', 'Catedral'),
(10, '15001', 'A Coruña', 'Marina');

-- Insertamos datos en la relación BCL dentro de la Tabla BCL
INSERT INTO BCL VALUES
('B001', 101, 1, 'Monday'),
('B002', 102, 2, 'Tuesday'),
('B003', 103, 3, 'Wednesday'),
('B004', 104, 4, 'Thursday'),
('B005', 105, 5, 'Friday'),
('B006', 106, 6, 'Monday'),
('B007', 107, 7, 'Tuesday'),
('B008', 108, 8, 'Wednesday'),
('B009', 109, 9, 'Thursday'),
('B010', 110, 10, 'Friday');
