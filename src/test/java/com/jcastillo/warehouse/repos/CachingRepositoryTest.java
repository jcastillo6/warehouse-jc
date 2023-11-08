package com.jcastillo.warehouse.repos;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jcastillo.warehouse.dao.Address;
import com.jcastillo.warehouse.dao.Warehouse;
import com.jcastillo.warehouse.dao.WarehouseType;

@Transactional
@SpringBootTest
public class CachingRepositoryTest {

    @Autowired WarehouseRepo repository;
    @Autowired CacheManager cacheManager;

    @Test
    void checkCachedValue() {

        var warehouse = new Warehouse();
        warehouse.setName("beval");
        warehouse.setType(WarehouseType.REGULAR);
        warehouse.setLocation(new Address("2", "", ""));

        warehouse = repository.save(warehouse);

        assertThat(repository.findByName("beval").get()).isEqualTo(warehouse);

        // Verify entity cached
        var cache = cacheManager.getCache("byName");
        assertThat(repository.findByName("beval").get()).isEqualTo(warehouse);
    }

    @Test
    void checkCacheEviction() {

        var warehouse = new Warehouse();
        warehouse.setName("beval");
        warehouse.setType(WarehouseType.REGULAR);
        warehouse.setLocation(new Address("2", "", ""));
        warehouse = repository.save(warehouse);

        // Verify entity evicted on cache
        var cache = cacheManager.getCache("byName");
        assertThat(cache.get("beval")).isNull();
    }
}

