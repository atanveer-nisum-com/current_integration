-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce_user
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
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
INSERT INTO `country`(id, name) VALUES (1,'Afghanistan'),(2,'Albania'),(3,'Algeria'),(4,'American Samoa'),(5,'Andorra'),(6,'Angola'),(7,'Anguilla'),(8,'Antarctica'),(9,'Antigua and Barbuda'),(10,'Argentina'),(11,'Armenia'),(12,'Aruba'),(13,'Australia'),(14,'Austria'),(15,'Azerbaijan'),(16,'Bahamas'),(17,'Bahrain'),(18,'Bangladesh'),(19,'Barbados'),(20,'Belarus'),(21,'Belgium'),(22,'Belize'),(23,'Benin'),(24,'Bermuda'),(25,'Bhutan'),(26,'Bolivia'),(27,'Bosnia and Herzegovina'),(28,'Botswana'),(29,'Bouvet Island'),(30,'Brazil'),(31,'British Indian Ocean Territory'),(32,'Brunei Darussalam'),(33,'Bulgaria'),(34,'Burkina Faso'),(35,'Burundi'),(36,'Cambodia'),(37,'Cameroon'),(38,'Canada'),(39,'Cape Verde'),(40,'Cayman Islands'),(41,'Central African Republic'),(42,'Chad'),(43,'Chile'),(44,'China'),(45,'Christmas Island'),(46,'Cocos (Keeling) Islands'),(47,'Colombia'),(48,'Comoros'),(49,'Congo'),(50,'Cook Islands'),(51,'Costa Rica'),(52,'Croatia (Hrvatska)'),(53,'Cuba'),(54,'Cyprus'),(55,'Czech Republic'),(56,'Denmark'),(57,'Djibouti'),(58,'Dominica'),(59,'Dominican Republic'),(60,'East Timor'),(61,'Ecuador'),(62,'Egypt'),(63,'El Salvador'),(64,'Equatorial Guinea'),(65,'Eritrea'),(66,'Estonia'),(67,'Ethiopia'),(68,'Falkland Islands (Malvinas)'),(69,'Faroe Islands'),(70,'Fiji'),(71,'Finland'),(72,'France'),(73,'France, Metropolitan'),(74,'French Guiana'),(75,'French Polynesia'),(76,'French Southern Territories'),(77,'Gabon'),(78,'Gambia'),(79,'Georgia'),(80,'Germany'),(81,'Ghana'),(82,'Gibraltar'),(83,'Guernsey'),(84,'Greece'),(85,'Greenland'),(86,'Grenada'),(87,'Guadeloupe'),(88,'Guam'),(89,'Guatemala'),(90,'Guinea'),(91,'Guinea-Bissau'),(92,'Guyana'),(93,'Haiti'),(94,'Heard and Mc Donald Islands'),(95,'Honduras'),(96,'Hong Kong'),(97,'Hungary'),(98,'Iceland'),(99,'India'),(100,'Isle of Man'),(101,'Indonesia'),(102,'Iran (Islamic Republic of)'),(103,'Iraq'),(104,'Ireland'),(105,'Israel'),(106,'Italy'),(107,'Ivory Coast'),(108,'Jersey'),(109,'Jamaica'),(110,'Japan'),(111,'Jordan'),(112,'Kazakhstan'),(113,'Kenya'),(114,'Kiribati'),(115,'Korea, Democratic People\'s Republic of'),(116,'Korea, Republic of'),(117,'Kosovo'),(118,'Kuwait'),(119,'Kyrgyzstan'),(120,'Lao People\'s Democratic Republic'),(121,'Latvia'),(122,'Lebanon'),(123,'Lesotho'),(124,'Liberia'),(125,'Libyan Arab Jamahiriya'),(126,'Liechtenstein'),(127,'Lithuania'),(128,'Luxembourg'),(129,'Macau'),(130,'Macedonia'),(131,'Madagascar'),(132,'Malawi'),(133,'Malaysia'),(134,'Maldives'),(135,'Mali'),(136,'Malta'),(137,'Marshall Islands'),(138,'Martinique'),(139,'Mauritania'),(140,'Mauritius'),(141,'Mayotte'),(142,'Mexico'),(143,'Micronesia, Federated States of'),(144,'Moldova, Republic of'),(145,'Monaco'),(146,'Mongolia'),(147,'Montenegro'),(148,'Montserrat'),(149,'Morocco'),(150,'Mozambique'),(151,'Myanmar'),(152,'Namibia'),(153,'Nauru'),(154,'Nepal'),(155,'Netherlands'),(156,'Netherlands Antilles'),(157,'New Caledonia'),(158,'New Zealand'),(159,'Nicaragua'),(160,'Niger'),(161,'Nigeria'),(162,'Niue'),(163,'Norfolk Island'),(164,'Northern Mariana Islands'),(165,'Norway'),(166,'Oman'),(167,'Pakistan'),(168,'Palau'),(169,'Palestine'),(170,'Panama');
INSERT INTO `country`(id, name) VALUES (171,'Papua New Guinea'),(172,'Paraguay'),(173,'Peru'),(174,'Philippines'),(175,'Pitcairn'),(176,'Poland'),(177,'Portugal'),(178,'Puerto Rico'),(179,'Qatar'),(180,'Reunion'),(181,'Romania'),(182,'Russian Federation'),(183,'Rwanda'),(184,'Saint Kitts and Nevis'),(185,'Saint Lucia'),(186,'Saint Vincent and the Grenadines'),(187,'Samoa'),(188,'San Marino'),(189,'Sao Tome and Principe'),(190,'Saudi Arabia'),(191,'Senegal'),(192,'Serbia'),(193,'Seychelles'),(194,'Sierra Leone'),(195,'Singapore'),(196,'Slovakia'),(197,'Slovenia'),(198,'Solomon Islands'),(199,'Somalia'),(200,'South Africa'),(201,'South Georgia South Sandwich Islands'),(202,'Spain'),(203,'Sri Lanka'),(204,'St. Helena'),(205,'St. Pierre and Miquelon'),(206,'Sudan'),(207,'Suriname'),(208,'Svalbard and Jan Mayen Islands'),(209,'Swaziland'),(210,'Sweden'),(211,'Switzerland'),(212,'Syrian Arab Republic'),(213,'Taiwan'),(214,'Tajikistan'),(215,'Tanania, United Republic of'),(216,'Thailand'),(217,'Togo'),(218,'Tokelau'),(219,'Tonga'),(220,'Trinidad and Tobago'),(221,'Tunisia'),(222,'Turkey'),(223,'Turkmenistan'),(224,'Turks and Caicos Islands'),(225,'Tuvalu'),(226,'Uganda'),(227,'Ukraine'),(228,'United Arab Emirates'),(229,'United Kingdom'),(230,'United States'),(231,'United States minor outlying islands'),(232,'Uruguay'),(233,'Uzbekistan'),(234,'Vanuatu'),(235,'Vatican City State'),(236,'Venezuela'),(237,'Vietnam'),(238,'Virgin Islands (British)'),(239,'Virgin Islands (U.S.)'),(240,'Wallis and Futuna Islands'),(241,'Western Sahara'),(242,'Yemen'),(243,'Zaire'),(244,'Zambia'),(245,'Zimbabwe');
UPDATE country set abbreviation="AF" where id=1;
UPDATE country set abbreviation="AL" where id=2;
UPDATE country set abbreviation="DZ" where id=3;
UPDATE country set abbreviation="DS" where id=4;
UPDATE country set abbreviation="AD" where id=5;
UPDATE country set abbreviation="AO" where id=6;
UPDATE country set abbreviation="AI" where id=7;
UPDATE country set abbreviation="AQ" where id=8;
UPDATE country set abbreviation="AG" where id=9;
UPDATE country set abbreviation="AR" where id=10;
UPDATE country set abbreviation="AM" where id=11;
UPDATE country set abbreviation="AW" where id=12;
UPDATE country set abbreviation="AU" where id=13;
UPDATE country set abbreviation="AT" where id=14;
UPDATE country set abbreviation="AZ" where id=15;
UPDATE country set abbreviation="BS" where id=16;
UPDATE country set abbreviation="BH" where id=17;
UPDATE country set abbreviation="BD" where id=18;
UPDATE country set abbreviation="BB" where id=19;
UPDATE country set abbreviation="BY" where id=20;
UPDATE country set abbreviation="BE" where id=21;
UPDATE country set abbreviation="BZ" where id=22;
UPDATE country set abbreviation="BJ" where id=23;
UPDATE country set abbreviation="BM" where id=24;
UPDATE country set abbreviation="BT" where id=25;
UPDATE country set abbreviation="BO" where id=26;
UPDATE country set abbreviation="BA" where id=27;
UPDATE country set abbreviation="BW" where id=28;
UPDATE country set abbreviation="BV" where id=29;
UPDATE country set abbreviation="BR" where id=30;
UPDATE country set abbreviation="IO" where id=31;
UPDATE country set abbreviation="BN" where id=32;
UPDATE country set abbreviation="BG" where id=33;
UPDATE country set abbreviation="BF" where id=34;
UPDATE country set abbreviation="BI" where id=35;
UPDATE country set abbreviation="KH" where id=36;
UPDATE country set abbreviation="CM" where id=37;
UPDATE country set abbreviation="CA" where id=38;
UPDATE country set abbreviation="CV" where id=39;
UPDATE country set abbreviation="KY" where id=40;
UPDATE country set abbreviation="CF" where id=41;
UPDATE country set abbreviation="TD" where id=42;
UPDATE country set abbreviation="CL" where id=43;
UPDATE country set abbreviation="CN" where id=44;
UPDATE country set abbreviation="CX" where id=45;
UPDATE country set abbreviation="CC" where id=46;
UPDATE country set abbreviation="CO" where id=47;
UPDATE country set abbreviation="KM" where id=48;
UPDATE country set abbreviation="CG" where id=49;
UPDATE country set abbreviation="CK" where id=50;
UPDATE country set abbreviation="CR" where id=51;
UPDATE country set abbreviation="HR" where id=52;
UPDATE country set abbreviation="CU" where id=53;
UPDATE country set abbreviation="CY" where id=54;
UPDATE country set abbreviation="CZ" where id=55;
UPDATE country set abbreviation="DK" where id=56;
UPDATE country set abbreviation="DJ" where id=57;
UPDATE country set abbreviation="DM" where id=58;
UPDATE country set abbreviation="DO" where id=59;
UPDATE country set abbreviation="TP" where id=60;
UPDATE country set abbreviation="EC" where id=61;
UPDATE country set abbreviation="EG" where id=62;
UPDATE country set abbreviation="SV" where id=63;
UPDATE country set abbreviation="GQ" where id=64;
UPDATE country set abbreviation="ER" where id=65;
UPDATE country set abbreviation="EE" where id=66;
UPDATE country set abbreviation="ET" where id=67;
UPDATE country set abbreviation="FK" where id=68;
UPDATE country set abbreviation="FO" where id=69;
UPDATE country set abbreviation="FJ" where id=70;
UPDATE country set abbreviation="FI" where id=71;
UPDATE country set abbreviation="FR" where id=72;
UPDATE country set abbreviation="FX" where id=73;
UPDATE country set abbreviation="GF" where id=74;
UPDATE country set abbreviation="PF" where id=75;
UPDATE country set abbreviation="TF" where id=76;
UPDATE country set abbreviation="GA" where id=77;
UPDATE country set abbreviation="GM" where id=78;
UPDATE country set abbreviation="GE" where id=79;
UPDATE country set abbreviation="DE" where id=80;
UPDATE country set abbreviation="GH" where id=81;
UPDATE country set abbreviation="GI" where id=82;
UPDATE country set abbreviation="GK" where id=83;
UPDATE country set abbreviation="GR" where id=84;
UPDATE country set abbreviation="GL" where id=85;
UPDATE country set abbreviation="GD" where id=86;
UPDATE country set abbreviation="GP" where id=87;
UPDATE country set abbreviation="GU" where id=88;
UPDATE country set abbreviation="GT" where id=89;
UPDATE country set abbreviation="GN" where id=90;
UPDATE country set abbreviation="GW" where id=91;
UPDATE country set abbreviation="GY" where id=92;
UPDATE country set abbreviation="HT" where id=93;
UPDATE country set abbreviation="HM" where id=94;
UPDATE country set abbreviation="HN" where id=95;
UPDATE country set abbreviation="HK" where id=96;
UPDATE country set abbreviation="HU" where id=97;
UPDATE country set abbreviation="IS" where id=98;
UPDATE country set abbreviation="IN" where id=99;
UPDATE country set abbreviation="IM" where id=100;
UPDATE country set abbreviation="ID" where id=101;
UPDATE country set abbreviation="IR" where id=102;
UPDATE country set abbreviation="IQ" where id=103;
UPDATE country set abbreviation="IE" where id=104;
UPDATE country set abbreviation="IL" where id=105;
UPDATE country set abbreviation="IT" where id=106;
UPDATE country set abbreviation="CI" where id=107;
UPDATE country set abbreviation="JE" where id=108;
UPDATE country set abbreviation="JM" where id=109;
UPDATE country set abbreviation="JP" where id=110;
UPDATE country set abbreviation="JO" where id=111;
UPDATE country set abbreviation="KZ" where id=112;
UPDATE country set abbreviation="KE" where id=113;
UPDATE country set abbreviation="KI" where id=114;
UPDATE country set abbreviation="KP" where id=115;
UPDATE country set abbreviation="KR" where id=116;
UPDATE country set abbreviation="XK" where id=117;
UPDATE country set abbreviation="KW" where id=118;
UPDATE country set abbreviation="KG" where id=119;
UPDATE country set abbreviation="LA" where id=120;
UPDATE country set abbreviation="LV" where id=121;
UPDATE country set abbreviation="LB" where id=122;
UPDATE country set abbreviation="LS" where id=123;
UPDATE country set abbreviation="LR" where id=124;
UPDATE country set abbreviation="LY" where id=125;
UPDATE country set abbreviation="LI" where id=126;
UPDATE country set abbreviation="LT" where id=127;
UPDATE country set abbreviation="LU" where id=128;
UPDATE country set abbreviation="MO" where id=129;
UPDATE country set abbreviation="MK" where id=130;
UPDATE country set abbreviation="MG" where id=131;
UPDATE country set abbreviation="MW" where id=132;
UPDATE country set abbreviation="MY" where id=133;
UPDATE country set abbreviation="MV" where id=134;
UPDATE country set abbreviation="ML" where id=135;
UPDATE country set abbreviation="MT" where id=136;
UPDATE country set abbreviation="MH" where id=137;
UPDATE country set abbreviation="MQ" where id=138;
UPDATE country set abbreviation="MR" where id=139;
UPDATE country set abbreviation="MU" where id=140;
UPDATE country set abbreviation="TY" where id=141;
UPDATE country set abbreviation="MX" where id=142;
UPDATE country set abbreviation="FM" where id=143;
UPDATE country set abbreviation="MD" where id=144;
UPDATE country set abbreviation="MC" where id=145;
UPDATE country set abbreviation="MN" where id=146;
UPDATE country set abbreviation="ME" where id=147;
UPDATE country set abbreviation="MS" where id=148;
UPDATE country set abbreviation="MA" where id=149;
UPDATE country set abbreviation="MZ" where id=150;
UPDATE country set abbreviation="MM" where id=151;
UPDATE country set abbreviation="NA" where id=152;
UPDATE country set abbreviation="NR" where id=153;
UPDATE country set abbreviation="NP" where id=154;
UPDATE country set abbreviation="NL" where id=155;
UPDATE country set abbreviation="AN" where id=156;
UPDATE country set abbreviation="NC" where id=157;
UPDATE country set abbreviation="NZ" where id=158;
UPDATE country set abbreviation="NI" where id=159;
UPDATE country set abbreviation="NE" where id=160;
UPDATE country set abbreviation="NG" where id=161;
UPDATE country set abbreviation="NU" where id=162;
UPDATE country set abbreviation="NF" where id=163;
UPDATE country set abbreviation="MP" where id=164;
UPDATE country set abbreviation="NO" where id=165;
UPDATE country set abbreviation="OM" where id=166;
UPDATE country set abbreviation="PK" where id=167;
UPDATE country set abbreviation="PW" where id=168;
UPDATE country set abbreviation="PS" where id=169;
UPDATE country set abbreviation="PA" where id=170;
UPDATE country set abbreviation="PG" where id=171;
UPDATE country set abbreviation="PY" where id=172;
UPDATE country set abbreviation="PE" where id=173;
UPDATE country set abbreviation="PH" where id=174;
UPDATE country set abbreviation="PN" where id=175;
UPDATE country set abbreviation="PL" where id=176;
UPDATE country set abbreviation="PT" where id=177;
UPDATE country set abbreviation="PR" where id=178;
UPDATE country set abbreviation="QA" where id=179;
UPDATE country set abbreviation="RE" where id=180;
UPDATE country set abbreviation="RO" where id=181;
UPDATE country set abbreviation="RU" where id=182;
UPDATE country set abbreviation="RW" where id=183;
UPDATE country set abbreviation="KN" where id=184;
UPDATE country set abbreviation="LC" where id=185;
UPDATE country set abbreviation="VC" where id=186;
UPDATE country set abbreviation="WS" where id=187;
UPDATE country set abbreviation="SM" where id=188;
UPDATE country set abbreviation="ST" where id=189;
UPDATE country set abbreviation="SA" where id=190;
UPDATE country set abbreviation="SN" where id=191;
UPDATE country set abbreviation="RS" where id=192;
UPDATE country set abbreviation="SC" where id=193;
UPDATE country set abbreviation="SL" where id=194;
UPDATE country set abbreviation="SG" where id=195;
UPDATE country set abbreviation="SK" where id=196;
UPDATE country set abbreviation="SI" where id=197;
UPDATE country set abbreviation="SB" where id=198;
UPDATE country set abbreviation="SO" where id=199;
UPDATE country set abbreviation="ZA" where id=200;
UPDATE country set abbreviation="GS" where id=201;
UPDATE country set abbreviation="ES" where id=202;
UPDATE country set abbreviation="LK" where id=203;
UPDATE country set abbreviation="SH" where id=204;
UPDATE country set abbreviation="PM" where id=205;
UPDATE country set abbreviation="SD" where id=206;
UPDATE country set abbreviation="SR" where id=207;
UPDATE country set abbreviation="SJ" where id=208;
UPDATE country set abbreviation="SZ" where id=209;
UPDATE country set abbreviation="SE" where id=210;
UPDATE country set abbreviation="CH" where id=211;
UPDATE country set abbreviation="SY" where id=212;
UPDATE country set abbreviation="TW" where id=213;
UPDATE country set abbreviation="TJ" where id=214;
UPDATE country set abbreviation="TZ" where id=215;
UPDATE country set abbreviation="TH" where id=216;
UPDATE country set abbreviation="TG" where id=217;
UPDATE country set abbreviation="TK" where id=218;
UPDATE country set abbreviation="TO" where id=219;
UPDATE country set abbreviation="TT" where id=220;
UPDATE country set abbreviation="TN" where id=221;
UPDATE country set abbreviation="TR" where id=222;
UPDATE country set abbreviation="TM" where id=223;
UPDATE country set abbreviation="TC" where id=224;
UPDATE country set abbreviation="TV" where id=225;
UPDATE country set abbreviation="UG" where id=226;
UPDATE country set abbreviation="UA" where id=227;
UPDATE country set abbreviation="AE" where id=228;
UPDATE country set abbreviation="GB" where id=229;
UPDATE country set abbreviation="US" where id=230;
UPDATE country set abbreviation="UM" where id=231;
UPDATE country set abbreviation="UY" where id=232;
UPDATE country set abbreviation="UZ" where id=233;
UPDATE country set abbreviation="VU" where id=234;
UPDATE country set abbreviation="VA" where id=235;
UPDATE country set abbreviation="VE" where id=236;
UPDATE country set abbreviation="VN" where id=237;
UPDATE country set abbreviation="VG" where id=238;
UPDATE country set abbreviation="VI" where id=239;
UPDATE country set abbreviation="WF" where id=240;
UPDATE country set abbreviation="EH" where id=241;
UPDATE country set abbreviation="YE" where id=242;
UPDATE country set abbreviation="ZR" where id=243;
UPDATE country set abbreviation="ZM" where id=244;
UPDATE country set abbreviation="ZW" where id=245;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `state`
--
LOCK TABLES `state` WRITE;
INSERT INTO `state` VALUES (1,'Alaska','AK',230),(2,'Alabama','AL',230),(3,'Arkansas','AR',230),(4,'Arizona','AZ',230),(5,'California','CA',230),(6,'Colorado','CO',230),(7,'Connecticut','CT',230),(8,'District of Columbia','DC',230),(9,'Delaware','DE',230),(10,'Florida','FL',230),(11,'Georgia','GA',230),(12,'Hawaii','HI',230),(13,'Iowa','IA',230),(14,'Idaho','ID',230),(15,'Illinois','IL',230),(16,'Indiana','IN',230),(17,'Kansas','KS',230),(18,'Kentucky','KY',230),(19,'Louisiana','LA',230),(20,'Massachusetts','MA',230),(21,'Maryland','MD',230),(22,'Maine','ME',230),(23,'Michigan','MI',230),(24,'Minnesota','MN',230),(25,'Missouri','MO',230),(26,'Mississippi','MS',230),(27,'Montana','MT',230),(28,'North Carolina','NC',230),(29,'North Dakota','ND',230),(30,'Nebraska','NE',230),(31,'New Hampshire','NH',230),(32,'New Jersey','NJ',230),(33,'New Mexico','NM',230),(34,'Nevada','NV',230),(35,'New York','NY',230),(36,'Ohio','OH',230),(37,'Oklahoma','OK',230),(38,'Oregon','OR',230),(39,'Pennsylvania','PA',230),(40,'Rhode Island','RI',230),(41,'South Carolina','SC',230),(42,'South Dakota','SD',230),(43,'Tennessee','TN',230),(44,'Texas','TX',230),(45,'Utah','UT',230),(46,'Virginia','VA',230),(47,'Vermont','VT',230),(48,'Washington','WA',230),(49,'Wisconsin','WI',230),(50,'West Virginia','WV',230),(51,'Wyoming','WY',230);
UNLOCK TABLES;

--
-- Dumping data for table `email`
--

LOCK TABLES `email` WRITE;
insert into email(email_body,email_type,email_subject,is_deleted) values('Dear ##FIRSTNAME## ##LASTNAME##,

Thank you for signing up to our ECommerce Application. You’re joining over a number of shoppers in our community.

Now that you are a member you will receive early notifications on unmatched deals. As our registered user, you will also receive exclusive discounts on some of our products.
If you have any questions or concerns you can contact our support center.

Regards,

ECommerce Staff','SIGNUP','Thank you for Registering',0);
insert into email(email_body,email_type,email_subject,is_deleted) values('Hi ##firstName## ##lastName##,<br><br>

It looks like you have lost/forgot your ECommerce Application password!<br><br>

But don’t worry! You can use the following link to reset your password:<br><br>

<a href=##contextName##/resetPassword/##token##>##contextName##/resetPassword/##token##</a><br><br>

If you don’t use this link within 24 hours, it will expire.<br><br><br>

Thanks, <br><br>

ECommerce Staff',

'FORGETPASSWORD','Please reset your password',0);
insert into email(email_body,email_type,email_subject,is_deleted) values('Hi ##firstName## ##lastName##,

We wanted to let you know that your ECommerce Application password has been changed.

Please do not reply to this email with your password. We will never ask for your password, and we strongly discourage you from sharing it with anyone.



Cheers!

ECommerce Staff',

'RESETPASSWORD','Your password has changed',0);
INSERT INTO `email` (`id`,`email_body`,`email_type`,`email_subject`,`is_deleted`) VALUES
(5,'Hi ##firstName## ##lastName##,\n\nWe wanted to let you know that your ECommerce Application profile has been updated.\n\n\n\nCheers!\n\nECommerce Staff','UPDATEPROFILE','Your profile has been updated',0);

/*!40000 ALTER TABLE `email` DISABLE KEYS */;
/*!40000 ALTER TABLE `email` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `payment_type`
--

LOCK TABLES `payment_type` WRITE;
insert into payment_type values(1,'Paypal');
insert into payment_type values(2,'MasterCard');
insert into payment_type values(3,'VISA');
insert into payment_type values(4,'American Express');
/*!40000 ALTER TABLE `payment_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tax`
--

LOCK TABLES `tax` WRITE;
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Alabama','AL',13.50 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Alaska','AK',7.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Arizona','AZ',10.73 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Arkansas','AR',11.63 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'California','CA',10.25 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Colorado','CO',10.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Connecticut','CT',6.35 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Delaware','DE',0.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'District of Columbia','DC',5.75 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Florida','FL',7.50 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Georgia','GA',8.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Guam','GU',4.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Hawaii','HI',4.71 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Idaho','ID',8.50 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Illinois','IL',10.25 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Indiana','IN',7.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Iowa[43]','IA',7.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Kansas','KS',10.15 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Kentucky','KY',6.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Louisiana','LA',12.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Maine','ME',5.50 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Maryland','MD',6.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Massachusetts','MA',6.25 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Michigan','MI',6.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Minnesota','MN',7.88 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Mississippi','MS',7.25 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Missouri','MO',10.85 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Montana','MT',0.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Nebraska','NE',7.50 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Nevada','NV',8.15 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'New Hampshire','NH',0.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'New Jersey','NJ',12.88 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'New Mexico','NM',8.69 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'New York','NY',8.88 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'North Carolina','NC',7.50 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'North Dakota [44]','ND',8.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Ohio[45]','OH',8.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Oklahoma','OK',11.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Oregon','OR',0.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Pennsylvania','PA',8.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Puerto Rico','PR',11.50 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Rhode Island','RI',7.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'South Carolina','SC',9.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'South Dakota','SD',6.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Tennessee','TN',9.75 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Texas','TX',8.25 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Utah','UT',8.35 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Vermont','VT',7.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Virginia','VA',6.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Washington','WA',10.40 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'West Virginia','WV',7.00 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Wisconsin','WI',6.75 );
INSERT INTO `tax` (`state`, `abbrv`, `percentage`) VALUES ( 'Wyoming','WY',6.00 );
/*!40000 ALTER TABLE `tax` DISABLE KEYS */;
/*!40000 ALTER TABLE `tax` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
INSERT INTO user(id, email_address, password, first_name, last_name, created_at, updated_at, is_deleted) VALUES (1,'mqazi@nisum.com','mqazi','mazhar','qazi',1234,0,0);
INSERT INTO user(id, email_address, password, first_name, last_name, created_at, updated_at, is_deleted) VALUES (2,'test1@nisum.com','test1','test1','test1',1234,0,0);
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
INSERT INTO address(id, user_id, address_line_1, address_line_2, city, state, country, zipcode, phone_number, address_type) VALUES (1,1,'address line 1',null,'city1',null,1,null,null,1);
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `preference`
--
LOCK TABLES `preference` WRITE;
INSERT INTO preference VALUES (1, "Preferred Store");
INSERT INTO preference VALUES (2, "Notification Preference");
INSERT INTO preference VALUES (3, "Favorite Categories");
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-02  9:45:25
