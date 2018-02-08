create database ecommerce_app;
create database ecommerce_promotions;

GRANT ALL PRIVILEGES ON ecommerce_promotions.* TO 'app_promotions'@'%' identified by '01df61e01fc6d2009ed9b531b0bf00aa68c0e27ff725f00c409a25fa1860d547';
GRANT ALL PRIVILEGES ON ecommerce_app.* TO 'app_shop'@'%' identified by '27514e5018e6b381a79dc48ca0fb42ba63e8f90390228e02b923db6f8d20b13d';
GRANT ALL PRIVILEGES ON ecommerce_app.* TO 'app_user'@'%' identified by '59c66f84104ced45525abda5528796248235e06b1d1b064a8d0e5cbc08299b4c';

