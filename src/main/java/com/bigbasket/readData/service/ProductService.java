package com.bigbasket.readData.service;

import com.bigbasket.readData.component.Product;

public interface ProductService extends Service<Product> {

    boolean save(Product product);

    String saveAndReturnId(Product product);
}
