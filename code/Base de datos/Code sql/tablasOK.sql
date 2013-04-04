SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`actividades`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`actividades` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`actividades` (
  `idActividades` INT(11) NOT NULL AUTO_INCREMENT ,
  `nAlumnos` INT(11) NULL DEFAULT NULL ,
  `descripcion` VARCHAR(123) NULL DEFAULT NULL ,
  `precioSocio` FLOAT NULL DEFAULT NULL ,
  `precioNoSocio` FLOAT NULL DEFAULT NULL ,
  `Temporada_idTemporada` INT(11) NOT NULL ,
  PRIMARY KEY (`idActividades`, `Temporada_idTemporada`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`alumno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`alumno` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`alumno` (
  `idAlumno` INT(11) NOT NULL AUTO_INCREMENT ,
  `talla` VARCHAR(12) NOT NULL ,
  `nombre` VARCHAR(45) NOT NULL ,
  `primerApellido` VARCHAR(45) NOT NULL ,
  `segundoApellido` VARCHAR(45) NOT NULL ,
  `nombrePadre` VARCHAR(100) NOT NULL ,
  `nombreMadre` VARCHAR(100) NOT NULL ,
  `numeroCuenta` VARCHAR(40) NULL DEFAULT NULL ,
  `telMovil` INT(11) NULL DEFAULT NULL ,
  `telFijo` INT(11) NULL DEFAULT NULL ,
  `observaciones` VARCHAR(300) NULL DEFAULT NULL ,
  `provincia` VARCHAR(70) NULL DEFAULT NULL ,
  `localidad` VARCHAR(45) NULL DEFAULT NULL ,
  `codigoPostal` INT(11) NULL DEFAULT NULL ,
  `colegio` VARCHAR(45) NULL DEFAULT NULL ,
  `domicilio` VARCHAR(100) NULL DEFAULT NULL ,
  `email` VARCHAR(75) NULL DEFAULT NULL ,
  `fechaNacimiento` DATE NOT NULL ,
  `Alumnocol` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`idAlumno`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`categoria` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`categoria` (
  `idCategoria` INT(11) NOT NULL AUTO_INCREMENT ,
  `tipo` VARCHAR(45) NULL DEFAULT NULL ,
  `descripcion` CHAR(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`idCategoria`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`fundacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`fundacion` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`fundacion` (
  `idFundacion` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `cp` INT(11) NULL DEFAULT NULL ,
  `ccc` VARCHAR(45) NULL DEFAULT NULL ,
  `email` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`idFundacion`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`equipo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`equipo` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`equipo` (
  `idEquipo` INT(11) NOT NULL AUTO_INCREMENT ,
  `Fundacion_idFundacion` INT(11) NOT NULL ,
  `Categoria_idCategoria` INT(11) NOT NULL ,
  `Temporada_idTemporada` INT(11) NOT NULL ,
  PRIMARY KEY (`idEquipo`, `Fundacion_idFundacion`, `Categoria_idCategoria`, `Temporada_idTemporada`) ,
  INDEX `fk_Equipo_Fundacion_idx` (`Fundacion_idFundacion` ASC) ,
  INDEX `fk_Equipo_Categoria1_idx` (`Categoria_idCategoria` ASC) ,
  CONSTRAINT `fk_Equipo_Categoria1`
    FOREIGN KEY (`Categoria_idCategoria` )
    REFERENCES `mydb`.`categoria` (`idCategoria` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipo_Fundacion`
    FOREIGN KEY (`Fundacion_idFundacion` )
    REFERENCES `mydb`.`fundacion` (`idFundacion` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`alumnoequipo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`alumnoequipo` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`alumnoequipo` (
  `Alumno_idAlumno` INT(11) NOT NULL ,
  `Equipo_idEquipo` INT(11) NOT NULL ,
  `Equipo_Fundacion_idFundacion` INT(11) NOT NULL ,
  `Equipo_Categoria_idCategoria` INT(11) NOT NULL ,
  PRIMARY KEY (`Alumno_idAlumno`, `Equipo_idEquipo`, `Equipo_Fundacion_idFundacion`, `Equipo_Categoria_idCategoria`) ,
  INDEX `fk_Alumno_has_Equipo_Equipo1_idx` (`Equipo_idEquipo` ASC, `Equipo_Fundacion_idFundacion` ASC, `Equipo_Categoria_idCategoria` ASC) ,
  INDEX `fk_Alumno_has_Equipo_Alumno1_idx` (`Alumno_idAlumno` ASC) ,
  CONSTRAINT `fk_Alumno_has_Equipo_Alumno1`
    FOREIGN KEY (`Alumno_idAlumno` )
    REFERENCES `mydb`.`alumno` (`idAlumno` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Alumno_has_Equipo_Equipo1`
    FOREIGN KEY (`Equipo_idEquipo` , `Equipo_Fundacion_idFundacion` , `Equipo_Categoria_idCategoria` )
    REFERENCES `mydb`.`equipo` (`idEquipo` , `Fundacion_idFundacion` , `Categoria_idCategoria` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`temporada`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`temporada` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`temporada` (
  `idTemporada` INT(11) NOT NULL AUTO_INCREMENT ,
  `curso` VARCHAR(45) NOT NULL ,
  `inicio` DATE NULL DEFAULT NULL ,
  `fin` DATE NULL DEFAULT NULL ,
  PRIMARY KEY (`idTemporada`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`usuario` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`usuario` (
  `idUsuario` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `primerApellido` VARCHAR(75) NOT NULL ,
  `segundoApellido` VARCHAR(45) NOT NULL ,
  `DNI` VARCHAR(12) NOT NULL ,
  `clave` VARCHAR(30) NULL DEFAULT '0000' ,
  `entrenador` TINYINT(1) NULL DEFAULT '0' ,
  `numeroCuenta` VARCHAR(45) NULL ,
  `telMovil` INT NULL ,
  `telFijo` INT NULL ,
  `email` VARCHAR(45) NULL ,
  `user` VARCHAR(45) NULL ,
  PRIMARY KEY (`idUsuario`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`grupo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`grupo` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`grupo` (
  `idGrupo` INT(11) NOT NULL AUTO_INCREMENT ,
  `n_alumnos` INT(11) NOT NULL DEFAULT '0' ,
  `Categoria_idCategoria` INT(11) NOT NULL ,
  `Usuario_idUsuario` INT(11) NOT NULL ,
  `Temporada_idTemporada` INT(11) NOT NULL ,
  PRIMARY KEY (`idGrupo`, `Categoria_idCategoria`, `Usuario_idUsuario`, `Temporada_idTemporada`) ,
  INDEX `fk_Grupo_Categoria1_idx` (`Categoria_idCategoria` ASC) ,
  INDEX `fk_Grupo_Usuario1_idx` (`Usuario_idUsuario` ASC) ,
  INDEX `fk_Grupo_Temporada1_idx` (`Temporada_idTemporada` ASC) ,
  CONSTRAINT `fk_Grupo_Categoria1`
    FOREIGN KEY (`Categoria_idCategoria` )
    REFERENCES `mydb`.`categoria` (`idCategoria` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Grupo_Temporada1`
    FOREIGN KEY (`Temporada_idTemporada` )
    REFERENCES `mydb`.`temporada` (`idTemporada` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Grupo_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario` )
    REFERENCES `mydb`.`usuario` (`idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`alumnogrupo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`alumnogrupo` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`alumnogrupo` (
  `Alumno_idAlumno` INT(11) NOT NULL ,
  `Grupo_idGrupo` INT(11) NOT NULL ,
  `Grupo_Categoria_idCategoria` INT(11) NOT NULL ,
  `Grupo_Usuario_idUsuario` INT(11) NOT NULL ,
  `Grupo_Temporada_idTemporada` INT(11) NOT NULL ,
  PRIMARY KEY (`Alumno_idAlumno`, `Grupo_idGrupo`, `Grupo_Categoria_idCategoria`, `Grupo_Usuario_idUsuario`, `Grupo_Temporada_idTemporada`) ,
  INDEX `fk_Alumno_has_Grupo_Grupo1_idx` (`Grupo_idGrupo` ASC, `Grupo_Categoria_idCategoria` ASC, `Grupo_Usuario_idUsuario` ASC, `Grupo_Temporada_idTemporada` ASC) ,
  INDEX `fk_Alumno_has_Grupo_Alumno1_idx` (`Alumno_idAlumno` ASC) ,
  CONSTRAINT `fk_Alumno_has_Grupo_Alumno1`
    FOREIGN KEY (`Alumno_idAlumno` )
    REFERENCES `mydb`.`alumno` (`idAlumno` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Alumno_has_Grupo_Grupo1`
    FOREIGN KEY (`Grupo_idGrupo` , `Grupo_Categoria_idCategoria` , `Grupo_Usuario_idUsuario` , `Grupo_Temporada_idTemporada` )
    REFERENCES `mydb`.`grupo` (`idGrupo` , `Categoria_idCategoria` , `Usuario_idUsuario` , `Temporada_idTemporada` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`alumnotemporada`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`alumnotemporada` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`alumnotemporada` (
  `Alumno_idAlumno` INT(11) NOT NULL ,
  `Alumno_Grupo_idGrupo` INT(11) NOT NULL ,
  `Alumno_Grupo_Categoria_idCategoria` INT(11) NOT NULL ,
  `Temporada_idTemporada` INT(11) NOT NULL ,
  PRIMARY KEY (`Alumno_idAlumno`, `Alumno_Grupo_idGrupo`, `Alumno_Grupo_Categoria_idCategoria`, `Temporada_idTemporada`) ,
  INDEX `fk_Alumno_has_Temporada_Temporada1_idx` (`Temporada_idTemporada` ASC) ,
  INDEX `fk_Alumno_has_Temporada_Alumno1_idx` (`Alumno_idAlumno` ASC, `Alumno_Grupo_idGrupo` ASC, `Alumno_Grupo_Categoria_idCategoria` ASC) ,
  CONSTRAINT `fk_Alumno_has_Temporada_Alumno1`
    FOREIGN KEY (`Alumno_idAlumno` )
    REFERENCES `mydb`.`alumno` (`idAlumno` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Alumno_has_Temporada_Temporada1`
    FOREIGN KEY (`Temporada_idTemporada` )
    REFERENCES `mydb`.`temporada` (`idTemporada` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`cuota`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`cuota` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`cuota` (
  `idCuota` INT(11) NOT NULL AUTO_INCREMENT ,
  `importe` FLOAT NOT NULL ,
  `fecha` DATE NOT NULL ,
  `pagado` TINYINT(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`idCuota`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`horario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`horario` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`horario` (
  `idHorario` INT(11) NOT NULL AUTO_INCREMENT ,
  `dia` VARCHAR(45) NOT NULL ,
  `horaInicio` TIME NOT NULL ,
  `horaFin` TIME NOT NULL ,
  `Grupo_idGrupo` INT(11) NOT NULL ,
  `Grupo_Categoria_idCategoria` INT(11) NOT NULL ,
  `Grupo_Usuario_idUsuario` INT(11) NOT NULL ,
  PRIMARY KEY (`idHorario`, `Grupo_idGrupo`, `Grupo_Categoria_idCategoria`, `Grupo_Usuario_idUsuario`) ,
  INDEX `fk_Horario_Grupo1_idx` (`Grupo_idGrupo` ASC, `Grupo_Categoria_idCategoria` ASC, `Grupo_Usuario_idUsuario` ASC) ,
  CONSTRAINT `fk_Horario_Grupo1`
    FOREIGN KEY (`Grupo_idGrupo` , `Grupo_Categoria_idCategoria` , `Grupo_Usuario_idUsuario` )
    REFERENCES `mydb`.`grupo` (`idGrupo` , `Categoria_idCategoria` , `Usuario_idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`pagoactividades`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`pagoactividades` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`pagoactividades` (
  `Alumno_idAlumno` INT(11) NOT NULL ,
  `Actividades_idActividades` INT(11) NOT NULL ,
  `Actividades_Temporada_idTemporada` INT(11) NOT NULL ,
  `Cuota_idCuota` INT(11) NOT NULL ,
  PRIMARY KEY (`Alumno_idAlumno`, `Actividades_idActividades`, `Actividades_Temporada_idTemporada`, `Cuota_idCuota`) ,
  INDEX `fk_Alumno_has_Actividades_Actividades1_idx` (`Actividades_idActividades` ASC, `Actividades_Temporada_idTemporada` ASC) ,
  INDEX `fk_Alumno_has_Actividades_Alumno1_idx` (`Alumno_idAlumno` ASC) ,
  INDEX `fk_PagoActividades_Cuota1_idx` (`Cuota_idCuota` ASC) ,
  CONSTRAINT `fk_Alumno_has_Actividades_Actividades1`
    FOREIGN KEY (`Actividades_idActividades` , `Actividades_Temporada_idTemporada` )
    REFERENCES `mydb`.`actividades` (`idActividades` , `Temporada_idTemporada` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Alumno_has_Actividades_Alumno1`
    FOREIGN KEY (`Alumno_idAlumno` )
    REFERENCES `mydb`.`alumno` (`idAlumno` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PagoActividades_Cuota1`
    FOREIGN KEY (`Cuota_idCuota` )
    REFERENCES `mydb`.`cuota` (`idCuota` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`pagotemporada`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`pagotemporada` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`pagotemporada` (
  `idPagoTemporada` INT(11) NOT NULL AUTO_INCREMENT ,
  `Cuota_idCuota` INT(11) NOT NULL ,
  `AlumnoTemporada_Alumno_idAlumno` INT(11) NOT NULL ,
  `AlumnoTemporada_Alumno_Grupo_idGrupo` INT(11) NOT NULL ,
  `AlumnoTemporada_Alumno_Grupo_Categoria_idCategoria` INT(11) NOT NULL ,
  `AlumnoTemporada_Temporada_idTemporada` INT(11) NOT NULL ,
  PRIMARY KEY (`idPagoTemporada`, `Cuota_idCuota`, `AlumnoTemporada_Alumno_idAlumno`, `AlumnoTemporada_Alumno_Grupo_idGrupo`, `AlumnoTemporada_Alumno_Grupo_Categoria_idCategoria`, `AlumnoTemporada_Temporada_idTemporada`) ,
  INDEX `fk_PagoTemporada_Cuota1_idx` (`Cuota_idCuota` ASC) ,
  INDEX `fk_PagoTemporada_AlumnoTemporada1_idx` (`AlumnoTemporada_Alumno_idAlumno` ASC, `AlumnoTemporada_Alumno_Grupo_idGrupo` ASC, `AlumnoTemporada_Alumno_Grupo_Categoria_idCategoria` ASC, `AlumnoTemporada_Temporada_idTemporada` ASC) ,
  CONSTRAINT `fk_PagoTemporada_AlumnoTemporada1`
    FOREIGN KEY (`AlumnoTemporada_Alumno_idAlumno` , `AlumnoTemporada_Alumno_Grupo_idGrupo` , `AlumnoTemporada_Alumno_Grupo_Categoria_idCategoria` , `AlumnoTemporada_Temporada_idTemporada` )
    REFERENCES `mydb`.`alumnotemporada` (`Alumno_idAlumno` , `Alumno_Grupo_idGrupo` , `Alumno_Grupo_Categoria_idCategoria` , `Temporada_idTemporada` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PagoTemporada_Cuota1`
    FOREIGN KEY (`Cuota_idCuota` )
    REFERENCES `mydb`.`cuota` (`idCuota` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`rango`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`rango` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`rango` (
  `Usuario_idUsuario` INT(11) NOT NULL ,
  `Equipo_idEquipo` INT(11) NOT NULL ,
  `Equipo_Fundacion_idFundacion` INT(11) NOT NULL ,
  `Equipo_Categoria_idCategoria` INT(11) NOT NULL ,
  `Equipo_Temporada_idTemporada` INT(11) NOT NULL ,
  `tipo` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`Usuario_idUsuario`, `Equipo_idEquipo`, `Equipo_Fundacion_idFundacion`, `Equipo_Categoria_idCategoria`, `Equipo_Temporada_idTemporada`) ,
  INDEX `fk_Usuario_has_Equipo_Equipo1_idx` (`Equipo_idEquipo` ASC, `Equipo_Fundacion_idFundacion` ASC, `Equipo_Categoria_idCategoria` ASC, `Equipo_Temporada_idTemporada` ASC) ,
  INDEX `fk_Usuario_has_Equipo_Usuario1_idx` (`Usuario_idUsuario` ASC) ,
  CONSTRAINT `fk_Usuario_has_Equipo_Equipo1`
    FOREIGN KEY (`Equipo_idEquipo` , `Equipo_Fundacion_idFundacion` , `Equipo_Categoria_idCategoria` , `Equipo_Temporada_idTemporada` )
    REFERENCES `mydb`.`equipo` (`idEquipo` , `Fundacion_idFundacion` , `Categoria_idCategoria` , `Temporada_idTemporada` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_has_Equipo_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario` )
    REFERENCES `mydb`.`usuario` (`idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`temporadaequipo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`temporadaequipo` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`temporadaequipo` (
  `Temporada_idTemporada` INT(11) NOT NULL ,
  `Equipo_idEquipo` INT(11) NOT NULL ,
  `Equipo_Fundacion_idFundacion` INT(11) NOT NULL ,
  `Equipo_Categoria_idCategoria` INT(11) NOT NULL ,
  `Equipo_Temporada_idTemporada` INT(11) NOT NULL ,
  PRIMARY KEY (`Temporada_idTemporada`, `Equipo_idEquipo`, `Equipo_Fundacion_idFundacion`, `Equipo_Categoria_idCategoria`, `Equipo_Temporada_idTemporada`) ,
  INDEX `fk_Temporada_has_Equipo_Equipo1_idx` (`Equipo_idEquipo` ASC, `Equipo_Fundacion_idFundacion` ASC, `Equipo_Categoria_idCategoria` ASC, `Equipo_Temporada_idTemporada` ASC) ,
  INDEX `fk_Temporada_has_Equipo_Temporada1_idx` (`Temporada_idTemporada` ASC) ,
  CONSTRAINT `fk_Temporada_has_Equipo_Equipo1`
    FOREIGN KEY (`Equipo_idEquipo` , `Equipo_Fundacion_idFundacion` , `Equipo_Categoria_idCategoria` , `Equipo_Temporada_idTemporada` )
    REFERENCES `mydb`.`equipo` (`idEquipo` , `Fundacion_idFundacion` , `Categoria_idCategoria` , `Temporada_idTemporada` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Temporada_has_Equipo_Temporada1`
    FOREIGN KEY (`Temporada_idTemporada` )
    REFERENCES `mydb`.`temporada` (`idTemporada` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `mydb` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
