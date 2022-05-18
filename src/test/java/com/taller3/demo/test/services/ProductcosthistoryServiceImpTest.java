package com.taller3.demo.test.services;

import com.taller3.demo.dao.ProductDaoImp;
import com.taller3.demo.dao.ProductcategoryDao;
import com.taller3.demo.dao.ProductcosthistoryDaoImp;
import com.taller3.demo.dao.ProductsubcategoryDao;
import com.taller3.demo.model.prod.Product;
import com.taller3.demo.model.prod.Productcategory;
import com.taller3.demo.model.prod.Productcosthistory;
import com.taller3.demo.model.prod.Productsubcategory;
import com.taller3.demo.services.ProductcosthistoryServiceImp;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductcosthistoryServiceImpTest {

    @Autowired
    private ProductcosthistoryServiceImp serviceImp;

    @Autowired
    private ProductcosthistoryDaoImp dao;

    @Autowired
    private ProductDaoImp daoProduct;

    @Autowired
    private ProductcategoryDao pc;
    @Autowired
    private ProductsubcategoryDao psc;

    Productcosthistory pch1 = new Productcosthistory();
    Productcosthistory pch2 = new Productcosthistory();
    Productcosthistory pch3 = new Productcosthistory();
    Productcosthistory pch4 = new Productcosthistory();
    Productcosthistory pch5 = new Productcosthistory();

    Product p1 = new Product();
    Product p2 = new Product();
    Product p3 = new Product();
    Product p4 = new Product();
    Product p5 = new Product();

    @BeforeEach
    void test() {
        Productcategory pCategory1 = new Productcategory();
        pCategory1.setName("Tech");

        Productsubcategory pSubCategory1 = new Productsubcategory();
        pSubCategory1.setName("Celular");
        pSubCategory1.setProductcategory(pCategory1);

        pc.save(pCategory1);
        psc.save(pSubCategory1);

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
        p2.setSellenddate(LocalDate.of(3000, 3, 15));
        p2.setProductsubcategory(pSubCategory1);
        p2.setSize(BigDecimal.valueOf(12));
        p2.setWeight(BigDecimal.valueOf(12));

        p3.setName("Switch");
        p3.setProductnumber("53");
        p3.setSellstartdate(LocalDate.of(2021, 3, 14));
        p3.setSellenddate(LocalDate.of(2021, 3, 15));
        p3.setProductsubcategory(pSubCategory1);
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

        daoProduct.save(p1);
        daoProduct.save(p2);
        daoProduct.save(p3);
        daoProduct.save(p4);
        daoProduct.save(p5);

        pch1 = new Productcosthistory();
        pch1.setProduct(p1);
        pch1.setStartdate(LocalDate.of(2022, 3, 14));
        pch1.setEnddate(LocalDate.of(2022, 3, 15));
        pch1.setStandardcost(BigDecimal.valueOf(25000));

        pch2 = new Productcosthistory();
        pch2.setProduct(p2);
        pch2.setStartdate(LocalDate.of(2022, 3, 14));
        pch2.setEnddate(LocalDate.of(3000, 3, 15));
        pch2.setStandardcost(BigDecimal.valueOf(-1));

        pch3 = new Productcosthistory();

        pch4 = new Productcosthistory();
        pch4.setProduct(p4);
        pch4.setStartdate(LocalDate.of(2022, 3, 14));
        pch4.setEnddate(LocalDate.of(2022, 3, 15));
        pch4.setStandardcost(BigDecimal.valueOf(25000));

        pch5 = new Productcosthistory();
        pch5.setProduct(p5);
        pch5.setStartdate(LocalDate.of(2022, 3, 14));
        pch5.setEnddate(LocalDate.of(2022, 3, 15));
        pch5.setStandardcost(BigDecimal.valueOf(25000));
    }

    // <------------------------> Save <------------------------>

    @Test
    void testThatSave() {
        serviceImp.saveProductcosthistory(pch1);
        assertNotNull(dao.get(1).get());
    }

    @Test
    void testConstraints() {
        serviceImp.saveProductcosthistory(pch1);
        Productcosthistory test = dao.get(1).get();
        assertNotNull(test.getProduct());
        assertTrue(test.getEnddate().isBefore(LocalDate.now()));
        assertTrue(test.getStandardcost().doubleValue() >= 0);
    }

    // <------------------------> Save Throws <------------------------>


    @Test
    void testExceptionSave() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceImp.saveProductcosthistory(pch2);
        });

        pch2.setEnddate(LocalDate.of(2022,03,10));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceImp.saveProductcosthistory(pch2);
        });
    }

    // <------------------------> Empty Save <------------------------>

    @Test
    void testExceptionEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceImp.saveProductcosthistory(pch3);
        });

        Product proc = new Product();
        proc.setProductid(1);
        pch3.setProduct(proc);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceImp.saveProductcosthistory(pch3);
        });

        pch3.setEnddate(LocalDate.of(2022,03,10));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceImp.saveProductcosthistory(pch3);
        });
    }


    // <------------------------> Edit <------------------------>

    @Test
    void testThatEdit() {
        serviceImp.saveProductcosthistory(pch1);
        serviceImp.editProductcosthistory(pch4, 1);
        Productcosthistory test = dao.get(1).get();
        assertEquals(test.getProduct().getName(), pch4.getProduct().getName());
        assertEquals(test.getEnddate(), pch4.getEnddate());
        assertEquals(test.getStandardcost().intValue(), pch4.getStandardcost().intValue());
    }


    // <------------------------> Edit Throws <------------------------>

    @Test
    void testExceptionEdit() {
        serviceImp.saveProductcosthistory(pch1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceImp.editProductcosthistory(pch2, 1);
        });

        pch3.setEnddate(LocalDate.of(2022,03,20));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceImp.editProductcosthistory(pch2, 1);
        });
    }

    // <------------------------> Empty Edit <------------------------>

    @Test
    void testExceptionEditEmpty() {
        serviceImp.saveProductcosthistory(pch1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceImp.editProductcosthistory(pch3, 1);
        });

        Product proc = new Product();
        proc.setProductid(1);
        pch2.setProduct(proc);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceImp.editProductcosthistory(pch3, 1);
        });

        pch3.setEnddate(LocalDate.of(2022,03,10));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceImp.editProductcosthistory(pch3, 1);
        });
    }
}