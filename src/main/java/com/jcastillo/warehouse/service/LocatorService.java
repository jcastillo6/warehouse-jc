package com.jcastillo.warehouse.service;

import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;

import com.jcastillo.warehouse.dao.Locator;
import com.jcastillo.warehouse.repos.LocatorRepo;

@Service
public class LocatorService implements AbstractService<Locator> {
    private LocatorRepo locatorRepo;

    public LocatorService(LocatorRepo locatorRepo) {
        this.locatorRepo =  locatorRepo;
    }
    @Override
    public Set<Locator> findAll() {
        return Set.of(locatorRepo.findAll().toArray(Locator[]::new));
    }

    @Override
    public long count() {
        return locatorRepo.count();
    }

    @Override
    public Optional<Locator> findById(Long id) {
        return locatorRepo.findById(id);
    }

    @Override
    public Locator save(Locator save) {
        return locatorRepo.save(save);
    }

    @Override
    public void delete(Locator entity) {
        locatorRepo.delete(entity);
    }
}
