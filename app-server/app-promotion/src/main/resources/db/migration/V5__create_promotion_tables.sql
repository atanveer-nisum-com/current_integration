CREATE TABLE `discount_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `discount_type` varchar(255) DEFAULT NULL,
  `discount_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`));

  
CREATE TABLE `order_amount_discount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_amount` decimal(10,0) DEFAULT NULL,
  `to_amount` decimal(10,0) DEFAULT NULL,
  `discount_value` decimal(10,0) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `created_at` int(11) DEFAULT NULL,
  `updated_at` int(11) DEFAULT NULL,
  `start_date` int(11) DEFAULT NULL,
  `end_date` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;
  
  
 CREATE TABLE `promotion_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `promotion_name` varchar(255) DEFAULT NULL,
  `promotion_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;
 
 
 CREATE TABLE `promotion_discount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `promotion_type_id` int(11) DEFAULT NULL,
  `discount_value` varchar(6) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `created_at` int(11) DEFAULT NULL,
  `updated_at` int(11) DEFAULT NULL,
  `discount_type` varchar(255) DEFAULT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `promotion_type_id` (`promotion_type_id`),
  CONSTRAINT `promotion_discount_ibfk_1` FOREIGN KEY 
  (`promotion_type_id`) REFERENCES `promotion_type` (`id`)
) ;

CREATE TABLE `product_promotion_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` varchar(255) DEFAULT NULL,
  `promotion_id` int(11) DEFAULT NULL,
  `discount_value` varchar(6) DEFAULT NULL,
  `created_at` int(11) DEFAULT NULL,
  `updated_at` int(11) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `product_promotion_link_ibfk_2` FOREIGN KEY (`promotion_id`) REFERENCES `promotion_discount` (`id`)
);