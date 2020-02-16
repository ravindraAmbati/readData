package com.bigbasket.readData.service;

import com.bigbasket.readData.component.Product;
import com.bigbasket.readData.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo repo;

    @Override
    public boolean save(Product product) {
        return repo.save(product);
    }

    @Override
    public String saveAndReturnId(Product product) {
        return repo.saveAndReturnId(product);
    }

    @Override
    public List<Product> create() {
        return repo.create();
    }

    @Override
    public String drop() {
        return repo.drop();
    }

    @Override
    public List<Product> readAll() {
        return repo.readAll();
    }
}
