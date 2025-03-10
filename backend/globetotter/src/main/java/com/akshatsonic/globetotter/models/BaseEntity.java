package com.akshatsonic.globetotter.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
public abstract class BaseEntity {
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;
}
