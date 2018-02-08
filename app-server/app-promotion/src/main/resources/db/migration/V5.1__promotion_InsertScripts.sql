 INSERT INTO promotion_type  (promotion_name, promotion_desc) VALUES  ('DOLLAR_OFF_ORDER','Dollar Off Order');
 
 INSERT INTO promotion_discount (promotion_type_id,discount_value,active,created_at,updated_at,category_name,discount_type) VALUES (1,5,1,1508746614,1508746614,'WOMEN-Activewear','BUY_PRODUCT_DISCOUNT');
  
 INSERT INTO order_amount_discount(from_amount,to_amount,discount_value,active,created_at,updated_at) VALUES (100,199,20,1,1508746614,1508746614);
 INSERT INTO order_amount_discount(from_amount,to_amount,discount_value,active,created_at,updated_at) VALUES (200,299,30,1,1508746614,1508746614);
 INSERT INTO order_amount_discount(from_amount,to_amount,discount_value,active,created_at,updated_at)  VALUES(300,-1,60,1,1508746614,1508746614);
 
 INSERT INTO product_promotion_link (product_id,promotion_id,discount_value,created_at,updated_at) VALUES('191570a0-af41-11e7-af0a-f583101e7c6f',1,10,1508746614,1508746614);
 INSERT INTO product_promotion_link (product_id,promotion_id,discount_value,created_at,updated_at) VALUES('190d8160-af41-11e7-af0a-f583101e7c6f',1,10,1508746614,1508746614);
 INSERT INTO product_promotion_link (product_id,promotion_id,created_at,updated_at) VALUES('1914fb70-af41-11e7-af0a-f583101e7c6f',1,1508746614,1508746614);