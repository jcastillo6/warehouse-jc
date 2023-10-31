package com.jcastillo.warehouse.repos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.jcastillo.warehouse.dao.Address;
import com.jcastillo.warehouse.dao.Locator;
import com.jcastillo.warehouse.dao.LocatorType;
import com.jcastillo.warehouse.dao.Warehouse;
import com.jcastillo.warehouse.dao.WarehouseType;

import jakarta.persistence.EntityManager;


@ActiveProfiles("local")
@SpringBootTest
@Transactional(propagation = Propagation.NOT_SUPPORTED) // we're going to handle transactions manually
class LocatorRepoTest {
    @Autowired
    private LocatorRepo locatorRepo;
    @Autowired
    private WarehouseRepo warehouseRepo;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;

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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
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

    @Test
    void test2() {
        transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.execute(status -> {
            Locator locator = new Locator();
            locator.setType(LocatorType.REGULAR);
            locator.setName("1");
            locator.setLevel(1);
            locator.setAisle(1);
            locator.setWarehouse(warehouse);
            entityManager.persist(locator);
            return locator.getId();

        });

        transactionTemplate.execute(status -> {
            warehouse = entityManager.find(Warehouse.class, warehouse.getId());
            assertEquals(1L, (Long) entityManager.createNativeQuery("SELECT count(1) FROM Locator WHERE warehouse_id =:warehouse_id").setParameter("warehouse_id", warehouse.getId()).getSingleResult());
            assertEquals(1, warehouse.getLocators().size());
            return warehouse.getLocators().size();
            });

        transactionTemplate.execute(status -> {
            warehouse = entityManager.find(Warehouse.class, warehouse.getId());
            entityManager.remove(warehouse);
            return "";
        });

        transactionTemplate.execute(status -> {
            assertEquals(0L, (Long) entityManager.createNativeQuery("SELECT count(1) FROM Locator WHERE warehouse_id =:warehouse_id").setParameter("warehouse_id", warehouse.getId()).getSingleResult());
            return "";
        });

    }
}