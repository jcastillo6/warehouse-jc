package com.jcastillo.warehouse.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jcastillo.warehouse.dao.Warehouse;

public interface WarehouseRepo extends JpaRepository<Warehouse, Long> {
    @Query("SELECT l FROM Locator l WHERE l.id = ?1")
    Optional<Warehouse> findByLocatorId(Long locatorId);
}
