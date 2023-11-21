package com.jcastillo.warehouse.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "warehouse")
@Data
public class WarehouseEntity extends AbstractEntity {
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;
    @Embedded
    private Address location;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WarehouseType type;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse", cascade = CascadeType.ALL)
    private Set<LocatorEntity> locators;

}
