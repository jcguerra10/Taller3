package com.taller3.demo.dao;

import java.math.BigDecimal;
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

@Repository
@Scope("singleton")
public class ProductDaoImp implements Dao<Product> {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Product> get(Integer id) {
		return Optional.ofNullable(entityManager.find(Product.class, id));
	}
	
	@Override
	public Optional<Product> findById(Integer id) {
		return Optional.ofNullable(entityManager.find(Product.class, id));
	}

	@Override
	public List<Product> getAll() {
		Query query = entityManager.createQuery("SELECT a FROM Product a");
		return query.getResultList();
	}

	@Override
	@Transactional
	public void save(Product aut) {
		executeInsideTransaction(entityManager -> entityManager.persist(aut));
	}

	@Override
	@Transactional
	public void update(Product aut) {
		executeInsideTransaction(entityManager -> entityManager.merge(aut));
	}

	@Override
	@Transactional
	public void deleteById(Integer autId) {
		Product aut = get(autId).orElse(null);
		executeInsideTransaction(entityManager -> entityManager.remove(aut));
	}
	
	private void executeInsideTransaction(Consumer<EntityManager> action) {		
		try {
			action.accept(entityManager);
		}
		catch (RuntimeException e) {
			throw e;
		}
	}

	public List<Product> findAllBySubcategoryId(Integer subcategoryId) {
		
		Query query = entityManager.createQuery("SELECT a FROM Product a WHERE a.productsubcategory.productsubcategoryid = :subcategoryId");
		query.setParameter("subcategoryId", subcategoryId);
		return query.getResultList();
	}
	
	/*
	public List<Product> findAllByProductmodelid(Integer pmId) {
		Query query = entityManager.createQuery("SELECT a FROM Product a WHERE a.productmodel.productmodelid = :pmId");
		query.setParameter("pmId", pmId);
		return query.getResultList();
	}
	*/
	
	public List<Product> findAllBySize(BigDecimal productSize) {
		Query query = entityManager.createQuery("SELECT a FROM Product a WHERE a.size = :productSize");
		query.setParameter("size", productSize);
		return query.getResultList();
	}

	
}