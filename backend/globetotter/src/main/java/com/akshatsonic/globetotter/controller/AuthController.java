package com.akshatsonic.globetotter.controller;

import com.akshatsonic.globetotter.dto.CreateUserRequest;
import com.akshatsonic.globetotter.dto.LoginRequest;
import com.akshatsonic.globetotter.dto.LogoutRequest;
import com.akshatsonic.globetotter.models.Session;
import com.akshatsonic.globetotter.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authService;

    @PostMapping("/user")
    public void createUser(@RequestBody CreateUserRequest createUserRequest){
        authService.createUser(createUserRequest);
    }

    @PostMapping("/login")
    public Session login(@RequestBody LoginRequest loginRequest){
        return authService.authenticate(loginRequest);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody LogoutRequest logoutRequest){
        authService.logout(logoutRequest);
    }

}
