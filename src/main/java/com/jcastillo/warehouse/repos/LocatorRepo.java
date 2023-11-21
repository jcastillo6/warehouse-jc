package com.jcastillo.warehouse.repos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcastillo.warehouse.entity.LocatorEntity;

public interface LocatorRepo extends JpaRepository<LocatorEntity, UUID> {
}
