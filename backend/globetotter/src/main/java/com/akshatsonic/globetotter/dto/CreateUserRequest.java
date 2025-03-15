package com.akshatsonic.globetotter.dto;

import com.akshatsonic.globetotter.enums.UserRole;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class CreateUserRequest {
    @NonNull
    private String username;
    @NonNull
    private String password;

    @Builder.Default
    private UserRole role=UserRole.PLAYER;
}
