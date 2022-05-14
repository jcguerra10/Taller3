package com.taller3.demo.services.interfaces;

import com.taller3.demo.model.prod.Productcosthistory;

public interface ProductcosthistoryService {
    public Productcosthistory saveProductcosthistory(Productcosthistory pch);
    public Productcosthistory editProductcosthistory(Productcosthistory pch, Integer id);
}
