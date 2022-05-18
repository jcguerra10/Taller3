package com.taller3.demo.test.services;

import com.taller3.demo.dao.*;
import com.taller3.demo.model.prod.*;
import com.taller3.demo.services.ProductInventoryServiceImp;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductInventoryServiceImpTest {

    @Autowired
    private ProductInventoryServiceImp serviceImp;

    @Autowired
    private ProductinventoryDaoImp dao;

    @Autowired
    private ProductDaoImp daoProduct;
    @Autowired
    private ProductcategoryDao pc;
    @Autowired
    private ProductsubcategoryDao psc;

    @Autowired
    private LocationDao daoLocation;

    Productinventory pi1 = new Productinventory();
    Productinventory pi2 = new Productinventory();
    Productinventory pi3 = new Productinventory();
    Productinventory pi4 = new Productinventory();
    Productinventory pi5 = new Productinventory();

    Product p1 = new Product();
    Product p2 = new Product();
    Product p3 = new Product();
    Product p4 = new Product();
    Product p5 = new Product();

    Location l1 = new Location();
    Location l2 = new Location();
    Location l3 = new Location();
    Location l4 = new Location();
    Location l5 = new Location();

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

        daoProduct.save(p1);
        daoProduct.save(p2);
        daoProduct.save(p3);
        daoProduct.save(p4);
        daoProduct.save(p5);

        l1.setName("Locat 1");
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

        daoLocation.save(l1);
        daoLocation.save(l2);
        daoLocation.save(l3);
        daoLocation.save(l4);
        daoLocation.save(l5);

        pi1 = new Productinventory();
        pi1.setProduct(p1);
        pi1.setLocation(l1);
        pi1.setQuantity(1);

        pi2 = new Productinventory();
        pi2.setProduct(p2);
        pi2.setLocation(l2);
        pi2.setQuantity(-1);

        pi3 = new Productinventory();

        pi4 = new Productinventory();
        pi4.setProduct(p4);
        pi4.setLocation(l4);
        pi4.setQuantity(3);

        pi5 = new Productinventory();
        pi5.setProduct(p5);
        pi5.setLocation(l5);
        pi5.setQuantity(7);
    }

    // <------------------------> Save <------------------------>

    @Test
    @Order(1)
    void testThatSave() {
        serviceImp.saveProductInventory(pi1);
        assertNotNull(dao.get(1).get());
    }

    @Test
    void testConstraints() {
        serviceImp.saveProductInventory(pi1);
        Productinventory test = dao.get(1).get();
        assertNotNull(test.getProduct());
        assertNotNull(test.getLocation());
        assertTrue(test.getQuantity() > 0);
    }

    // <------------------------> Save Throws <------------------------>

    @Test
    void testExceptionSave() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceImp.saveProductInventory(pi2);
        });

    }

    // <------------------------> Empty Save <------------------------>

    @Test
    void testExceptionSaveEmpty() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            serviceImp.saveProductInventory(pi3);
        });

        Product proc = new Product();
        proc.setProductid(1);
        pi3.setProduct(proc);

        Assertions.assertThrows(NullPointerException.class, () -> {
            serviceImp.saveProductInventory(pi3);
        });

        Location loc = new Location();
        loc.setLocationid(1);
        pi3.setLocation(loc);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceImp.saveProductInventory(pi3);
        });
    }

    // <------------------------> Edit <------------------------>

    @Test
    void testThatEdit() {
        serviceImp.editProductInventory(pi4, 1);
        Productinventory test = dao.get(1).get();
        assertEquals(test.getLocation().getName() , pi4.getLocation().getName());
        assertEquals(test.getProduct().getName(), pi4.getProduct().getName());
        assertEquals(test.getQuantity(), pi4.getQuantity());
    }

    // <------------------------> Edit Throws <------------------------>

    @Test
    void testExceptionEdit() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceImp.editProductInventory(pi2, 1);
        });
    }


    // <------------------------> Empty Edit <------------------------>

    @Test
    void testExceptionEditEmpty() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            serviceImp.editProductInventory(pi3, 1);
        });

        Product proc = new Product();
        proc.setProductid(1);
        pi3.setProduct(proc);

        Assertions.assertThrows(NullPointerException.class, () -> {
            serviceImp.editProductInventory(pi3, 1);
        });

        Location loc = new Location();
        loc.setLocationid(1);
        pi3.setLocation(loc);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceImp.editProductInventory(pi3, 1);
        });
    }

}