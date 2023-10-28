package com.jcastillo.warehouse.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcastillo.warehouse.dao.Warehouse;

public interface WarehouseRepo extends JpaRepository<Warehouse, Long> {
}
