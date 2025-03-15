package com.akshatsonic.globetotter.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponseEntity<T> {
    private int statusCode;
    private String message;
    private T data;
}
