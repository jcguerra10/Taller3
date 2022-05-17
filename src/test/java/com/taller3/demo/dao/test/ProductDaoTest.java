package com.taller3.demo.dao.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.taller3.demo.dao.ProductDaoImp;
import com.taller3.demo.dao.ProductcategoryDao;
import com.taller3.demo.dao.ProductsubcategoryDao;
import com.taller3.demo.model.prod.Product;
import com.taller3.demo.model.prod.Productcategory;
import com.taller3.demo.model.prod.Productsubcategory;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ProductDaoTest {

	@Autowired
	private ProductDaoImp dao;
	
	@Autowired
	private ProductcategoryDao pc;
	@Autowired
	private ProductsubcategoryDao psc;

	static Product p1 = new Product();
	static Product p2 = new Product();
	static Product p3 = new Product();
	static Product p4 = new Product();
	static Product p5 = new Product();

	@BeforeEach
	void setUp() {
		Productcategory pCategory1 = new Productcategory();
		pCategory1.setName("Tech");

		Productsubcategory pSubCategory1 = new Productsubcategory();
		pSubCategory1.setName("Celular");
		pSubCategory1.setProductcategory(pCategory1);
		
		Productsubcategory pSubCategory2 = new Productsubcategory();
		pSubCategory2.setName("Console");
		pSubCategory2.setProductcategory(pCategory1);

		pc.save(pCategory1);
		psc.save(pSubCategory1);
		psc.save(pSubCategory2);
		
		p1.setName("Iphone1");
		p1.setProductnumber("21");
		p1.setSellstartdate(LocalDate.of(2022, 3, 14));
		p1.setSellenddate(LocalDate.of(2022, 3, 15));
		p1.setProductsubcategory(pSubCategory1);
		p1.setSize(BigDecimal.valueOf(12));
		p1.setWeight(BigDecimal.valueOf(12));
		
		p2.setName("Iphone2");
		p2.setProductnumber("22");
		p2.setSellstartdate(LocalDate.of(2021, 3, 14));
		p2.setSellenddate(LocalDate.of(2021, 3, 15));
		p2.setProductsubcategory(pSubCategory1);
		p2.setSize(BigDecimal.valueOf(12));
		p2.setWeight(BigDecimal.valueOf(12));
		
		p3.setName("Switch");
		p3.setProductnumber("53");
		p3.setSellstartdate(LocalDate.of(2021, 3, 14));
		p3.setSellenddate(LocalDate.of(2021, 3, 15));
		p3.setProductsubcategory(pSubCategory2);
		p3.setSize(BigDecimal.valueOf(12));
		p3.setWeight(BigDecimal.valueOf(12));
		
		p4.setName("Iphone4");
		p4.setProductnumber("24");
		p4.setSellstartdate(LocalDate.of(2022, 3, 14));
		p4.setSellenddate(LocalDate.of(2022, 3, 15));
		p4.setProductsubcategory(pSubCategory1);
		p4.setSize(BigDecimal.valueOf(12));
		p4.setWeight(BigDecimal.valueOf(12));
		
		p5.setName("Iphone5");
		p5.setProductnumber("24");
		p5.setSellstartdate(LocalDate.of(2022, 3, 14));
		p5.setSellenddate(LocalDate.of(2022, 3, 15));
		p5.setProductsubcategory(pSubCategory1);
		p5.setSize(BigDecimal.valueOf(12));
		p5.setWeight(BigDecimal.valueOf(15));
		
		p5.setName("Play");
		p5.setProductnumber("57");
		p5.setSellstartdate(LocalDate.of(2022, 3, 14));
		p5.setSellenddate(LocalDate.of(2022, 3, 15));
		p5.setProductsubcategory(pSubCategory2);
		p5.setSize(BigDecimal.valueOf(12));
		p5.setWeight(BigDecimal.valueOf(15));


	}

	@Test
	@Order(1)
	void testSaveAndGet() {
		System.out.println("1");
		dao.save(p1);
		Product testProduct = dao.get(1).get();	
		assertNotNull(testProduct);	
	}
	
	@Test
	@Order(2)
	void testUpdate() {
		System.out.println("2");
		
		dao.save(p2);
		dao.save(p3);
		dao.save(p4);
		dao.save(p5);
		
		Product testProduct = dao.get(1).get();
		testProduct.setName("Samsung S10");                       
		testProduct.setProductnumber("32");                     
		testProduct.setSellstartdate(LocalDate.of(2022, 4, 14));
		testProduct.setSellenddate(LocalDate.of(2022, 4, 15));        
		testProduct.setSize(BigDecimal.valueOf(15));     
		
		dao.update(testProduct);
		
		Product result = dao.get(1).get();
		
		assertTrue(result.getName() == "Samsung S10");
		assertTrue(result.getProductnumber() == "32");
		assertTrue(result.getSellstartdate().equals(LocalDate.of(2022, 4, 14)));
		assertTrue(result.getSellenddate().equals(LocalDate.of(2022, 4, 15)));
		assertEquals(result.getSize().doubleValue(), BigDecimal.valueOf(15).doubleValue());
	}
	
	@Test
	@Order(3)
	void testGetAll() {
		System.out.println("3");
		
		List<Product> allProducts = dao.getAll();
		
		allProducts.forEach( p -> {
			System.out.println("-- " + p.getProductsubcategory().getProductsubcategoryid() + " / " + p.getName() + " / " + p.getProductsubcategory().getName());
		});
		
		assertEquals(allProducts.size(), 5);
	}

	
	@Test
	@Order(4)
	void testFindBySubcategory() { 
		System.out.println("4");

		
		List<Product> allProducts = dao.findAllBySubcategoryId(3);
		
		
		allProducts.forEach(d -> {
			assertTrue(d.getProductsubcategory().getProductsubcategoryid() == 3);
		});
	}
	
	@Test
	@Order(5)
	void testFindBySize() {
		System.out.println("5");
		
		List<Product> allProducts = dao.findAllBySize(BigDecimal.valueOf(15));
		
		allProducts.forEach(d -> {
			assertTrue(d.getSize().doubleValue() == 15.00);
			assertFalse(d.getSize().doubleValue() == 16.00);
		});
	}
	
	@Test
	@Order(6)
	void testFindSpecialQuery1() {
		System.out.println("6");
		
		List<?> allSpecialQuery = dao.specialQuery1(LocalDate.of(2022,3,1), LocalDate.of(2022,5,3));
		
		allSpecialQuery.forEach(d -> {
			System.out.println("--- " + d);
		});
	}
	

}
