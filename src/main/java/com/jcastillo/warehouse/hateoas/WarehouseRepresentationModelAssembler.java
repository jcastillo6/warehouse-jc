package com.jcastillo.warehouse.hateoas;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.jcastillo.warehouse.model.Warehouse;
import com.jcastillo.warehouse.controller.WarehouseApiController;
import com.jcastillo.warehouse.entity.WarehouseEntity;

public class WarehouseRepresentationModelAssembler extends RepresentationModelAssemblerSupport<WarehouseEntity, Warehouse> {

    public WarehouseRepresentationModelAssembler() {
        super(WarehouseApiController.class, Warehouse.class);
    }

    @Override
    public Warehouse toModel(WarehouseEntity entity) {
        return null;
    }
}
