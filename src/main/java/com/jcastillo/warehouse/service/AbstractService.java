package com.jcastillo.warehouse.service;

import java.util.Optional;
import java.util.Set;

public interface AbstractService<T> {
    Set<T> findAll();
    long count();
    Optional<T> findById(Long id);
    T save(T save);
    void delete(T entity);
}
