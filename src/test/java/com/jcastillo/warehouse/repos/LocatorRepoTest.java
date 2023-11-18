package com.jcastillo.warehouse.repos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("local")
@SpringBootTest
@ExtendWith(SpringExtension.class)
class LocatorRepoTest {

    @Autowired
    private WarehouseRepo warehouseRepo;

    @Test
    void test() {

        var warehouses = warehouseRepo.findAll();
        assertTrue(warehouses.size() > 0);
    }
}