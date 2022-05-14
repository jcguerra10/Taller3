package com.taller3.demo.services.interfaces;

import com.taller3.demo.model.prod.Productsubcategory;

public interface ProductsubcategoryService {
    public Productsubcategory saveProductsubcategory(Productsubcategory ps);
    public Productsubcategory editProductsubcategory(Productsubcategory ps, Integer i);
}
