package com.taller3.demo.services;

import java.util.Optional;

import com.taller3.demo.dao.ProductinventoryDaoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller3.demo.model.prod.Productinventory;
import com.taller3.demo.services.interfaces.ProductInventoryService;

@Service
public class ProductInventoryServiceImp implements ProductInventoryService {

	@Autowired
	private ProductinventoryDaoImp dao;

	@Transactional
	@Override
	public void saveProductInventory(Productinventory proInventory) {
		if (proInventory == null)
			throw new NullPointerException("ObjectNull");
		if (proInventory.getLocation() == null) 
			throw new NullPointerException("location");
		if (proInventory.getQuantity() == null)
			throw new IllegalArgumentException("Quantity Null");
		if (proInventory.getQuantity() < 0)
			throw new IllegalArgumentException("Quantity is not Greater than 0");
		dao.save(proInventory);
	}

	@Transactional
	@Override
	public void editProductInventory(Productinventory proInventory, Integer id) {
		Optional<Productinventory> op = dao.findById(id);
		Productinventory opLoc = op.get();
		if (proInventory == null)
			throw new NullPointerException("No Object");
		if (proInventory.getLocation() == null) 
			throw new NullPointerException("Location Null");
		if (proInventory.getQuantity() == null)
			throw new IllegalArgumentException("Quantity Null");
		if (proInventory.getQuantity() < 0)
			throw new IllegalArgumentException("Quantity is not Greater than 0");
		opLoc.setProduct(proInventory.getProduct());
		opLoc.setLocation(proInventory.getLocation());
		opLoc.setQuantity(proInventory.getQuantity());
		dao.update(opLoc);
	}

}