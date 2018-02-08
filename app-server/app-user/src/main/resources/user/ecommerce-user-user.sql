CREATE SCHEMA IF NOT EXISTS ecommerce_user;
CREATE USER 'app_user'@'localhost' IDENTIFIED BY 'appuser';
GRANT ALL ON ecommerce_user.* TO 'app_user'@'localhost';