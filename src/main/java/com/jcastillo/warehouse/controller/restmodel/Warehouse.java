package com.jcastillo.warehouse.controller.restmodel;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
    public interface BasicValidation {}

    @NotNull(groups = BasicValidation.class)
    @Size(min = 3, max = 30, groups = BasicValidation.class)
    private String name;

    @NotNull(groups = BasicValidation.class)
    @Size(min = 3, max = 30, groups = BasicValidation.class)
    private String location;

    public Warehouse(com.jcastillo.warehouse.dao.Warehouse warehouseDao) {
        this.name = warehouseDao.getName();
        this.location = warehouseDao.getLocation().getAddress1();
    }

}
