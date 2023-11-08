package com.jcastillo.warehouse.dao;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Warehouse extends AbstractEntity {
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;
    @Embedded
    private Address location;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WarehouseType type;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse")
    private Set<Locator> locators;

    public Warehouse(com.jcastillo.warehouse.controller.restmodel.Warehouse warehouseRest) {
        this.name = warehouseRest.getName();
        this.location = new Address(warehouseRest.getLocation(), null, null);
        this.type = WarehouseType.REGULAR;
    }
}
