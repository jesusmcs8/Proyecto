SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `Baloncesto` ;
CREATE SCHEMA IF NOT EXISTS `Baloncesto` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
SHOW WARNINGS;
USE `Baloncesto` ;

-- -----------------------------------------------------
-- Table `Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Usuario` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Usuario` (
  `DNI` VARCHAR(9) NOT NULL ,
  `nombre` VARCHAR(30) NOT NULL ,
  `clave` VARCHAR(35) NULL DEFAULT '0000' ,
  `apellidos` VARCHAR(55) NOT NULL ,
  PRIMARY KEY (`DNI`) )
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
  `Apellidos` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idAlumno`) )
ENGINE = InnoDB;

SHOW WARNINGS;
USE `Baloncesto` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
