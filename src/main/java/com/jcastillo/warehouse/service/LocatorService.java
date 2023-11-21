package com.jcastillo.warehouse.service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jcastillo.warehouse.entity.LocatorEntity;
import com.jcastillo.warehouse.repos.LocatorRepo;

@Service
public class LocatorService implements AbstractService<LocatorEntity> {
    private final LocatorRepo locatorRepo;

    public LocatorService(LocatorRepo locatorRepo) {
        this.locatorRepo =  locatorRepo;
    }
    @Override
    public Set<LocatorEntity> findAll() {
        return Set.of(locatorRepo.findAll().toArray(LocatorEntity[]::new));
    }

    @Override
    public long count() {
        return locatorRepo.count();
    }

    @Override
    public Optional<LocatorEntity> findById(UUID id) {
        return locatorRepo.findById(id);
    }

    @Override
    public LocatorEntity save(LocatorEntity save) {
        return locatorRepo.save(save);
    }

    @Override
    public void delete(LocatorEntity entity) {
        locatorRepo.delete(entity);
    }
}
