package com.taller3.demo.services;

import java.util.Optional;

import com.taller3.demo.dao.ProductcategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller3.demo.model.prod.Productcategory;
import com.taller3.demo.services.interfaces.ProductcategoryService;

@Service
public class ProductcategoryServiceImp implements ProductcategoryService {
	
	@Autowired
	private ProductcategoryDao pcRepository;

	@Transactional
	@Override
	public void saveProductcategory(Productcategory pc) {
		if(pc == null)
			throw new NullPointerException("Null Object");
		pcRepository.save(pc);
	}

	@Transactional
	@Override
	public void editProductcategory(Productcategory pc, Integer i) {
		Optional<Productcategory> op = pcRepository.findById(i);
		Productcategory opLoc = op.get();
		if(pc == null)
			throw new NullPointerException("Null Object");
		opLoc.setName(pc.getName());
		pcRepository.save(opLoc);
	}
}
