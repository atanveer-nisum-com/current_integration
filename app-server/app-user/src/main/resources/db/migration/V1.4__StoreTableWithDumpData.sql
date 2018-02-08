use ecommerce_user;

DROP TABLE IF EXISTS `store`;

CREATE TABLE `store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `zipcode` int(11) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `created_at` int(11) DEFAULT NULL,
  `updated_at` int(11) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;


LOCK TABLES `store` WRITE;

INSERT INTO `store` VALUES (1,92108,'Macy\'s Mission Valley Home',1556786,1559896,'\0','1555 Camino de La Reina San Diego, CA'),(2,92108,'Macy\'s Fashion Valley',1536893,1545464,'\0','7017 Friars Road San Diego, CA'),(3,92101,'Macy\'s Westfield Horton Plaza',1788963,1796565,'\0','160 Horton Plaza San Diego, CA'),(4,92122,'Macy\'s Westfield UTC',1772574,1866767,'\0','4333 La Jolla Village Drive San Diego, CA'),(5,95678,'Macy\'s Furniture Gallery Roseville Furniture',1567383,1576156,'\0','1152 Galleria Blvd. Roseville, CA'),(6,95678,'Macy\'s Westfield Galleria At Roseville',1998377,1999991,'\0','1197 Galleria Blvd. Roseville, CA'),(7,90067,'Macy\'s Century City',1555516,1665155,'\0','10250 Santa Monica Boulevard Los Angeles, CA'),(8,90064,'Macy\'s Westside Pavilion',2587652,2789768,'\0','10730 W. Pico Blvd. Los Angeles, CA'),(9,90064,'Macy\'s Furniture Gallery Westside Pavilion Furniture Store',2587652,2889768,'\0','10800 West Pico Blvd Los Angeles, CA'),(10,90017,'Macy\'s On The Bloc',2087152,2289348,'\0','750 West 7th Street Los Angeles, CA');

UNLOCK TABLES;

ALTER TABLE `ecommerce_user`.`user` 
ADD INDEX `fk_store_id_by_user_idx` (`store_id` ASC);
ALTER TABLE `ecommerce_user`.`user` 
ADD CONSTRAINT `fk_store_id_by_user`
  FOREIGN KEY (`store_id`)
  REFERENCES `ecommerce_user`.`store` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

