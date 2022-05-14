package com.taller3.demo.services.interfaces;

import com.taller3.demo.model.prod.Productcategory;

public interface ProductcategoryService {
    public Productcategory saveProductcategory(Productcategory pc);
    public Productcategory editProductcategory(Productcategory pc, Integer i);
}
