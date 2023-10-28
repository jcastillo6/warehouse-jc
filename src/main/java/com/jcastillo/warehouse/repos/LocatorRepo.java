package com.jcastillo.warehouse.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcastillo.warehouse.dao.Locator;

public interface LocatorRepo extends JpaRepository<Locator, Long> {
}
