package com.taller3.demo.services;

import java.util.Optional;

import com.taller3.demo.dao.ProductsubcategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller3.demo.model.prod.Productsubcategory;
import com.taller3.demo.services.interfaces.ProductsubcategoryService;

@Service
public class ProductsubcategoryImp implements ProductsubcategoryService {
@Autowired
private ProductsubcategoryDao dao;
	@Transactional
	@Override
	public void saveProductsubcategory(Productsubcategory pc) {
		if(pc == null)
			throw new NullPointerException("Null Object");
		dao.save(pc);
	}

	@Transactional
	@Override
	public void editProductsubcategory(Productsubcategory pc, Integer i) {
		Optional<Productsubcategory> op = dao.findById(i);
		Productsubcategory opLoc = op.get();
		if(pc == null)
			throw new NullPointerException("Null Object");
		opLoc.setName(pc.getName());
		dao.update(opLoc);
	}
}
