package com.taller3.demo.services.interfaces;

import com.taller3.demo.model.prod.Productinventory;
import com.taller3.demo.model.prod.ProductinventoryPK;

public interface ProductInventoryService {
    public Productinventory saveProductInventory(Productinventory proInventory);
	public Productinventory editProductInventory(Productinventory proInventory, Integer id);
}
