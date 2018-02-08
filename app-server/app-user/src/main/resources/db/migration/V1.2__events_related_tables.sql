CREATE TABLE IF NOT EXISTS `event_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS`app_modules` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `is_active` TINYINT(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS`event` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `payload` MEDIUMTEXT NOT NULL,
  `type_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `module_id` INT(11) NOT NULL,
  `is_processed` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_by_idx` (`user_id` ASC),
  INDEX `fk_module_id_by_idx` (`module_id` ASC),
  INDEX `fk_event_type_id_by_idx` (`type_id` ASC),
  CONSTRAINT `fk_event_type_id_by`
    FOREIGN KEY (`type_id`)
    REFERENCES `ecommerce_user`.`event_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_module_id_by`
    FOREIGN KEY (`module_id`)
    REFERENCES `ecommerce_user`.`app_modules` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id_by`
    FOREIGN KEY (`user_id`)
    REFERENCES `ecommerce_user`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);