-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce_shop
-- ------------------------------------------------------
-- Server version	5.6.37-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `email`
--

LOCK TABLES `email` WRITE;
insert into email(email_body,email_type,email_subject,is_deleted) values('Dear ##FIRSTNAME## ##LASTNAME##,

Your order has been successfully created and processed. Below are the details.

Total items in order : ##TOTALITEMS##
Total price : ##TOTALPRICE##

Feel free to contact us in case of any queries. Thanks


 Regards,

 ECommerce Staff','ORDER COFIRMATION EMAIL','Your order has been placed successfully',0);
insert into email(email_body,email_type,email_subject,is_deleted) values('Hi ##firstName## ##lastName##,<br><br>

Your order has been canceled. The order details are: <br><br>

<b>Order No: </b> ##orderNumber##<br>
<b>Total Items: </b> ##totalItems##<br>
<b>Item Names: </b> ##itemNames##<br>
<b>Total Price: </b> ##orderTotalPrice##<br><br>

Thanks, <br><br>

ECommerce Staff',

'CANCEL ORDER','Your order is cancelled',0);

/*!40000 ALTER TABLE `email` DISABLE KEYS */;
/*!40000 ALTER TABLE `email` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `store`
--
LOCK TABLES `store` WRITE;
INSERT INTO `store` (`zipcode`, `name`, `created_at`, `updated_at`, `is_deleted`) VALUES ('90001', 'Gap Men', '1556786', '1559896', 0);
INSERT INTO `store` (`zipcode`, `name`, `created_at`, `updated_at`, `is_deleted`) VALUES ('90001', 'Gap Women', '1536893', '1545464', 0);
INSERT INTO `store` (`zipcode`, `name`, `created_at`, `updated_at`, `is_deleted`) VALUES ('90002', 'Macy\'s Women & Men', '1788963', '1796565', 0);
INSERT INTO `store` (`zipcode`, `name`, `created_at`, `updated_at`, `is_deleted`) VALUES ('90001', 'Gap outlet', '1772574', '1866767', 0);
INSERT INTO `store` (`zipcode`, `name`, `created_at`, `updated_at`, `is_deleted`) VALUES ('90001', 'Macy\'s outlet', '1567383', '1576156', 0);
INSERT INTO `store` (`zipcode`, `name`, `created_at`, `updated_at`, `is_deleted`) VALUES ('90001', 'Macy\'s New Collection', '1998377', '1999991', 0);
INSERT INTO `store` (`zipcode`, `name`, `created_at`, `updated_at`, `is_deleted`) VALUES ('90002', 'Macy\'s Men', '1555516', '1665155', 0);
INSERT INTO `store` (`zipcode`, `name`, `created_at`, `updated_at`, `is_deleted`) VALUES ('90601', 'Macy\'s Shoes', '2587652', '2789768', 0);
INSERT INTO `store` (`zipcode`, `name`, `created_at`, `updated_at`, `is_deleted`) VALUES ('90601', 'Macy\'s Leather', '2587652', '2889768', 0);
INSERT INTO `store` (`zipcode`, `name`, `created_at`, `updated_at`, `is_deleted`) VALUES ('90602', 'Gap Shoes', '2087152', '2289348', 0);
UPDATE `store` SET `zipcode`='92108', `name`='Macy\'s Mission Valley Home' WHERE `id`='1';
UPDATE `store` SET `zipcode`='92108', `name`='Macy\'s Fashion Valley' WHERE `id`='2';
UPDATE `store` SET `zipcode`='92101', `name`='Macy\'s Westfield Horton Plaza' WHERE `id`='3';
UPDATE `store` SET `zipcode`='92122', `name`='Macy\'s Westfield UTC' WHERE `id`='4';
UPDATE `store` SET `zipcode`='95678', `name`='Macy\'s Furniture Gallery Roseville Furniture' WHERE `id`='5';
UPDATE `store` SET `zipcode`='95678', `name`='Macy\'s Westfield Galleria At Roseville' WHERE `id`='6';
UPDATE `store` SET `zipcode`='90067', `name`='Macy\'s Century City' WHERE `id`='7';
UPDATE `store` SET `zipcode`='90064', `name`='Macy\'s Westside Pavilion' WHERE `id`='8';
UPDATE `store` SET `zipcode`='90064', `name`='Macy\'s Furniture Gallery Westside Pavilion Furniture Store' WHERE `id`='9';
UPDATE `store` SET `zipcode`='90017', `name`='Macy\'s On The Bloc' WHERE `id`='10';
UPDATE `store` SET `address`='1555 Camino de La Reina San Diego, CA' WHERE `id`='1';
UPDATE `store` SET `address`='7017 Friars Road San Diego, CA' WHERE `id`='2';
UPDATE `store` SET `address`='160 Horton Plaza San Diego, CA' WHERE `id`='3';
UPDATE `store` SET `address`='4333 La Jolla Village Drive San Diego, CA' WHERE `id`='4';
UPDATE `store` SET `address`='1152 Galleria Blvd. Roseville, CA' WHERE `id`='5';
UPDATE `store` SET `address`='1197 Galleria Blvd. Roseville, CA' WHERE `id`='6';
UPDATE `store` SET `address`='10250 Santa Monica Boulevard Los Angeles, CA' WHERE `id`='7';
UPDATE `store` SET `address`='10730 W. Pico Blvd. Los Angeles, CA' WHERE `id`='8';
UPDATE `store` SET `address`='10800 West Pico Blvd Los Angeles, CA' WHERE `id`='9';
UPDATE `store` SET `address`='750 West 7th Street Los Angeles, CA' WHERE `id`='10';
UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-02  9:45:25
