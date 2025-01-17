package com.bigbasket.readData.repo;

import com.bigbasket.readData.component.Product;


public interface ProductRepo extends Repo<Product> {

    String createSQL = "CREATE TABLE " + Product.class.getSimpleName() +
            "(\n" +
            "\tid SERIAL PRIMARY KEY,\n" +
            "\tprodId VARCHAR NOT NULL,\n" +
            "\tname VARCHAR,\n" +
            "\tquantity VARCHAR,\n" +
            "\tprice decimal,\n" +
            "\ttimeStamp timestamp DEFAULT now()\n" +
            ")";

    String dropSQL = "drop table " + Product.class.getSimpleName();

    String insertTestDataSQL = "insert into " + Product.class.getSimpleName() + " (prodId,name,quantity,price)\n" +
            "values ('test','testName','1 Kg',100.0);";

    String selectTestDataSQL = "select * from " + Product.class.getSimpleName();

    String readAllSQL = "select * from " + Product.class.getSimpleName();
    String saveSQL = "insert into " + Product.class.getSimpleName() + " (prodId,name,quantity,price)\n" +
            "values (?,?,?,?)";
    String saveAndReturnIdSQL = "insert into " + Product.class.getSimpleName() + " (prodId,name,quantity,price)\n" +
            "values (?,?,?,?)\n" +
            "returning id;";

    String id = "ID";

    String readProd = "select * from " + Product.class.getSimpleName() + " where prodId=:prodId";
    String maxPrice = "select MAX(PRICE) from " + Product.class.getSimpleName() + " where prodId=:prodId";
    String minPrice = "select MIN(PRICE) from " + Product.class.getSimpleName() + " where prodId=:prodId";
    String avgPrice = "select AVG(PRICE) from " + Product.class.getSimpleName() + " where prodId=:prodId";
    String varPrice = "select VARIANCE(PRICE) from " + Product.class.getSimpleName() + " where prodId=:prodId";
    String stdDevPrice = "select STDDEV(PRICE) from " + Product.class.getSimpleName() + " where prodId=:prodId";

    String saveProdPrices = "insert into " + Product.class.getSimpleName() + "Prices" + " " +
            "(prodId,price,maxPrice,minPrice,avgPrice,stdDevPrice,varPrice)\n" +
            "values (?,?,?,?,?,?,?)";
    String saveProdPricesAndReturnId = "insert into " + Product.class.getSimpleName() + "Prices" + " " +
            "(prodId,price,maxPrice,minPrice,avgPrice,stdDevPrice,varPrice)\n" +
            "values (?,?,?,?,?,?,?)\n" +
            "returning id;";
}
