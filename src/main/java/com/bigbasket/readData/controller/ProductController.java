package com.bigbasket.readData.controller;

import com.bigbasket.readData.component.Product;

public interface ProductController extends Controller<Product> {

    boolean save(Product product);

    String saveAndReturnId(Product product);
}
