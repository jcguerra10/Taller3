package com.taller3.demo.services.interfaces;

import com.taller3.demo.model.prod.Product;

public interface ProductService {
    void saveProduct(Product pro);
    void editProduct(Product pro, Integer id);
}
