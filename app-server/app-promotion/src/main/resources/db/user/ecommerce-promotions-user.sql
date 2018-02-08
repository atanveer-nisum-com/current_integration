CREATE SCHEMA IF NOT EXISTS ecommerce_promotions;
CREATE USER 'app_promo'@'localhost' IDENTIFIED BY 'apppromo';
GRANT ALL ON ecommerce_promotions.* TO 'app_promo'@'localhost';