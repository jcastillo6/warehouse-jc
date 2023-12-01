package com.jcastillo.warehouse.hateoas;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.jcastillo.warehouse.model.Warehouse;
import com.jcastillo.warehouse.controller.WarehouseApiController;
import com.jcastillo.warehouse.entity.WarehouseEntity;
import com.jcastillo.warehouse.service.LocatorService;

@Component
public class WarehouseRepresentationModelAssembler extends RepresentationModelAssemblerSupport<WarehouseEntity, Warehouse> {

    private LocatorService locatorService;

    public WarehouseRepresentationModelAssembler(LocatorService locatorService) {
        super(WarehouseApiController.class, Warehouse.class);
        this.locatorService = locatorService;
    }

    public WarehouseRepresentationModelAssembler() {
        super(WarehouseApiController.class, Warehouse.class);
    }

    @Override
    public Warehouse toModel(WarehouseEntity entity) {
        Warehouse resource = createModelWithId(entity.getId(), entity);
        resource.setAddress(entity.getLocation().getText());
        resource.name(entity.getName());
        resource.setId(entity.getId());
        resource.setType(Enum.valueOf(Warehouse.TypeEnum.class, entity.getType().name()));
        resource.add(linkTo(methodOn(WarehouseApiController.class).getWarehouseById(entity.getId().toString())).withSelfRel());
        resource.add(linkTo(methodOn(WarehouseApiController.class).getLocatorsByWarehouseId(entity.getId().toString()))
            .withRel("warehouse-locators"));
        return resource;
    }

    public List<Warehouse> toListModel(Iterable<WarehouseEntity> warehousesEntity) {
        if (Objects.isNull(warehousesEntity)) {
            return List.of();
        }
        return StreamSupport.stream(warehousesEntity.spliterator(), false)
            .map(this::toModel)
            .collect(Collectors.toList());
    }
}
