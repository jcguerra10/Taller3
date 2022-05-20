package com.taller3.demo.services.interfaces;

import com.taller3.demo.model.prod.Productcategory;

public interface ProductcategoryService {
    void saveProductcategory(Productcategory pc);
    void editProductcategory(Productcategory pc, Integer i);
}
