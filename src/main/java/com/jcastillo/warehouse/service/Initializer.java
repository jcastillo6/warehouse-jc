package com.jcastillo.warehouse.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jcastillo.warehouse.dao.Address;
import com.jcastillo.warehouse.dao.Locator;
import com.jcastillo.warehouse.dao.LocatorType;
import com.jcastillo.warehouse.dao.Warehouse;
import com.jcastillo.warehouse.dao.WarehouseType;

import jakarta.annotation.PostConstruct;

@Service
public class Initializer {
    private Logger log = LoggerFactory.getLogger(Initializer.class);
    private WarehouseService warehouseService;
    private LocatorService locatorService;

    public Initializer(WarehouseService warehouse, LocatorService locator) {
        this.warehouseService = warehouse;
        this.locatorService = locator;
    }

    @PostConstruct
    public void init() {
        log.info("+++++ Starting database initialization +++++++");
        var warehouse = new Warehouse();
        warehouse.setName("Warehouse 1");
        warehouse.setType(WarehouseType.REGULAR);
        warehouse.setLocation(new Address("Trigal", "10010201", "00001201"));
        warehouseService.save(warehouse);

        var locator = new Locator();
        locator.setName("Locator 1");
        locator.setLevel(1);
        locator.setAisle(1);
        locator.setType(LocatorType.REGULAR);
        locator.setWarehouse(warehouse);
        locatorService.save(locator);
        log.info("+++++ Starting database end +++++++");
    }
}
