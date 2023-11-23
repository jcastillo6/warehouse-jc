package com.jcastillo.warehouse.controller;

import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.jcastillo.warehouse.api.WarehouseApi;
import com.jcastillo.warehouse.model.Locator;
import com.jcastillo.warehouse.model.Warehouse;
import com.jcastillo.warehouse.service.WarehouseService;


@RestController
public class WarehouseApiController implements WarehouseApi {
    private static final Logger log = LoggerFactory.getLogger(WarehouseApiController.class);
    private final WarehouseService warehouseService;

    public WarehouseApiController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Override
    public ResponseEntity<List<Warehouse>> createWarehouse(Warehouse body) {
        log.info("Request for warehouse creation with id {}", body.getId());
        var warehouseResponse = warehouseService.save(body);
        return ResponseEntity.ok(List.of(warehouseResponse));
    }

    @Override
    public ResponseEntity<List<Warehouse>> getWarehouseById(String warehouseId) {
        var warehouseOpt = warehouseService.getWarehouseEntity(UUID.fromString(warehouseId));
        return warehouseOpt.map(warehouse -> ResponseEntity.ok(List.of(warehouse))).orElseGet(() -> ResponseEntity.notFound().build());
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
