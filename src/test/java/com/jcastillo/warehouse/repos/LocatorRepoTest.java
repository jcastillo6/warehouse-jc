package com.jcastillo.warehouse.repos;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.invoke.StringConcatException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jcastillo.warehouse.WarehouseApplication;
import com.jcastillo.warehouse.dao.Warehouse;

@ActiveProfiles("local")
@ExtendWith(SpringExtension.class)
class LocatorRepoTest {

    @Test
    void test() {
        assertTrue(true);
    }
}