package com.jcastillo.warehouse.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
public class Locator extends AbstractEntity {
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;
    private int aisle;
    private int level;
    @Enumerated(EnumType.STRING)
    private LocatorType type;
    @ManyToOne
    @JoinColumn(
        name = "warehouse_id",
        nullable = false,
        referencedColumnName = "id",
        foreignKey = @ForeignKey(
            name = "warehouse_locator_fk"
        )
    )
    private Warehouse warehouse;
}
