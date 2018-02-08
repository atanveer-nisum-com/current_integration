CREATE MATERIALIZED VIEW ecommerce_app.product_by_id AS
	SELECT *
	FROM product
	WHERE id IS NOT NULL AND name IS NOT NULL AND code IS NOT NULL
	PRIMARY KEY ( id, name, code )
	WITH CLUSTERING ORDER BY ( name DESC, code ASC );