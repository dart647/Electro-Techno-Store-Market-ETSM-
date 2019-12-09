-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db_etsm
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_etsm
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_etsm` DEFAULT CHARACTER SET utf8 ;
USE `db_etsm` ;

-- -----------------------------------------------------
-- Table `db_etsm`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_etsm`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `roles` SET("USER", "ADMIN", "MANAGER") NOT NULL,
  `active` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_etsm`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_etsm`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `desc` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_etsm`.`subCategory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_etsm`.`subCategory` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `attributes` SET("") NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`, `category_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `fk_subCategory_category1_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_subCategory_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `db_etsm`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_etsm`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_etsm`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` MEDIUMTEXT NOT NULL,
  `price` INT NOT NULL,
  `desc` LONGTEXT NULL,
  `subCategory_id` INT NOT NULL,
  PRIMARY KEY (`id`, `subCategory_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `fk_product_subCategory1_idx` (`subCategory_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_subCategory1`
    FOREIGN KEY (`subCategory_id`)
    REFERENCES `db_etsm`.`subCategory` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_etsm`.`userInfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_etsm`.`userInfo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fio` VARCHAR(45) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `birthdate` DATE NULL,
  `adress` LONGTEXT NULL,
  `loyaltyCode` VARCHAR(10) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `loyaltyCode_UNIQUE` (`loyaltyCode` ASC) VISIBLE,
  INDEX `fk_userInfo_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_userInfo_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `db_etsm`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_etsm`.`sales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_etsm`.`sales` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `price` INT NOT NULL,
  `discount` INT NOT NULL,
  `nds` INT NOT NULL DEFAULT 18,
  `sum` INT NOT NULL,
  `userInfo_id` INT NOT NULL,
  PRIMARY KEY (`id`, `userInfo_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_sales_userInfo1_idx` (`userInfo_id` ASC) VISIBLE,
  CONSTRAINT `fk_sales_userInfo1`
    FOREIGN KEY (`userInfo_id`)
    REFERENCES `db_etsm`.`userInfo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_etsm`.`sales_has_product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_etsm`.`sales_has_product` (
  `sales_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`sales_id`, `product_id`),
  INDEX `fk_sales_has_product_product1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_sales_has_product_sales1_idx` (`sales_id` ASC) VISIBLE,
  CONSTRAINT `fk_sales_has_product_sales1`
    FOREIGN KEY (`sales_id`)
    REFERENCES `db_etsm`.`sales` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sales_has_product_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `db_etsm`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE USER 'admin' IDENTIFIED BY '04011999dartdante647';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `db_etsm`.* TO 'admin';
GRANT ALL ON `db_etsm`.* TO 'admin';
GRANT EXECUTE ON ROUTINE `db_etsm`.* TO 'admin';
GRANT SELECT, INSERT, TRIGGER ON TABLE `db_etsm`.* TO 'admin';
GRANT SELECT ON TABLE `db_etsm`.* TO 'admin';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
