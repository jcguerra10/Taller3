package com.taller3.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.taller3.demo.dao.interfaces.Dao;
import com.taller3.demo.model.prod.Product;
import com.taller3.demo.model.prod.Productcategory;

@Repository
@Scope("singleton")
public class ProductcategoryDao implements Dao<Productcategory>  {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Optional<Productcategory> get(Integer id) {
		return Optional.ofNullable(entityManager.find(Productcategory.class, id));
	}

	@Override
	public Optional<Productcategory> findById(Integer id) {
		return Optional.ofNullable(entityManager.find(Productcategory.class, id));
	}

	@Override
	public List<Productcategory> getAll() {
		Query query = entityManager.createQuery("SELECT a FROM Productcategory a");
		return query.getResultList();
	}

	@Override
	@Transactional
	public void save(Productcategory aut) {
		entityManager.persist(aut);
	}

	@Override
	@Transactional
	public void update(Productcategory aut) {
		entityManager.merge(aut);
	}

	@Override
	@Transactional
	public void deleteById(Integer autId) {
		Productcategory aut = get(autId).orElse(null);
		entityManager.remove(aut);
	}

}
