package com.taller3.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taller3.demo.model.prod.Location;
import com.taller3.demo.model.prod.Productinventory;
import com.taller3.demo.model.prod.ProductinventoryPK;

@Repository
public interface ProductInventoryRepository extends CrudRepository<Productinventory, Integer> {

	Optional<Productinventory> findById(ProductinventoryPK pk);
	
}
