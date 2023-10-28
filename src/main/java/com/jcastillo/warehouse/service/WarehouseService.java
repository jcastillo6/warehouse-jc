package com.jcastillo.warehouse.service;

import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.jcastillo.warehouse.dao.Warehouse;
import com.jcastillo.warehouse.repos.WarehouseRepo;

@Service
public class WarehouseService implements AbstractService<Warehouse>{

    private WarehouseRepo warehouseRepo;

    public WarehouseService(WarehouseRepo warehouseRepo) {
        this.warehouseRepo = warehouseRepo;
    }
    @Override
    public Set<Warehouse> findAll() {
        return Set.of(warehouseRepo.findAll().toArray(Warehouse[]::new));
    }

    @Override
    public long count() {
        return warehouseRepo.count();
    }

    @Override
    public Optional<Warehouse> findById(Long id) {
        return warehouseRepo.findById(id);
    }

    @Override
    public Warehouse save(com.jcastillo.warehouse.dao.Warehouse save) {
        return warehouseRepo.save(save);
    }

    @Override
    public void delete(Warehouse entity) {
        warehouseRepo.delete(entity);
    }
}
