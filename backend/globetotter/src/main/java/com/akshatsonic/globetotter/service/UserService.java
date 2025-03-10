package com.akshatsonic.globetotter.service;

import com.akshatsonic.globetotter.dto.CreateUserRequest;
import com.akshatsonic.globetotter.exceptions.UserAlreadyPresentException;
import com.akshatsonic.globetotter.exceptions.UserNotFoundException;
import com.akshatsonic.globetotter.models.User;
import com.akshatsonic.globetotter.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found with username: "+username);
        }
        return user.get();
    }

    public User createUser(CreateUserRequest createUserRequest){
        try{
            getUser(createUserRequest.getUsername());
            throw new UserAlreadyPresentException("User already present with username: "+createUserRequest.getUsername());
        }
        catch (UserNotFoundException e){
            User user = new User();
            user.setUsername(createUserRequest.getUsername());
            userRepository.save(user);
            return user;
        }
    }
}
