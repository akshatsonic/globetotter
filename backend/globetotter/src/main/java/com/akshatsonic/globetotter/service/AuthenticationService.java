package com.akshatsonic.globetotter.service;

import com.akshatsonic.globetotter.dto.CreateUserRequest;
import com.akshatsonic.globetotter.dto.LoginRequest;
import com.akshatsonic.globetotter.dto.LogoutRequest;
import com.akshatsonic.globetotter.dto.SessionResponseDto;
import com.akshatsonic.globetotter.exceptions.InvalidPasswordException;
import com.akshatsonic.globetotter.exceptions.UserAlreadyPresentException;
import com.akshatsonic.globetotter.exceptions.UserNotFoundException;
import com.akshatsonic.globetotter.models.Session;
import com.akshatsonic.globetotter.repository.AuthRepository;
import com.akshatsonic.globetotter.models.AuthEntity;
import com.akshatsonic.globetotter.models.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Value("${auth.decrypt.token")
    String token;

    private final UserService userService;
    private final SessionService sessionService;
    private final AuthRepository authRepository;

    public SessionResponseDto authenticate(LoginRequest loginRequest){
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        User user = userService.getUser(username);
        String encryptedPassword = encrypt(password);
        Optional<AuthEntity> authEntity = authRepository.findByUserAndPassword(user, encryptedPassword);
        if (authEntity.isEmpty()){
            throw new InvalidPasswordException("Invalid password");
        }
        Session session = sessionService.createSession(user);
        return SessionResponseDto.builder()
                .sessionToken(session.getSessionToken())
                .build();
    }

    @Transactional
    public void createUser(CreateUserRequest createUserRequest){
        try{
            userService.getUser(createUserRequest.getUsername());
            throw new UserAlreadyPresentException("User already present with username: "+createUserRequest.getUsername());
        }
        catch (UserNotFoundException e){
            User user = userService.createUser(createUserRequest);
            createAuth(createUserRequest, user);

        }
    }

    private void createAuth(CreateUserRequest createUserRequest, User user){
        AuthEntity authEntity = AuthEntity.builder()
                .user(user)
                .password(encrypt(createUserRequest.getPassword()))
                .build();
        authRepository.save(authEntity);
    }

    private String encrypt(String password){
        return Base64.getEncoder().encodeToString((password+token).getBytes());
    }

    public void logout(LogoutRequest logoutRequest){
        String sessionToken = logoutRequest.getSessionToken();
        Long userId = logoutRequest.getUserId();
        sessionService.invalidateSession(sessionToken, userId);
    }

}
