package com.jcastillo.warehouse.service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jcastillo.warehouse.model.Warehouse;
import com.jcastillo.warehouse.controller.mapper.WarehouseMapper;
import com.jcastillo.warehouse.entity.WarehouseEntity;
import com.jcastillo.warehouse.repos.WarehouseRepo;

@Service
public class WarehouseService implements AbstractService<WarehouseEntity>{

    private final WarehouseRepo warehouseRepo;
    private final WarehouseMapper warehouseMapper;

    public WarehouseService(WarehouseRepo warehouseRepo, WarehouseMapper warehouseMapper) {
        this.warehouseRepo = warehouseRepo;
        this.warehouseMapper = warehouseMapper;
    }
    @Override
    public Set<WarehouseEntity> findAll() {
        return Set.of(warehouseRepo.findAll().toArray(WarehouseEntity[]::new));
    }

    @Override
    public long count() {
        return warehouseRepo.count();
    }

    @Override
    public Optional<WarehouseEntity> findById(UUID id) {
        return warehouseRepo.findById(id);
    }

    @Override
    public WarehouseEntity save(WarehouseEntity warehouseEntity) {
        return warehouseRepo.save(warehouseEntity);
    }

    public WarehouseEntity save(Warehouse warehouse) {
        var warehouseEntity = warehouseMapper.getWarehouse(warehouse);
        if (warehouseEntity.isPresent()) {
            return save(warehouseEntity.get());
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public Optional<WarehouseEntity> getWarehouseEntity(UUID warehouseId) {
        return warehouseRepo.findById(warehouseId);
    }

    @Override
    public void delete(WarehouseEntity entity) {
        warehouseRepo.delete(entity);
    }
}
