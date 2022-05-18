package com.taller3.demo.test.dao;

import com.taller3.demo.dao.*;
import com.taller3.demo.model.prod.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductinventoryDaoImpTest {

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

        pi1.setProduct(p1);
        pi1.setLocation(l1);
        pi1.setQuantity(1);

        pi2.setProduct(p2);
        pi2.setLocation(l2);
        pi2.setQuantity(4);

        pi3.setProduct(p3);
        pi3.setLocation(l3);
        pi3.setQuantity(5);

        pi4.setProduct(p4);
        pi4.setLocation(l4);
        pi4.setQuantity(3);

        pi5.setProduct(p5);
        pi5.setLocation(l5);
        pi5.setQuantity(7);
    }


    @Test
    @Order(1)
    void saveAndGet() {
        System.out.println("1");
        dao.save(pi1);
        Productinventory testPI = dao.get(1).get();
        assertNotNull(testPI);
    }

    @Test
    @Order(2)
    void update() {
        System.out.println("2");
        dao.save(pi2);
        dao.save(pi3);
        dao.save(pi4);
        dao.save(pi5);
        Productinventory testPI = dao.get(1).get();

        testPI.setQuantity(78);

        dao.update(testPI);

        Productinventory result = dao.get(1).get();

        assertTrue(result.getQuantity() == 78);

    }

    @Test
    @Order(3)
    void getAll() {
        System.out.println("3");

        List<Productinventory> allProducts = dao.getAll();

        allProducts.forEach(p -> {
            System.out.println("-- " + p.getQuantity());
        });

        assertEquals(allProducts.size(), 5);
    }
}