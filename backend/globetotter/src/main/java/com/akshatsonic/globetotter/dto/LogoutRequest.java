package com.akshatsonic.globetotter.dto;

import lombok.Data;

@Data
public class LogoutRequest {
    private String sessionToken;
    private Long userId;
}
