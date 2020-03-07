select MAX(PRICE) from Product where prodId='10000222';
select * from Product where prodId='10000100';
select * from ProductPrices;
select * from Product where price = 0;
select ((maxprice-minprice)/price)*100,* from ProductPrices where stddevprice != 0;
select 
((maxprice-minprice)/prices.price)*100 pert,* 
from ProductPrices prices, Product prod
where prod.timestamp <= '2020-03-08'  and prod.timestamp >= '2020-03-07' 
and stddevprice != 0 and prices.prodid  =  prod.prodid 
and prices.timestamp <= '2020-03-08'  and prices.timestamp >= '2020-03-07' 
order by pert desc