package com.jcastillo.warehouse.repos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcastillo.warehouse.entity.WarehouseEntity;

public interface WarehouseRepo extends JpaRepository<WarehouseEntity, UUID> {
}
