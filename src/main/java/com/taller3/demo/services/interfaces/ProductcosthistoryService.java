package com.taller3.demo.services.interfaces;

import com.taller3.demo.model.prod.Productcosthistory;

public interface ProductcosthistoryService {
    void saveProductcosthistory(Productcosthistory pch);
    void editProductcosthistory(Productcosthistory pch, Integer id);
}
