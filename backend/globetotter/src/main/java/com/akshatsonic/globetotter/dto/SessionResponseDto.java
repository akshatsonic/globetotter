package com.akshatsonic.globetotter.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionResponseDto {
    private String sessionToken;
}
