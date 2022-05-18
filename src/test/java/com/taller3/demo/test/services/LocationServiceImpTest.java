package com.taller3.demo.test.services;

import com.taller3.demo.dao.LocationDao;
import com.taller3.demo.model.prod.Location;
import com.taller3.demo.services.LocationServiceImp;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LocationServiceImpTest {

    @Autowired
    private LocationServiceImp serviceLocation;

    @Autowired
    private LocationDao dao;

    Location l1;
    Location l2;
    Location l3;
    Location l4;
    Location l5;

    @BeforeEach
    void setUp() {
        l1 = new Location();
        l1.setName("Locat 1");
        l1.setAvailability(new BigDecimal(2));
        l1.setCostrate(new BigDecimal(1));

        l2 = new Location();
        l2.setName("Lo");
        l2.setAvailability(new BigDecimal(11));
        l2.setCostrate(new BigDecimal(3));

        l3 = new Location();
        /*
        l3.setName("Locat 3");
        l3.setAvailability(new BigDecimal(8));
        l3.setCostrate(new BigDecimal(1));
         */

        l4 = new Location();
        l4.setName("Locat 4");
        l4.setAvailability(new BigDecimal(7));
        l4.setCostrate(new BigDecimal(0));

        l5 = new Location();
        l5.setName("Locat 5");
        l5.setAvailability(new BigDecimal(2));
        l5.setCostrate(new BigDecimal(1));
    }

    // <------------------------> Save <------------------------>

    @Test
    @Order(1)
    void testThatSave() {
        serviceLocation.saveLocation(l1);
        assertNotNull(dao.get(1).get());
    }

    @Test
    @Order(2)
    void testConstraints() {
        Location testLocation = dao.get(1).get();
        assertTrue(testLocation.getName() == "Locat 1");
        assertTrue(testLocation.getAvailability().intValue() == 2);
        assertTrue(testLocation.getCostrate().intValue() == 1);
    }

    // <------------------------> Save Throws <------------------------>

    @Test
    void testExceptionName() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceLocation.saveLocation(l2);
        });

        l2.setName("locat 2");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceLocation.saveLocation(l2);
        });

        l2.setAvailability(new BigDecimal(8));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceLocation.saveLocation(l2);
        });

        l2.setCostrate(new BigDecimal(1));
    }

    // <------------------------> Empty Save <------------------------>

    @Test
    void testExceptionNameEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceLocation.saveLocation(l3);
        });

        l3.setName("locat 3");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceLocation.saveLocation(l3);
        });

        l3.setAvailability(new BigDecimal(2));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceLocation.saveLocation(l3);
        });

        l3.setCostrate(new BigDecimal(1));
    }

    // <------------------------> Edit <------------------------>

    @Test
    void testThatEdit() {
        serviceLocation.saveLocation(l1);
        serviceLocation.editLocation(l4, 1);
        Location test = dao.get(1).get();
        assertEquals(test.getName(), l4.getName());
        assertEquals(test.getAvailability().intValue(), l4.getAvailability().intValue());
        assertEquals(test.getCostrate().intValue(), l4.getCostrate().intValue());
    }

    // <------------------------> Edit Throws <------------------------>

    @Test
    void testExceptionEdit() {
        serviceLocation.saveLocation(l1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceLocation.editLocation(l2, 1);
        });

        l2.setName("locat 2");

        Assertions.assertThrows(IllegalArgumentException.class, () -> serviceLocation.editLocation(l2, 1));

        l2.setAvailability(new BigDecimal(1));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceLocation.editLocation(l2, 1);
        });
    }

    // <------------------------> Edit Empty <------------------------>

    @Test
    void testEmptyEdit() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceLocation.editLocation(l3, 1);
        });

        l3.setName("locat 3");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceLocation.editLocation(l3, 1);
        });

        l3.setAvailability(new BigDecimal(1));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serviceLocation.editLocation(l3, 1);
        });
    }
}