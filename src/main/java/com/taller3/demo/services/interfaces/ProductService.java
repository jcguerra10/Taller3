package com.taller3.demo.services.interfaces;

import com.taller3.demo.model.prod.Product;

public interface ProductService {
    public Product saveProduct(Product pro);
    public Product editProduct(Product pro, Integer id);
}
