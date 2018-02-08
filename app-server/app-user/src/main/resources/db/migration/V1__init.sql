-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `email_address` VARCHAR(50) NOT NULL,
  `password` VARCHAR(44) NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `auth_token` VARCHAR(100) NULL DEFAULT NULL,
  `token_validity` INT(11) NULL DEFAULT NULL,
  `created_at` INT(11) NOT NULL,
  `updated_at` INT(11) NULL DEFAULT NULL,
  `store_id` INT(11) NULL,
  `is_deleted` BIT(1) NOT NULL,
  `is_guest` BIT(1) NOT NULL,
  `reset_token` VARCHAR(100) NULL DEFAULT NULL,
  `token_created_at` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `country` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `abbreviation` VARCHAR(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 246
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `address` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `address_line_1` VARCHAR(30) NOT NULL,
  `address_line_2` VARCHAR(30) CHARACTER SET 'latin1' NULL DEFAULT NULL,
  `city` VARCHAR(50) NOT NULL,
  `state` VARCHAR(50) NULL DEFAULT NULL,
  `country` INT(11) NOT NULL,
  `zipcode` VARCHAR(20) NULL DEFAULT NULL,
  `phone_number` VARCHAR(20) NULL DEFAULT NULL,
  `address_type` BIT(1) NOT NULL,
  `is_default` BIT(1) NOT NULL DEFAULT b'0',
  `created_at` INT(11) NULL DEFAULT NULL,
  `updated_at` INT(11) NULL DEFAULT NULL,
  `is_deleted` BIT(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC),
  INDEX `country` (`country` ASC),
  CONSTRAINT `address_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`),
  CONSTRAINT `address_ibfk_2`
    FOREIGN KEY (`country`)
    REFERENCES `country` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `category_preference`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `category_preference` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `category_id` INT(11) NOT NULL,
  `created_at` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `email`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `email` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `email_body` TEXT NULL DEFAULT NULL,
  `email_type` VARCHAR(45) NULL DEFAULT NULL,
  `email_subject` VARCHAR(45) NULL DEFAULT NULL,
  `is_deleted` BIT(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `payment_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payment_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `preference`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `preference` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `schema_version`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `schema_version` (
  `version_rank` INT(11) NOT NULL,
  `installed_rank` INT(11) NOT NULL,
  `version` VARCHAR(50) NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  `type` VARCHAR(20) NOT NULL,
  `script` VARCHAR(1000) NOT NULL,
  `checksum` INT(11) NULL DEFAULT NULL,
  `installed_by` VARCHAR(100) NOT NULL,
  `installed_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` INT(11) NOT NULL,
  `success` TINYINT(1) NOT NULL,
  PRIMARY KEY (`version`),
  INDEX `schema_version_vr_idx` (`version_rank` ASC),
  INDEX `schema_version_ir_idx` (`installed_rank` ASC),
  INDEX `schema_version_s_idx` (`success` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `state`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `state` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `abbreviation` VARCHAR(10) NOT NULL,
  `country_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `country_id` (`country_id` ASC),
  CONSTRAINT `state_ibfk_1`
    FOREIGN KEY (`country_id`)
    REFERENCES `country` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

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


-- -----------------------------------------------------
-- Table `user_payment_cards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_payment_cards` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `payment_type_id` INT(11) NULL DEFAULT NULL,
  `user_id` INT(11) NULL DEFAULT NULL,
  `card_number` BIGINT(11) NULL DEFAULT NULL,
  `is_default` BIT(1) NULL DEFAULT NULL,
  `created_at` INT(11) NULL DEFAULT NULL,
  `updated_at` INT(11) NULL DEFAULT NULL,
  `is_deleted` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_payment_type_id_idx` (`payment_type_id` ASC),
  INDEX `fk_user_id_idx` (`user_id` ASC),
  CONSTRAINT `fk_payment_type_id`
    FOREIGN KEY (`payment_type_id`)
    REFERENCES `payment_type` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `user_preference`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_preference` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `preference_id` INT(11) NOT NULL,
  `value` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wishlist` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `user_id` INT(11) NULL DEFAULT NULL,
  `is_deleted` BIT(1) NULL DEFAULT b'0',
  `is_default` BIT(1) NULL DEFAULT b'0',
  `created_at` INT(11) NULL DEFAULT NULL,
  `updated_at` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `wishlist_user_fk_idx` (`user_id` ASC),
  CONSTRAINT `wishlist_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wishlist_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wishlist_items` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `wishlist_id` INT(11) NOT NULL,
  `is_deleted` BIT(1) NULL DEFAULT b'0',
  `item_uuid` VARCHAR(64) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `wishlist_fk_idx` (`wishlist_id` ASC),
  CONSTRAINT `wishlist_fk`
    FOREIGN KEY (`wishlist_id`)
    REFERENCES `wishlist` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;