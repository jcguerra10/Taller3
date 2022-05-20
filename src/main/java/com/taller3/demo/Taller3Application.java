package com.taller3.demo;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.taller3.demo.dao.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.taller3.demo.model.prod.Location;
import com.taller3.demo.model.prod.Product;
import com.taller3.demo.model.prod.Productcategory;
import com.taller3.demo.model.prod.Productcosthistory;
import com.taller3.demo.model.prod.Productsubcategory;
import com.taller3.demo.model.prod.UserApp;
import com.taller3.demo.model.prod.UserType;
import com.taller3.demo.services.UserServiceImp;

@SpringBootApplication
public class Taller3Application {
	public static void main(String[] args) {
		ConfigurableApplicationContext s = SpringApplication.run(Taller3Application.class, args);
		
		UserServiceImp usi = s.getBean(UserServiceImp.class);
		
		UserApp uaAdmin = new UserApp();
		
		uaAdmin.setId(1);
		uaAdmin.setUsername("admin1");
		uaAdmin.setPassword("{noop}123456");
		uaAdmin.setType(UserType.administrator);
		
		UserApp uaOper = new UserApp();
		
		uaOper.setId(2);
		uaOper.setUsername("oper1");
		uaOper.setPassword("{noop}123456");
		uaOper.setType(UserType.operator);
		
		usi.save(uaAdmin);
		usi.save(uaOper);
		
		ProductDaoImp pr = s.getBean(ProductDaoImp.class);
		ProductcategoryDao pcr = s.getBean(ProductcategoryDao.class);
		ProductsubcategoryDao pscr = s.getBean(ProductsubcategoryDao.class);
		LocationDao lr = s.getBean(LocationDao.class);

		Productcategory pCategory = new Productcategory();
		pCategory.setName("Tech");
		
		Productsubcategory pSubCategory = new Productsubcategory();
		pSubCategory.setName("Iphone");
		pSubCategory.setProductcategory(pCategory);
		
		Product p = new Product();
		p.setName("iphone");
		p.setProductnumber("21");
		p.setSellstartdate(LocalDate.of(2022, 3, 14));
		p.setSellenddate(LocalDate.of(2022, 3, 15));
		p.setProductsubcategory(pSubCategory);
		p.setSize(BigDecimal.valueOf(12));
		p.setWeight(BigDecimal.valueOf(12));
		
		pcr.save(pCategory);
		pscr.save(pSubCategory);
		
		pr.save(p);
		
		Location l = new Location();
		
		l.setLocationid(1);
		l.setName("stan1");
		l.setAvailability(BigDecimal.valueOf(2));
		l.setCostrate(BigDecimal.valueOf(1));
		
		lr.save(l);
		
		ProductcosthistoryDaoImp pchr = s.getBean(ProductcosthistoryDaoImp.class);
		
		Productcosthistory pch = new Productcosthistory();
		
		pch.setProduct(p);
		pch.setStartdate(LocalDate.of(2022, 4, 13));
		pch.setEnddate(LocalDate.of(2022, 4, 14));
		pch.setStandardcost(BigDecimal.valueOf(12));
		
		pchr.save(pch);
	}

}
