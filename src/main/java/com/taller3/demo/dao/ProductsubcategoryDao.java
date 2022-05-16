package com.taller3.demo.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.taller3.demo.dao.interfaces.Dao;
import com.taller3.demo.model.prod.Productcategory;
import com.taller3.demo.model.prod.Productsubcategory;

@Repository
@Scope("singleton")
public class ProductsubcategoryDao implements Dao<Productsubcategory> {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Optional<Productsubcategory> get(Integer id) {
		return Optional.ofNullable(entityManager.find(Productsubcategory.class, id));
	}

	@Override
	public Optional<Productsubcategory> findById(Integer id) {
		return Optional.ofNullable(entityManager.find(Productsubcategory.class, id));
	}

	@Override
	public List<Productsubcategory> getAll() {
		Query query = entityManager.createQuery("SELECT a FROM Productsubcategory a");
		return query.getResultList();
	}

	@Override
	public void save(Productsubcategory aut) {
		entityManager.persist(aut);
	}

	@Override
	public void update(Productsubcategory aut) {
		entityManager.merge(aut);
	}

	@Override
	public void deleteById(Integer autId) {
		Productsubcategory aut = get(autId).orElse(null);
		entityManager.remove(aut);
	}

}
