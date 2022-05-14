package com.taller3.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taller3.demo.model.prod.Productcosthistory;
import com.taller3.demo.model.prod.ProductcosthistoryPK;

@Repository
public interface ProductcosthistoryRepository extends CrudRepository<Productcosthistory, Integer> {

}
