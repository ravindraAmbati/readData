package com.bigbasket.readData.controller;

import com.bigbasket.readData.ReadProduct;
import com.bigbasket.readData.component.Product;
import com.bigbasket.readData.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/bigbasket")
public class ProductControllerImpl implements ProductController {

    private static final long startProductId = 10000000;
    private static final long endProductId = 10001030;

    @Autowired
    ProductService service;

    @Autowired
    ReadProduct readProduct;

    @GetMapping("/start")
    public void start(){
        try {
            long prodId = startProductId;
            while(prodId<endProductId){
                service.save(readProduct.readProduct(prodId));
                prodId++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @PostMapping(path = "/save",consumes = "application/json",produces = "application/json")
    public boolean save(@RequestBody Product product) {
        return service.save(product);
    }

    @Override
    @PostMapping(path = "/saveAndReturnId",consumes = "application/json",produces = "application/json")
    public String saveAndReturnId(@RequestBody Product product) {
        return service.saveAndReturnId(product);
    }

    @Override
    @GetMapping("/create")
    public List<Product> create() {
        return service.create();
    }

    @Override
    @GetMapping("/drop")
    public String drop() {
        return service.drop();
    }

    @Override
    @GetMapping("/read")
    public List<Product> readAll() {
        return service.readAll();
    }
}
