select p1.id, p2.id, p1.name,p1.price from product p1, product p2
where p1.id != p2.id and p1.prodid = p2.prodid and p1.name = p2.name and p1.quantity = p2.quantity and p1.price = p2.price;
select * from product where name IS NOT NULL order by prodid;
select count(*) from product where name IS NOT NULL;
select count(*) from product where name IS NULL;
select * from product where name IS NOT NULL order by price desc;
select * from product where name IS NOT NULL order by price asc;
select * from product where name IS NOT NULL order by price desc limit 10;
select * from product where name IS NOT NULL order by price asc limit 10;
select * from product where name IS NOT NULL order by price desc limit 20;
select * from product where name IS NOT NULL order by price asc limit 20;
select * from product where name IS NOT NULL order by price desc limit 50;
select * from product where name IS NOT NULL order by price asc limit 50;
select * from product where name IS NOT NULL order by price desc limit 100;
select * from product where name IS NOT NULL order by price asc limit 100;
select p1.name, p1.quantity, p1.price, p1.timeStamp, p2.name, p2.quantity, p2.price, p2.timeStamp
from product p1, product p2
where p1.id != p2.id and p1.prodid = p2.prodid and 
p1.price != p2.price and p1.name is not NULL and p2.name is not NULL order by p1.prodid;
