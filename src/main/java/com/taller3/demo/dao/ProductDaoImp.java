package com.taller3.demo.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
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
		entityManager.persist(aut);
	}

	@Override
	@Transactional
	public void update(Product aut) {
		entityManager.merge(aut);
	}

	@Override
	@Transactional
	public void deleteById(Integer autId) {
		Product aut = get(autId).orElse(null);
		entityManager.remove(aut);
	}

	public List<Product> findAllBySubcategoryId(Integer subcategoryId) {
		
		Query query = entityManager.createQuery("SELECT a FROM Product a WHERE a.productsubcategory.productsubcategoryid = :subcategoryId").setParameter("subcategoryId", subcategoryId);
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
		Query query = entityManager.createQuery("SELECT a FROM Product a WHERE a.size = :productSize").setParameter("productSize", productSize);
		return query.getResultList();
	}
	
	public List<?> specialQuery1(LocalDate from, LocalDate to) {
		return entityManager.createQuery("SELECT p FROM Product p, Location l, Productinventory pi, Productcosthistory pch "
				+ "WHERE p.productid = pi.product.productid AND pi.location.locationid = l.locationid AND pch.product.productid = p.productid "
				+ "AND pch.startdate BETWEEN :from AND :to "
				+ "AND pch.enddate BETWEEN :from AND :to "
				+ "AND pi.quantity > 0 "
				+ "GROUP BY l.locationid").setParameter("from", from).setParameter("to", to).getResultList();
	}

	public List<?> specialQuery2() {
		return entityManager.createQuery("SELECT e FROM Product e "
				+ "WHERE e.productcosthistories.size >= 2").getResultList();
	}
	
}