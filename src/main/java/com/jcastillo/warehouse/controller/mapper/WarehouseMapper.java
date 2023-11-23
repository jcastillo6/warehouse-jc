package com.jcastillo.warehouse.controller.mapper;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.jcastillo.warehouse.entity.Address;
import com.jcastillo.warehouse.entity.WarehouseEntity;
import com.jcastillo.warehouse.entity.WarehouseType;
import com.jcastillo.warehouse.model.Warehouse;

@Service
@Scope("prototype")
public class WarehouseMapper {
    private final Logger log = LoggerFactory.getLogger(WarehouseMapper.class);
    public WarehouseMapper() {
        log.info("Instance creation");
    }

    public Optional<WarehouseEntity> getWarehouse(Warehouse warehouseApiModel) {
        if (warehouseApiModel == null) {
            return Optional.empty();
        }
        var warehouseDao = new WarehouseEntity();
        var address = new Address(warehouseApiModel.getAddress());
        warehouseDao.setLocation(address);
        warehouseDao.setName(warehouseApiModel.getName());
        //TODO implement get enum value
        warehouseDao.setType(WarehouseType.REGULAR);
        //TODO implement add location logic
        return Optional.of(warehouseDao);
    }

    public Optional<Warehouse> getWarehouse(WarehouseEntity warehouseEntity) {
        if (warehouseEntity == null) {
            return Optional.empty();
        }
        var warehouse = new Warehouse();
        warehouse.setAddress(warehouseEntity.getLocation().getText());
        warehouse.setId(warehouseEntity.getId());
        warehouse.setType(Enum.valueOf(Warehouse.TypeEnum.class, warehouseEntity.getType().name()));
        warehouse.setId(warehouseEntity.getId());
        return Optional.of(warehouse);
    }
}
