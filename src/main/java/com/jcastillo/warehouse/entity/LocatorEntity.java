package com.jcastillo.warehouse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "locator")
@Data
public class LocatorEntity extends AbstractEntity {
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;
    private int aisle;
    private int level;
    @Enumerated(EnumType.STRING)
    private LocatorType type;
    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private WarehouseEntity warehouse;
}
