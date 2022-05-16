package com.taller3.demo.dao.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.taller3.demo.model.prod.Product;

public interface Dao<T> {

	Optional<T> get(Integer id);

	Optional<T> findById(Integer id);
	
	List<T> getAll();

	@Transactional
	void save(T aut);

	@Transactional
	void update(T aut);

	@Transactional
	void deleteById(Integer autId);

}
