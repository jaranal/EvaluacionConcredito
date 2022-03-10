CREATE TABLE `Alumnos` (
	`idAlumno` VARCHAR(15) NOT NULL AUTO_INCREMENT,
	`Nombre` VARCHAR(30) NOT NULL AUTO_INCREMENT,
	`aPaterno` VARCHAR(30) NOT NULL AUTO_INCREMENT,
	`aMaterno` VARCHAR(30) NOT NULL AUTO_INCREMENT,
	`Grado` VARCHAR(15) NOT NULL AUTO_INCREMENT,
	`Direccion` VARCHAR(45) NOT NULL AUTO_INCREMENT,
	`Telefono` INT(15) NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (`idAlumno`)
);

CREATE TABLE `Grado` (
	`idGrado` VARCHAR(15) NOT NULL,
	`nomGrado` VARCHAR(30) NOT NULL,
	`Ubicacion` VARCHAR(30) NOT NULL,
	PRIMARY KEY (`idGrado`)
);

CREATE TABLE `Calificaciones` (
	`idCal` INT NOT NULL,
	`idMateria` INT(15) NOT NULL,
	`idAlumno` VARCHAR(15) NOT NULL,
	`idGrado` VARCHAR(15) NOT NULL,
	`Calificacion` INT(30) NOT NULL,
	`fecha` DATE NOT NULL,
	`Tipo` VARCHAR(15) NOT NULL,
	PRIMARY KEY (`idCal`)
);

CREATE TABLE `Materias` (
	`idMateria` VARCHAR(15) NOT NULL,
	`idGrado` VARCHAR(30) NOT NULL,
	`nombreMat` VARCHAR(30) NOT NULL,
	`nomMaestro` VARCHAR(30) NOT NULL,
	PRIMARY KEY (`idMateria`)
);

ALTER TABLE `Alumnos` ADD CONSTRAINT `Alumnos_fk0` FOREIGN KEY (`Grado`) REFERENCES `Grado`(`idGrado`);

ALTER TABLE `Grado` ADD CONSTRAINT `Grado_fk0` FOREIGN KEY (`nomGrado`) REFERENCES `Grado`(``);

ALTER TABLE `Grado` ADD CONSTRAINT `Grado_fk1` FOREIGN KEY (`Ubicacion`) REFERENCES `Grado`(``);

ALTER TABLE `Calificaciones` ADD CONSTRAINT `Calificaciones_fk0` FOREIGN KEY (`idMateria`) REFERENCES `Materias`(`idMateria`);

ALTER TABLE `Calificaciones` ADD CONSTRAINT `Calificaciones_fk1` FOREIGN KEY (`idAlumno`) REFERENCES `Alumnos`(`idAlumno`);

ALTER TABLE `Calificaciones` ADD CONSTRAINT `Calificaciones_fk2` FOREIGN KEY (`idGrado`) REFERENCES `Grado`(`idGrado`);

ALTER TABLE `Materias` ADD CONSTRAINT `Materias_fk0` FOREIGN KEY (`idGrado`) REFERENCES `Grado`(`idGrado`);





