package com.jcastillo.warehouse.repos;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jcastillo.warehouse.dao.Warehouse;

public interface WarehouseRepo extends JpaRepository<Warehouse, Long> {
    @Query("SELECT l FROM Locator l WHERE l.id = ?1")
    Optional<Warehouse> findByLocatorId(Long locatorId);

    @Override
    @CacheEvict(value = "byName", key = "#p0.name")
    <S extends Warehouse> S save(S entity);

    @Cacheable("byName")
    Optional<Warehouse> findByName(String name);

}
