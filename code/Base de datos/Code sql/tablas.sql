SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Fundacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Fundacion` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Fundacion` (
  `idFundacion` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `cp` INT NULL ,
  `ccc` VARCHAR(45) NULL ,
  `email` VARCHAR(45) NULL ,
  PRIMARY KEY (`idFundacion`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Usuario` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `primerApellido` VARCHAR(75) NOT NULL ,
  `segundoApellido` VARCHAR(45) NOT NULL ,
  `DNI` VARCHAR(12) NOT NULL ,
  `clave` VARCHAR(30) NULL DEFAULT '0000' ,
  `entrenador` TINYINT(1) NULL DEFAULT false ,
  `numeroCuenta` VARCHAR(45) NULL ,
  `telMovil` INT NULL ,
  `telFijo` INT NULL ,
  `email` VARCHAR(45) NULL ,
  PRIMARY KEY (`idUsuario`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Categoria` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Categoria` (
  `idCategoria` INT NOT NULL AUTO_INCREMENT ,
  `tipo` VARCHAR(45) NULL ,
  `descripcion` CHAR NULL ,
  PRIMARY KEY (`idCategoria`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Equipo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Equipo` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Equipo` (
  `idEquipo` INT NOT NULL AUTO_INCREMENT ,
  `Fundacion_idFundacion` INT NOT NULL ,
  `Categoria_idCategoria` INT NOT NULL ,
  `Temporada_idTemporada` INT NOT NULL ,
  PRIMARY KEY (`idEquipo`, `Fundacion_idFundacion`, `Categoria_idCategoria`, `Temporada_idTemporada`) ,
  INDEX `fk_Equipo_Fundacion_idx` (`Fundacion_idFundacion` ASC) ,
  INDEX `fk_Equipo_Categoria1_idx` (`Categoria_idCategoria` ASC) ,
  CONSTRAINT `fk_Equipo_Fundacion`
    FOREIGN KEY (`Fundacion_idFundacion` )
    REFERENCES `mydb`.`Fundacion` (`idFundacion` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipo_Categoria1`
    FOREIGN KEY (`Categoria_idCategoria` )
    REFERENCES `mydb`.`Categoria` (`idCategoria` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Temporada`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Temporada` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Temporada` (
  `idTemporada` INT NOT NULL AUTO_INCREMENT ,
  `curso` VARCHAR(45) NOT NULL ,
  `inicio` DATE NULL ,
  `fin` DATE NULL ,
  PRIMARY KEY (`idTemporada`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Grupo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Grupo` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Grupo` (
  `idGrupo` INT NOT NULL AUTO_INCREMENT ,
  `n_alumnos` INT NOT NULL DEFAULT 0 ,
  `Categoria_idCategoria` INT NOT NULL ,
  `Usuario_idUsuario` INT NOT NULL ,
  `Temporada_idTemporada` INT NOT NULL ,
  PRIMARY KEY (`idGrupo`, `Categoria_idCategoria`, `Usuario_idUsuario`, `Temporada_idTemporada`) ,
  INDEX `fk_Grupo_Categoria1_idx` (`Categoria_idCategoria` ASC) ,
  INDEX `fk_Grupo_Usuario1_idx` (`Usuario_idUsuario` ASC) ,
  INDEX `fk_Grupo_Temporada1_idx` (`Temporada_idTemporada` ASC) ,
  CONSTRAINT `fk_Grupo_Categoria1`
    FOREIGN KEY (`Categoria_idCategoria` )
    REFERENCES `mydb`.`Categoria` (`idCategoria` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Grupo_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario` )
    REFERENCES `mydb`.`Usuario` (`idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Grupo_Temporada1`
    FOREIGN KEY (`Temporada_idTemporada` )
    REFERENCES `mydb`.`Temporada` (`idTemporada` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Alumno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Alumno` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Alumno` (
  `idAlumno` INT NOT NULL AUTO_INCREMENT ,
  `talla` VARCHAR(12) NOT NULL ,
  `nombre` VARCHAR(45) NOT NULL ,
  `primerApellido` VARCHAR(45) NOT NULL ,
  `segundoApellido` VARCHAR(45) NOT NULL ,
  `nombrePadre` VARCHAR(100) NOT NULL ,
  `nombreMadre` VARCHAR(100) NOT NULL ,
  `numeroCuenta` VARCHAR(40) NULL ,
  `telMovil` INT NULL ,
  `telFijo` INT NULL ,
  `observaciones` VARCHAR(300) NULL ,
  `provincia` VARCHAR(70) NULL ,
  `localidad` VARCHAR(45) NULL ,
  `codigoPostal` INT NULL ,
  `colegio` VARCHAR(45) NULL ,
  `domicilio` VARCHAR(100) NULL ,
  `email` VARCHAR(75) NULL ,
  `fechaNacimiento` DATE NOT NULL ,
  `Alumnocol` VARCHAR(45) NULL ,
  PRIMARY KEY (`idAlumno`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`AlumnoEquipo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`AlumnoEquipo` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`AlumnoEquipo` (
  `Alumno_idAlumno` INT NOT NULL ,
  `Equipo_idEquipo` INT NOT NULL ,
  `Equipo_Fundacion_idFundacion` INT NOT NULL ,
  `Equipo_Categoria_idCategoria` INT NOT NULL ,
  PRIMARY KEY (`Alumno_idAlumno`, `Equipo_idEquipo`, `Equipo_Fundacion_idFundacion`, `Equipo_Categoria_idCategoria`) ,
  INDEX `fk_Alumno_has_Equipo_Equipo1_idx` (`Equipo_idEquipo` ASC, `Equipo_Fundacion_idFundacion` ASC, `Equipo_Categoria_idCategoria` ASC) ,
  INDEX `fk_Alumno_has_Equipo_Alumno1_idx` (`Alumno_idAlumno` ASC) ,
  CONSTRAINT `fk_Alumno_has_Equipo_Alumno1`
    FOREIGN KEY (`Alumno_idAlumno` )
    REFERENCES `mydb`.`Alumno` (`idAlumno` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Alumno_has_Equipo_Equipo1`
    FOREIGN KEY (`Equipo_idEquipo` , `Equipo_Fundacion_idFundacion` , `Equipo_Categoria_idCategoria` )
    REFERENCES `mydb`.`Equipo` (`idEquipo` , `Fundacion_idFundacion` , `Categoria_idCategoria` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Rango`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Rango` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Rango` (
  `Usuario_idUsuario` INT NOT NULL ,
  `Equipo_idEquipo` INT NOT NULL ,
  `Equipo_Fundacion_idFundacion` INT NOT NULL ,
  `Equipo_Categoria_idCategoria` INT NOT NULL ,
  `Equipo_Temporada_idTemporada` INT NOT NULL ,
  `tipo` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`Usuario_idUsuario`, `Equipo_idEquipo`, `Equipo_Fundacion_idFundacion`, `Equipo_Categoria_idCategoria`, `Equipo_Temporada_idTemporada`) ,
  INDEX `fk_Usuario_has_Equipo_Equipo1_idx` (`Equipo_idEquipo` ASC, `Equipo_Fundacion_idFundacion` ASC, `Equipo_Categoria_idCategoria` ASC, `Equipo_Temporada_idTemporada` ASC) ,
  INDEX `fk_Usuario_has_Equipo_Usuario1_idx` (`Usuario_idUsuario` ASC) ,
  CONSTRAINT `fk_Usuario_has_Equipo_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario` )
    REFERENCES `mydb`.`Usuario` (`idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_has_Equipo_Equipo1`
    FOREIGN KEY (`Equipo_idEquipo` , `Equipo_Fundacion_idFundacion` , `Equipo_Categoria_idCategoria` , `Equipo_Temporada_idTemporada` )
    REFERENCES `mydb`.`Equipo` (`idEquipo` , `Fundacion_idFundacion` , `Categoria_idCategoria` , `Temporada_idTemporada` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TemporadaEquipo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`TemporadaEquipo` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`TemporadaEquipo` (
  `Temporada_idTemporada` INT NOT NULL ,
  `Equipo_idEquipo` INT NOT NULL ,
  `Equipo_Fundacion_idFundacion` INT NOT NULL ,
  `Equipo_Categoria_idCategoria` INT NOT NULL ,
  `Equipo_Temporada_idTemporada` INT NOT NULL ,
  PRIMARY KEY (`Temporada_idTemporada`, `Equipo_idEquipo`, `Equipo_Fundacion_idFundacion`, `Equipo_Categoria_idCategoria`, `Equipo_Temporada_idTemporada`) ,
  INDEX `fk_Temporada_has_Equipo_Equipo1_idx` (`Equipo_idEquipo` ASC, `Equipo_Fundacion_idFundacion` ASC, `Equipo_Categoria_idCategoria` ASC, `Equipo_Temporada_idTemporada` ASC) ,
  INDEX `fk_Temporada_has_Equipo_Temporada1_idx` (`Temporada_idTemporada` ASC) ,
  CONSTRAINT `fk_Temporada_has_Equipo_Temporada1`
    FOREIGN KEY (`Temporada_idTemporada` )
    REFERENCES `mydb`.`Temporada` (`idTemporada` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Temporada_has_Equipo_Equipo1`
    FOREIGN KEY (`Equipo_idEquipo` , `Equipo_Fundacion_idFundacion` , `Equipo_Categoria_idCategoria` , `Equipo_Temporada_idTemporada` )
    REFERENCES `mydb`.`Equipo` (`idEquipo` , `Fundacion_idFundacion` , `Categoria_idCategoria` , `Temporada_idTemporada` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Actividades`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Actividades` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Actividades` (
  `idActividades` INT NOT NULL AUTO_INCREMENT ,
  `nAlumnos` INT NULL ,
  `descripcion` VARCHAR(123) NULL ,
  `precioSocio` FLOAT NULL ,
  `precioNoSocio` FLOAT NULL ,
  `Temporada_idTemporada` INT NOT NULL ,
  PRIMARY KEY (`idActividades`, `Temporada_idTemporada`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Horario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Horario` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Horario` (
  `idHorario` INT NOT NULL AUTO_INCREMENT ,
  `dia` VARCHAR(45) NOT NULL ,
  `horaInicio` TIME NOT NULL ,
  `horaFin` TIME NOT NULL ,
  `Grupo_idGrupo` INT NOT NULL ,
  `Grupo_Categoria_idCategoria` INT NOT NULL ,
  `Grupo_Usuario_idUsuario` INT NOT NULL ,
  PRIMARY KEY (`idHorario`, `Grupo_idGrupo`, `Grupo_Categoria_idCategoria`, `Grupo_Usuario_idUsuario`) ,
  INDEX `fk_Horario_Grupo1_idx` (`Grupo_idGrupo` ASC, `Grupo_Categoria_idCategoria` ASC, `Grupo_Usuario_idUsuario` ASC) ,
  CONSTRAINT `fk_Horario_Grupo1`
    FOREIGN KEY (`Grupo_idGrupo` , `Grupo_Categoria_idCategoria` , `Grupo_Usuario_idUsuario` )
    REFERENCES `mydb`.`Grupo` (`idGrupo` , `Categoria_idCategoria` , `Usuario_idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`PagoTemporada`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`PagoTemporada` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`PagoTemporada` (
  `Alumno_idAlumno` INT NOT NULL ,
  `Alumno_Grupo_idGrupo` INT NOT NULL ,
  `Alumno_Grupo_Categoria_idCategoria` INT NOT NULL ,
  `Temporada_idTemporada` INT NOT NULL ,
  PRIMARY KEY (`Alumno_idAlumno`, `Alumno_Grupo_idGrupo`, `Alumno_Grupo_Categoria_idCategoria`, `Temporada_idTemporada`) ,
  INDEX `fk_Alumno_has_Temporada_Temporada1_idx` (`Temporada_idTemporada` ASC) ,
  INDEX `fk_Alumno_has_Temporada_Alumno1_idx` (`Alumno_idAlumno` ASC, `Alumno_Grupo_idGrupo` ASC, `Alumno_Grupo_Categoria_idCategoria` ASC) ,
  CONSTRAINT `fk_Alumno_has_Temporada_Alumno1`
    FOREIGN KEY (`Alumno_idAlumno` )
    REFERENCES `mydb`.`Alumno` (`idAlumno` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Alumno_has_Temporada_Temporada1`
    FOREIGN KEY (`Temporada_idTemporada` )
    REFERENCES `mydb`.`Temporada` (`idTemporada` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Alumno_has_Grupo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Alumno_has_Grupo` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Alumno_has_Grupo` (
  `Alumno_idAlumno` INT NOT NULL ,
  `Grupo_idGrupo` INT NOT NULL ,
  `Grupo_Categoria_idCategoria` INT NOT NULL ,
  `Grupo_Usuario_idUsuario` INT NOT NULL ,
  `Grupo_Temporada_idTemporada` INT NOT NULL ,
  PRIMARY KEY (`Alumno_idAlumno`, `Grupo_idGrupo`, `Grupo_Categoria_idCategoria`, `Grupo_Usuario_idUsuario`, `Grupo_Temporada_idTemporada`) ,
  INDEX `fk_Alumno_has_Grupo_Grupo1_idx` (`Grupo_idGrupo` ASC, `Grupo_Categoria_idCategoria` ASC, `Grupo_Usuario_idUsuario` ASC, `Grupo_Temporada_idTemporada` ASC) ,
  INDEX `fk_Alumno_has_Grupo_Alumno1_idx` (`Alumno_idAlumno` ASC) ,
  CONSTRAINT `fk_Alumno_has_Grupo_Alumno1`
    FOREIGN KEY (`Alumno_idAlumno` )
    REFERENCES `mydb`.`Alumno` (`idAlumno` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Alumno_has_Grupo_Grupo1`
    FOREIGN KEY (`Grupo_idGrupo` , `Grupo_Categoria_idCategoria` , `Grupo_Usuario_idUsuario` , `Grupo_Temporada_idTemporada` )
    REFERENCES `mydb`.`Grupo` (`idGrupo` , `Categoria_idCategoria` , `Usuario_idUsuario` , `Temporada_idTemporada` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`AlumnoActividades`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`AlumnoActividades` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`AlumnoActividades` (
  `Alumno_idAlumno` INT NOT NULL ,
  `Actividades_idActividades` INT NOT NULL ,
  `Actividades_Temporada_idTemporada` INT NOT NULL ,
  PRIMARY KEY (`Alumno_idAlumno`, `Actividades_idActividades`, `Actividades_Temporada_idTemporada`) ,
  INDEX `fk_Alumno_has_Actividades_Actividades1_idx` (`Actividades_idActividades` ASC, `Actividades_Temporada_idTemporada` ASC) ,
  INDEX `fk_Alumno_has_Actividades_Alumno1_idx` (`Alumno_idAlumno` ASC) ,
  CONSTRAINT `fk_Alumno_has_Actividades_Alumno1`
    FOREIGN KEY (`Alumno_idAlumno` )
    REFERENCES `mydb`.`Alumno` (`idAlumno` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Alumno_has_Actividades_Actividades1`
    FOREIGN KEY (`Actividades_idActividades` , `Actividades_Temporada_idTemporada` )
    REFERENCES `mydb`.`Actividades` (`idActividades` , `Temporada_idTemporada` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `mydb` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
