select count(*) from product;
select count(*) from product where name IS NOT NULL;
select count(*) from product where name IS NULL;
select * from product where name IS NOT NULL order by price desc;
select * from product where name IS NOT NULL order by price asc;
select * from product where name IS NOT NULL order by price desc limit 10;
select * from product where name IS NOT NULL order by price asc limit 10;