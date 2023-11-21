package com.jcastillo.warehouse.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import com.jcastillo.warehouse.util.DateProcessor;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(updatable = false)
    protected UUID id;
    @Version
    protected int version;
    @NotNull
    @Column(name = "created_at", nullable = false)
    @DateTimeFormat(pattern = DateProcessor.DATE_FORMAT)
    protected LocalDateTime createdAt;
    @NotNull
    @Column(name = "updated_at", nullable = false)
    @DateTimeFormat(pattern = DateProcessor.DATE_FORMAT)
    protected LocalDateTime updatedAt;

    protected AbstractEntity() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
}
