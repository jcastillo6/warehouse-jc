package com.jcastillo.warehouse.dao;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
    @Column(nullable = false)
    private String text;
    private String latitude;
    private String longitude;

    public Address(String address) {
        this.text = address;
    }
}
