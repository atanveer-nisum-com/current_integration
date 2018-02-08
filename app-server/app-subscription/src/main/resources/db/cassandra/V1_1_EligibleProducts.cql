CREATE TABLE IF NOT EXISTS eligibleproducts (
productID text,
clientID text,
name text,
description text,
delFrequency text,
delCharge decimal,
price decimal,
campaignID text,
category text,
PRIMARY KEY (productID,clientID));