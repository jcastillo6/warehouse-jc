package com.jcastillo.warehouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.jcastillo.warehouse.api.WarehouseApi;
import com.jcastillo.warehouse.api.model.Locator;
import com.jcastillo.warehouse.api.model.Warehouse;
import com.jcastillo.warehouse.controller.exception.IllegalOperationException;
import com.jcastillo.warehouse.controller.mapper.WarehouseMapper;
import com.jcastillo.warehouse.repos.WarehouseRepo;


@RestController
public class WarehouseApiController implements WarehouseApi {
    private static final Logger log = LoggerFactory.getLogger(WarehouseApiController.class);
    private WarehouseRepo warehouseRepo;
    private WarehouseMapper warehouseMapper;

    public WarehouseApiController(WarehouseRepo warehouseRepo, WarehouseMapper mapper) {
        this.warehouseRepo = warehouseRepo;
        this.warehouseMapper = mapper;
    }

    @Override
    public ResponseEntity<List<Warehouse>> createWarehouse(Warehouse body) {
        log.info("Request for warehouse creation with id {}", body.getId());
        var warehouseOpt = warehouseMapper.getWarehouse(body);
        if (warehouseOpt.isPresent()) {
            warehouseRepo.save(warehouseOpt.get());
        }
        else {
            throw new IllegalOperationException(HttpStatus.BAD_REQUEST, "Cannot save entry");
        }

        return ResponseEntity.ok(List.of(body));
    }

    @Override
    public ResponseEntity<List<Warehouse>> getWarehouseById(String warehouseId) {
        return WarehouseApi.super.getWarehouseById(warehouseId);
    }

    @Override
    public ResponseEntity<List<Locator>> getWarehouseByWarehouseId(String warehouseId) {
        return WarehouseApi.super.getWarehouseByWarehouseId(warehouseId);
    }

    @Override
    public ResponseEntity<List<Warehouse>> getWarehouses() {
        return WarehouseApi.super.getWarehouses();
    }
}
