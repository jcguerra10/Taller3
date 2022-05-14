package com.taller3.demo.dao.interfaces;

import java.util.List;
import java.util.Optional;

import com.taller3.demo.model.prod.Product;

public interface Dao<T> {

	Optional<T> get(Integer id);

	Optional<T> findById(Integer id);
	
	List<T> getAll();

	void save(T aut);

	void update(T aut);

	void deleteById(Integer autId);

}
