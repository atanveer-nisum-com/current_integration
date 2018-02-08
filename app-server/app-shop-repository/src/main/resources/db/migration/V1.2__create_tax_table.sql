-- -----------------------------------------------------
-- Table `tax`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tax` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `state` VARCHAR(50) NOT NULL,
  `abbrv` CHAR(5) NOT NULL,
  `percentage` DECIMAL(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;