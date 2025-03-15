package com.akshatsonic.globetotter.controller;

import com.akshatsonic.globetotter.dto.CreateUserRequest;
import com.akshatsonic.globetotter.dto.GenericResponseEntity;
import com.akshatsonic.globetotter.dto.LoginRequest;
import com.akshatsonic.globetotter.dto.LogoutRequest;
import com.akshatsonic.globetotter.dto.SessionResponseDto;
import com.akshatsonic.globetotter.models.Session;
import com.akshatsonic.globetotter.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.akshatsonic.globetotter.Constants.SESSION_TOKEN_HEADER;
import static com.akshatsonic.globetotter.Constants.USER_ID_HEADER;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authService;

    @PostMapping("/user")
    public GenericResponseEntity createUser(@RequestBody CreateUserRequest createUserRequest){
        authService.createUser(createUserRequest);
        return GenericResponseEntity.builder()
                .statusCode(200)
                .message("User created successfully")
                .build();
    }

    @PostMapping("/login")
    public GenericResponseEntity<SessionResponseDto> login(@RequestBody LoginRequest loginRequest){
        SessionResponseDto sessionResponse =  authService.authenticate(loginRequest);
        return GenericResponseEntity.<SessionResponseDto>builder()
                .statusCode(200)
                .message("Login successful")
                .data(sessionResponse)
                .build();
    }

    @PostMapping("/logout")
    public GenericResponseEntity logout(
            @RequestHeader(SESSION_TOKEN_HEADER) String sessionToken,
            @RequestHeader(USER_ID_HEADER) Long userId
    ){
        LogoutRequest logoutRequest = LogoutRequest.builder()
                .sessionToken(sessionToken)
                .userId(userId)
                .build();
        authService.logout(logoutRequest);
        return GenericResponseEntity.builder()
                .statusCode(200)
                .message("Logout successful")
                .build();
    }

}
