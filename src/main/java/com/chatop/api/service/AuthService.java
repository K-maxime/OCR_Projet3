package com.chatop.api.service;


import com.chatop.api.model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {


    private final UserService userService;

    public User register(User user){
        return userService.createUser(user);
    }

    public Optional<User> getMyUser(){
        long myUserId = 1; // TODO recuperer le bon ID
        return userService.getUser(myUserId);
    }

    public User login(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        User connectedUser = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (Objects.equals(password, connectedUser.getPassword())) {
            return connectedUser;
        }
        throw new RuntimeException("Invalid password");
    }
}
