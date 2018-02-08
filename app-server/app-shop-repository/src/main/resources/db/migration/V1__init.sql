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
-- Table `store`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `zipcode` INT(11) NULL DEFAULT NULL,
  `name` VARCHAR(75) NULL DEFAULT NULL,
  `created_at` INT(11) NULL DEFAULT NULL,
  `updated_at` INT(11) NULL DEFAULT NULL,
  `is_deleted` BIT(1) NULL DEFAULT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transaction` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `payment_type_id` INT(11) NULL DEFAULT NULL,
  `payment` DECIMAL(10,0) NOT NULL,
  `currency` VARCHAR(50) NOT NULL,
  `currency_code` VARCHAR(5) NOT NULL,
  `transaction_status` SMALLINT(6) NOT NULL,
  `transaction_id` VARCHAR(100) NULL DEFAULT NULL,
  `comments` VARCHAR(100) NULL DEFAULT NULL,
  `transaction_time` INT(11) NULL DEFAULT NULL,
  `updated_at` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orders` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NULL DEFAULT NULL,
  `total_item` INT(11) NOT NULL,
  `sub_total_price` DECIMAL(10,0) NULL DEFAULT NULL,
  `taxation` DECIMAL(10,2) NULL DEFAULT NULL,
  `order_total_price` DECIMAL(10,2) NULL DEFAULT NULL,
  `order_status` SMALLINT(6) NOT NULL,
  `address_id` INT(11) NULL DEFAULT NULL,
  `created_at` INT(11) NOT NULL,
  `updated_at` INT(11) NULL DEFAULT NULL,
  `transaction_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_orders_transaction1_idx` (`transaction_id` ASC),
  CONSTRAINT `fk_orders_transaction1`
    FOREIGN KEY (`transaction_id`)
    REFERENCES `transaction` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `order_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_item` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `order_id` INT(11) NOT NULL,
  `quantity` INT(11) NOT NULL,
  `price` DECIMAL(10,0) NOT NULL,
  `sub_total_price` DECIMAL(10,0) NOT NULL DEFAULT '0',
  `taxation` DECIMAL(10,2) NULL DEFAULT NULL,
  `total_price` DECIMAL(10,2) NULL DEFAULT NULL,
  `inventory_status` SMALLINT(6) NULL DEFAULT NULL,
  `created_at` INT(11) NOT NULL,
  `updated_at` INT(11) NULL DEFAULT NULL,
  `is_deleted` BIT(1) NOT NULL DEFAULT b'0',
  `item_uuid` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `order_id` (`order_id` ASC),
  CONSTRAINT `order_item_ibfk_1`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;