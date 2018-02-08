CREATE TABLE IF NOT EXISTS item (
id uuid,
name text,
code text,
buyable tinyint,
created_at int,
description text,
displayable tinyint,
image_default text,
images_list LIST<text>,
price decimal,
quantity int,
updated_at int,
PRIMARY KEY (id, name, code)
) WITH CLUSTERING ORDER BY ( name ASC, code ASC );

CREATE TABLE IF NOT EXISTS product (
is_deleted tinyint,
id uuid,
code text,
name text,
buyable tinyint,
created_at int,
description text,
displayable tinyint,
image_default text,
images_list LIST<text>,
is_recommended tinyint,
price decimal,
quantity int,
sku text,
updated_at int,
category text,
search_str text,
row_counter int,
PRIMARY KEY (code, name)
) WITH CLUSTERING ORDER BY (name ASC);