package com.taller3.demo.test.services;

import com.taller3.demo.dao.ProductDaoImp;
import com.taller3.demo.dao.ProductcategoryDao;
import com.taller3.demo.dao.ProductsubcategoryDao;
import com.taller3.demo.model.prod.Product;
import com.taller3.demo.model.prod.Productcategory;
import com.taller3.demo.model.prod.Productsubcategory;
import com.taller3.demo.services.ProductServiceImp;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductServiceImpTest {

    @Autowired
    private ProductServiceImp serviceProduct;

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
        pSubCategory1.setName("Cellphone");
        pSubCategory1.setProductcategory(pCategory1);

        Productsubcategory pSubCategory2 = new Productsubcategory();
        pSubCategory2.setName("Console");
        pSubCategory2.setProductcategory(pCategory1);

        pc.save(pCategory1);
        psc.save(pSubCategory1);
        psc.save(pSubCategory2);

        p1 = new Product();
        p1.setName("Iphone1");
        p1.setProductnumber("21");
        p1.setSellstartdate(LocalDate.of(2022, 3, 14));
        p1.setSellenddate(LocalDate.of(2022, 3, 15));
        p1.setProductsubcategory(pSubCategory1);
        p1.setSize(BigDecimal.valueOf(12));
        p1.setWeight(BigDecimal.valueOf(12));

        p2 = new Product();
        p2.setName("Iphone2");
        p2.setProductnumber("22");
        p2.setSellstartdate(LocalDate.of(2021, 3, 16));
        p2.setSellenddate(LocalDate.of(2021, 3, 15));
        p2.setProductsubcategory(pSubCategory1);
        p2.setSize(BigDecimal.valueOf(-1));
        p2.setWeight(BigDecimal.valueOf(-1));

        p3 = new Product();

        p4 = new Product();
        p4.setName("xbox");
        p4.setProductnumber("24");
        p4.setSellstartdate(LocalDate.of(2022, 3, 14));
        p4.setSellenddate(LocalDate.of(2022, 3, 15));
        p4.setProductsubcategory(pSubCategory2);
        p4.setSize(BigDecimal.valueOf(12));
        p4.setWeight(BigDecimal.valueOf(12));

        p5 = new Product();
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

    // <------------------------> Save <------------------------>

    @Test
    void testThatSave() {
        serviceProduct.saveProduct(p1);
        assertNotNull(dao.get(1).get());
    }


    @Test
    void testConstraints() {
        serviceProduct.saveProduct(p1);
        Product testProduct = dao.get(1).get();
        assertNotNull(testProduct.getProductsubcategory());
        assertNotNull(testProduct.getProductsubcategory().getProductcategory());
        assertTrue(testProduct.getProductnumber() == "21", "ProductNumber");
        assertTrue(testProduct.getSellstartdate().compareTo(testProduct.getSellenddate()) < 0 , "Date");
        assertTrue(testProduct.getWeight().doubleValue() > 0, "Weight");
        assertTrue(testProduct.getSize().intValue() > 0, "Size");
    }

    // <------------------------> Save Throws <------------------------>

    @Test
    void testExceptionSave() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceProduct.saveProduct(p2);
        });

        p2.setSellstartdate(LocalDate.of(2022,3,14));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceProduct.saveProduct(p2);
        });

        p2.setWeight(BigDecimal.valueOf(12));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceProduct.saveProduct(p2);
        });
    }

    // <------------------------> Empty Save <------------------------>

    @Test
    void testExceptionEmpty() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            serviceProduct.saveProduct(p3);
        });

        Productsubcategory pSubCategory = new Productsubcategory();
        p3.setProductsubcategory(pSubCategory);

        Assertions.assertThrows(NullPointerException.class, () -> {
            serviceProduct.saveProduct(p3);
        });

        Productcategory pCategory = new Productcategory();
        p3.getProductsubcategory().setProductcategory(pCategory);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceProduct.saveProduct(p3);
        });

        p3.setProductnumber("1");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceProduct.saveProduct(p3);
        });

        p3.setSellstartdate(LocalDate.of(2022,03,11));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceProduct.saveProduct(p3);
        });

        p3.setSellenddate(LocalDate.of(2022,03,12));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceProduct.saveProduct(p3);
        });

        p3.setWeight(BigDecimal.valueOf(12));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceProduct.saveProduct(p3);
        });
    }

    // <------------------------> Edit <------------------------>


    @Test
    void testThatEdit() {
        serviceProduct.saveProduct(p1);
        serviceProduct.editProduct(p4, 1);
        Product test = dao.get(1).get();
        assertEquals(test.getProductsubcategory().getName(), p4.getProductsubcategory().getName());
        assertEquals(test.getProductsubcategory().getProductcategory().getName(), p4.getProductsubcategory().getProductcategory().getName());
        assertEquals(test.getSellstartdate(), p4.getSellstartdate());
        assertEquals(test.getSellenddate(), p4.getSellenddate());
        assertEquals(test.getWeight().doubleValue(), p4.getWeight().doubleValue());
        assertEquals(test.getSize().doubleValue(), p4.getSize().doubleValue());
    }

    // <------------------------> Edit Throws <------------------------>

    @Test
    void testExceptionEdit() {
        serviceProduct.saveProduct(p1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceProduct.editProduct(p2, 1);
        });

        p2.setProductnumber("1");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceProduct.editProduct(p2, 1);
        });

        p2.setSellstartdate(LocalDate.of(2022,03,11));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceProduct.editProduct(p2, 1);
        });

        p2.setSellenddate(LocalDate.of(2022,03,12));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceProduct.editProduct(p2, 1);
        });

        p2.setWeight(BigDecimal.valueOf(12));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceProduct.editProduct(p2, 1);
        });
    }

    // <------------------------> Empty Edit <------------------------>


    @Test
    void testExceptionEditEmpty() {
        serviceProduct.saveProduct(p1);

        Assertions.assertThrows(NullPointerException.class, () -> {
            serviceProduct.editProduct(p3, 1);
        });

        Productsubcategory pSubCategory = new Productsubcategory();
        p3.setProductsubcategory(pSubCategory);

        Assertions.assertThrows(NullPointerException.class, () -> {
            serviceProduct.editProduct(p3, 1);
        });

        Productcategory pCategory = new Productcategory();
        p3.getProductsubcategory().setProductcategory(pCategory);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceProduct.editProduct(p3, 1);
        });

        p3.setProductnumber("1");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceProduct.editProduct(p3, 1);
        });

        p3.setSellstartdate(LocalDate.of(2022,03,11));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceProduct.editProduct(p3, 1);
        });

        p4.setSellenddate(LocalDate.of(2022,03,12));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceProduct.editProduct(p3, 1);
        });

        p3.setWeight(BigDecimal.valueOf(12));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceProduct.editProduct(p3, 1);
        });
    }



}