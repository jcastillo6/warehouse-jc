package com.jcastillo.warehouse.controller;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import com.jcastillo.warehouse.controller.restmodel.Warehouse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
class WarehouseControllerTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void getWarehouses() {
        List<Warehouse> warehouses = given()
            .when().get("/warehouses/")
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .and().contentType(ContentType.JSON)
            .and().extract().response().jsonPath().getList("$");
        assertNotNull(warehouses);
        assertTrue(warehouses.size() >= 1);
    }
}