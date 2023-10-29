package com.jcastillo.warehouse.repos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.BeforeTransaction;
import com.jcastillo.warehouse.dao.Address;
import com.jcastillo.warehouse.dao.Locator;
import com.jcastillo.warehouse.dao.LocatorType;
import com.jcastillo.warehouse.dao.Warehouse;
import com.jcastillo.warehouse.dao.WarehouseType;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ActiveProfiles("local")
@SpringBootTest
class LocatorRepoTest {
    @Autowired
    private LocatorRepo locatorRepo;
    @Autowired
    private WarehouseRepo warehouseRepo;

    @Autowired
    private EntityManager entityManager;
    private Warehouse warehouse;

    @BeforeEach
    void setup() {
        warehouse = new Warehouse();
        warehouse.setLocation(new Address("1", "1", "1"));
        warehouse.setName("w");
        warehouse.setType(WarehouseType.REGULAR);
        warehouseRepo.save(warehouse);
    }

    @BeforeTransaction
    void init() {
        assertEquals(1, locatorRepo.count());
    }

    @Test
    @Transactional
    void test() {
        Locator locator = new Locator();
        locator.setType(LocatorType.REGULAR);
        locator.setName("1");
        locator.setLevel(1);
        locator.setAisle(1);
        locator.setWarehouse(warehouse);
        locatorRepo.save(locator);
        assertEquals(2, locatorRepo.count());
    }
}