package com.jcastillo.warehouse.controller;

import static org.springframework.http.ResponseEntity.status;

import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.jcastillo.warehouse.api.WarehouseApi;
import com.jcastillo.warehouse.hateoas.WarehouseRepresentationModelAssembler;
import com.jcastillo.warehouse.model.Locator;
import com.jcastillo.warehouse.model.Warehouse;
import com.jcastillo.warehouse.service.WarehouseService;


@RestController
public class WarehouseApiController implements WarehouseApi {
    private static final Logger log = LoggerFactory.getLogger(WarehouseApiController.class);
    private final WarehouseService warehouseService;
    private final WarehouseRepresentationModelAssembler assembler;

    public WarehouseApiController(WarehouseService warehouseService, WarehouseRepresentationModelAssembler assembler) {
        this.warehouseService = warehouseService;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<List<Warehouse>> createWarehouse(Warehouse body) {
        log.info("Request for warehouse creation with id {}", body.getId());
        var warehouseEntity = warehouseService.save(body);
        var warehouseModel = assembler.toModel(warehouseEntity);
        return status(HttpStatus.CREATED).body(List.of(warehouseModel));
    }

    @Override
    public ResponseEntity<List<Warehouse>> getWarehouseById(String warehouseId) {
        var warehouseEntityOpt = warehouseService.getWarehouseEntity(UUID.fromString(warehouseId));
        return warehouseEntityOpt.map(warehouse -> ResponseEntity.ok().cacheControl(CacheControl.maxAge(5, TimeUnit.DAYS)).eTag(String.valueOf(warehouse.getUpdatedAt().atZone(ZoneId.systemDefault()).toEpochSecond())).body(List.of(assembler.toModel(warehouse))))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<Locator>> getLocatorsByWarehouseId(String warehouseId) {
        return WarehouseApi.super.getLocatorsByWarehouseId(warehouseId);
    }

    @Override
    public ResponseEntity<List<Warehouse>> getWarehouses() {
        var warehouses = warehouseService.findAll();
        return ResponseEntity.ok(assembler.toListModel(warehouses));
    }
}
