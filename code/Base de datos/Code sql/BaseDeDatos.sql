SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
SHOW WARNINGS;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `Fundacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Fundacion` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Fundacion` (
  `idFundacion` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `cp` INT NULL ,
  `ccc` VARCHAR(45) NULL ,
  `email` VARCHAR(45) NULL ,
  PRIMARY KEY (`idFundacion`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Usuario` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `apellido` VARCHAR(75) NULL ,
  `DNI` VARCHAR(12) NOT NULL ,
  `clave` VARCHAR(30) NULL DEFAULT '0000' ,
  `entrenador` TINYINT(1) NULL DEFAULT false ,
  PRIMARY KEY (`idUsuario`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Categoria` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Categoria` (
  `idCategoria` INT NOT NULL AUTO_INCREMENT ,
  `tipo` VARCHAR(45) NULL ,
  `descripcion` CHAR NULL ,
  PRIMARY KEY (`idCategoria`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Equipo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Equipo` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Equipo` (
  `idEquipo` INT NOT NULL AUTO_INCREMENT ,
  `Fundacion_idFundacion` INT NOT NULL ,
  `Categoria_idCategoria` INT NOT NULL ,
  `Temporada_idTemporada` INT NOT NULL ,
  PRIMARY KEY (`idEquipo`, `Fundacion_idFundacion`, `Categoria_idCategoria`, `Temporada_idTemporada`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Grupo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Grupo` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Grupo` (
  `idGrupo` INT NOT NULL AUTO_INCREMENT ,
  `n_alumnos` INT NOT NULL DEFAULT 0 ,
  `Categoria_idCategoria` INT NOT NULL ,
  `Usuario_idUsuario` INT NOT NULL ,
  PRIMARY KEY (`idGrupo`, `Categoria_idCategoria`, `Usuario_idUsuario`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Alumno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Alumno` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Alumno` (
  `idAlumno` INT NOT NULL ,
  `nombre` VARCHAR(45) NOT NULL ,
  `Grupo_idGrupo` INT NOT NULL ,
  `Grupo_Categoria_idCategoria` INT NOT NULL ,
  PRIMARY KEY (`idAlumno`, `Grupo_idGrupo`, `Grupo_Categoria_idCategoria`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Alumno_has_Equipo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Alumno_has_Equipo` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Alumno_has_Equipo` (
  `Alumno_idAlumno` INT NOT NULL ,
  `Equipo_idEquipo` INT NOT NULL ,
  `Equipo_Fundacion_idFundacion` INT NOT NULL ,
  `Equipo_Categoria_idCategoria` INT NOT NULL ,
  PRIMARY KEY (`Alumno_idAlumno`, `Equipo_idEquipo`, `Equipo_Fundacion_idFundacion`, `Equipo_Categoria_idCategoria`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Temporada`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Temporada` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Temporada` (
  `idTemporada` INT NOT NULL ,
  `curso` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idTemporada`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Rango`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rango` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Rango` (
  `Usuario_idUsuario` INT NOT NULL ,
  `Equipo_idEquipo` INT NOT NULL ,
  `Equipo_Fundacion_idFundacion` INT NOT NULL ,
  `Equipo_Categoria_idCategoria` INT NOT NULL ,
  `Equipo_Temporada_idTemporada` INT NOT NULL ,
  `tipo` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`Usuario_idUsuario`, `Equipo_idEquipo`, `Equipo_Fundacion_idFundacion`, `Equipo_Categoria_idCategoria`, `Equipo_Temporada_idTemporada`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Temporada_has_Equipo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Temporada_has_Equipo` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Temporada_has_Equipo` (
  `Temporada_idTemporada` INT NOT NULL ,
  `Equipo_idEquipo` INT NOT NULL ,
  `Equipo_Fundacion_idFundacion` INT NOT NULL ,
  `Equipo_Categoria_idCategoria` INT NOT NULL ,
  `Equipo_Temporada_idTemporada` INT NOT NULL ,
  PRIMARY KEY (`Temporada_idTemporada`, `Equipo_idEquipo`, `Equipo_Fundacion_idFundacion`, `Equipo_Categoria_idCategoria`, `Equipo_Temporada_idTemporada`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Cuota`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Cuota` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Cuota` (
  `idcuota` INT NOT NULL ,
  `importe` FLOAT NULL ,
  `pagado` TINYINT(1) NULL ,
  PRIMARY KEY (`idcuota`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Actividades`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Actividades` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Actividades` (
  `idActividades` INT NOT NULL AUTO_INCREMENT ,
  `nAlumnos` INT NULL ,
  `descripcion` VARCHAR(123) NULL ,
  `precioSocio` FLOAT NULL ,
  `precioNoSocio` FLOAT NULL ,
  PRIMARY KEY (`idActividades`) )
ENGINE = InnoDB;

SHOW WARNINGS;
USE `mydb` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
