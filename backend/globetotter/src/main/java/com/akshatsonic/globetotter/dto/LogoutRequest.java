package com.akshatsonic.globetotter.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogoutRequest {
    private String sessionToken;
    private Long userId;
}
