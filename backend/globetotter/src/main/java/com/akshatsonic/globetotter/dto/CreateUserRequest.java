package com.akshatsonic.globetotter.dto;

import com.akshatsonic.globetotter.enums.UserRole;
import lombok.Data;
import lombok.NonNull;

@Data
public class CreateUserRequest {
    @NonNull
    private String username;
    @NonNull
    private String password;
    private UserRole role=UserRole.PLAYER;
}
