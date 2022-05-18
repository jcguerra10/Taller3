package com.taller3.demo.services.interfaces;

import com.taller3.demo.model.prod.Productinventory;
import com.taller3.demo.model.prod.ProductinventoryPK;

public interface ProductInventoryService {
    void saveProductInventory(Productinventory proInventory);
	void editProductInventory(Productinventory proInventory, Integer id);
}
