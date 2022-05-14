package com.taller3.demo.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.taller3.demo.dao.interfaces.Dao;
import com.taller3.demo.model.prod.Product;
import com.taller3.demo.model.prod.Productinventory;

public class ProductinventoryDaoImp implements Dao<Productinventory> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Productinventory> get(Integer id) {
		return Optional.ofNullable(entityManager.find(Productinventory.class, id));
	}

	@Override
	public Optional<Productinventory> findById(Integer id) {
		return Optional.ofNullable(entityManager.find(Productinventory.class, id));
	}

	@Override
	public List<Productinventory> getAll() {
		Query query = entityManager.createQuery("SELECT a FROM Productinventory a");
		return query.getResultList();
	}

	@Override
	public void save(Productinventory aut) {
		executeInsideTransaction(entityManager -> entityManager.persist(aut));
	}

	@Override
	public void update(Productinventory aut) {
		executeInsideTransaction(entityManager -> entityManager.merge(aut));
	}

	@Override
	public void deleteById(Integer autId) {
		Productinventory aut = get(autId).orElse(null);
		executeInsideTransaction(entityManager -> entityManager.remove(aut));
	}

	private void executeInsideTransaction(Consumer<EntityManager> action) {
		try {
			action.accept(entityManager);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public List<Product> findAllByProductId(Integer prdId) {
		Query query = entityManager.createQuery("SELECT a FROM Productinventory a WHERE a.product.productid = :prdId");
		query.setParameter("prdId", prdId);
		return query.getResultList();
	}

	public List<Product> findAllBySize(Integer lctId) {
		Query query = entityManager.createQuery("SELECT a FROM Productinventory a WHERE a.location.locationid = :lctId");
		query.setParameter("lctId", lctId);
		return query.getResultList();
	}

}
