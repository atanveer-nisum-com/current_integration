create MATERIALIZED VIEW IF NOT EXISTS product_by_category as
select * from product
where category is not null and name is not null and code is not null 
primary key (category,name,code)
with clustering order by (name desc);