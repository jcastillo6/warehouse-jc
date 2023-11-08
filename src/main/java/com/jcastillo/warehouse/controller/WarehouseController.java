package com.jcastillo.warehouse.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jcastillo.warehouse.controller.exception.IllegalOperationException;
import com.jcastillo.warehouse.controller.restmodel.Warehouse;
import com.jcastillo.warehouse.repos.WarehouseRepo;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private WarehouseRepo warehouseRepo;
    public WarehouseController(WarehouseRepo warehouseRepo) {
        this.warehouseRepo = warehouseRepo;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Warehouse> getWarehouses() {
        return warehouseRepo.findAll().stream().map(Warehouse::new).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Warehouse getWarehouses(@Validated(Warehouse.BasicValidation.class) @RequestBody Warehouse warehouseRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorString = getErrorMsg(bindingResult);
            throw new IllegalOperationException(HttpStatus.BAD_REQUEST, "Cannot save entry because of "+errorString);
        }

        var warehouse = new com.jcastillo.warehouse.dao.Warehouse(warehouseRequest);
        warehouseRepo.save(warehouse);

        return new Warehouse(warehouse);
    }

    private String getErrorMsg(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream().map(ObjectError::toString).collect(Collectors.joining(" /n"));
    }
}
