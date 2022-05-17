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
import com.taller3.demo.model.prod.Productcosthistory;

@Repository
@Scope("singleton")
public class ProductcosthistoryDaoImp implements Dao<Productcosthistory> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Productcosthistory> get(Integer id) {
		return Optional.ofNullable(entityManager.find(Productcosthistory.class, id));
	}

	@Override
	public Optional<Productcosthistory> findById(Integer id) {
		return Optional.ofNullable(entityManager.find(Productcosthistory.class, id));
	}
	
	@Override
	public List<Productcosthistory> getAll() {
		return entityManager.createQuery("SELECT a FROM Productcosthistory a").getResultList();
	}

	@Override
	@Transactional
	public void save(Productcosthistory aut) {
		entityManager.persist(aut);
	}

	@Override
	public void update(Productcosthistory aut) {
		executeInsideTransaction(entityManager -> entityManager.merge(aut));
	}

	@Override
	public void deleteById(Integer autId) {
		Productcosthistory aut = get(autId).orElse(null);
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

	public List<Productcosthistory> findAllByStandardCost(BigDecimal stndcost) {
		
		Query query = entityManager.createQuery("SELECT a FROM Productcosthistory a WHERE a.standardcost = :stndcost");
		query.setParameter("stndcost", stndcost);
		return query.getResultList();
	}
	
	/*
	public List<Product> findAllByProductmodelid(Integer pmId) {
		Query query = entityManager.createQuery("SELECT a FROM Product a WHERE a.productmodel.productmodelid = :pmId");
		query.setParameter("pmId", pmId);
		return query.getResultList();
	}
	*/
	
	public List<Product> findAllByProductId(Integer pdctId) {
		Query query = entityManager.createQuery("SELECT a FROM Productcosthistory a WHERE a.product.productid = :pdctId");
		query.setParameter("pdctId", pdctId);
		return query.getResultList();
	}

	
}
