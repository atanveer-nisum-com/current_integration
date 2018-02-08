CREATE SCHEMA IF NOT EXISTS ecommerce_shop;
CREATE USER 'app_shop'@'localhost' IDENTIFIED BY 'appshop';
GRANT ALL ON ecommerce_shop.* TO 'app_shop'@'localhost';