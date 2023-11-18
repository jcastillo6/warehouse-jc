package com.jcastillo.warehouse.controller.mapper;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jcastillo.warehouse.api.model.Warehouse;
import com.jcastillo.warehouse.dao.Address;
import com.jcastillo.warehouse.dao.WarehouseType;

@Service
@Scope("prototype")
public class WarehouseMapper {
    private final Logger log = LoggerFactory.getLogger(WarehouseMapper.class);
    public WarehouseMapper() {
        log.info("Instance creation");
    }

    public Optional<com.jcastillo.warehouse.dao.Warehouse> getWarehouse(Warehouse warehouseApiModel) {
        if (warehouseApiModel == null) {
            return Optional.empty();
        }
        var warehouseDao = new com.jcastillo.warehouse.dao.Warehouse();
        var address = new Address(warehouseApiModel.getAddress());
        warehouseDao.setLocation(address);
        warehouseDao.setName(warehouseApiModel.getId());
        //TODO implement get enum value
        warehouseDao.setType(WarehouseType.REGULAR);
        //TODO implement add location logic
        return Optional.of(warehouseDao);
    }

}
