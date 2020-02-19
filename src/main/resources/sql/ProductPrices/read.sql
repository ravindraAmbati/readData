select MAX(PRICE) from Product where prodId='10000222';
select * from Product where prodId='10000100';
select * from ProductPrices;
select * from Product where price = 0;
select ((maxprice-minprice)/price)*100,* from ProductPrices where stddevprice != 0;
