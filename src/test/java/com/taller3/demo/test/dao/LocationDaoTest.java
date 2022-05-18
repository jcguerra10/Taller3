package com.taller3.demo.test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.taller3.demo.dao.LocationDao;
import com.taller3.demo.model.prod.Location;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class LocationDaoTest {

	@Autowired
	private LocationDao dao;
	
	Location l1 = new Location();
	Location l2 = new Location();
	Location l3 = new Location();
	Location l4 = new Location();
	Location l5 = new Location();
	
	@BeforeEach
	void setup() {
		l1.setName("Locat 8");
		l1.setAvailability(new BigDecimal(2));
		l1.setCostrate(new BigDecimal(1));
		
		l2.setName("Locat 2");
		l2.setAvailability(new BigDecimal(1));
		l2.setCostrate(new BigDecimal(1));
		
		l3.setName("Locat 3");
		l3.setAvailability(new BigDecimal(8));
		l3.setCostrate(new BigDecimal(1));
		
		l4.setName("Locat 4");
		l4.setAvailability(new BigDecimal(2));
		l4.setCostrate(new BigDecimal(1));
		
		l5.setName("Locat 5");
		l5.setAvailability(new BigDecimal(2));
		l5.setCostrate(new BigDecimal(1));
	}
	
	@Test
	@Order(1)
	void testSaveAndGet() {
		System.out.println("1");
		
		dao.save(l1);
		
		Location testLocation = dao.get(1).get();
		
		assertNotNull(testLocation);
	}
	
	@Test
	@Order(2)
	void testUpdate() {
		System.out.println("2");
		
		dao.save(l2);
		dao.save(l3);
		dao.save(l4);
		dao.save(l5);
		
		Location testLocation = dao.get(1).get();
		
		testLocation.setName("Locat 1");
		testLocation.setCostrate(new BigDecimal(1));
		testLocation.setAvailability(new BigDecimal(2));
		
		dao.update(testLocation);
		
		Location result = dao.get(1).get();
		
		assertTrue(result.getName() == "Locat 1");
		assertTrue(result.getCostrate().intValue() == BigDecimal.valueOf(1).intValue());
		assertTrue(result.getAvailability().intValue() == BigDecimal.valueOf(2).intValue());
				
	}
	
	@Test
	@Order(3)
	void testGetAll() {
		System.out.println("3");
		
		List<Location> allLocations = dao.getAll();
		
		allLocations.forEach( p -> {
			System.out.println("-- " + p.getName() + " / ");
		});
		
		assertEquals(allLocations.size(), 5);
	}
	
}
