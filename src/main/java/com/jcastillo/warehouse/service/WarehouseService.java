package com.jcastillo.warehouse.service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jcastillo.warehouse.api.model.Warehouse;
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

    public Warehouse save(Warehouse warehouse) {
        var warehouseEntity = warehouseMapper.getWarehouse(warehouse);
        if (warehouseEntity.isPresent()) {
            var warehousePersisted = save(warehouseEntity.get());
            warehouse.setId(warehousePersisted.getId());
            return warehouse;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public Optional<Warehouse> getWarehouseEntity(UUID warehouseId) {
        var warehouseOpt = findById(warehouseId);
        if (warehouseOpt.isPresent()) {
            var warehouseEntity = warehouseOpt.get();
            return warehouseMapper.getWarehouse(warehouseEntity);
        }

        return Optional.empty();
    }

    @Override
    public void delete(WarehouseEntity entity) {
        warehouseRepo.delete(entity);
    }
}
