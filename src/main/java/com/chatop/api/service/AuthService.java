package com.chatop.api.service;


import com.chatop.api.dto.LoginDTO;
import com.chatop.api.dto.UserDTO;
import com.chatop.api.model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {


    private final UserService userService;

    public User register(UserDTO dto){
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userService.createUser(user);
    }

    public Optional<User> getMyUser(){
        long myUserId = 1; // TODO recuperer le bon ID
        return userService.getUser(myUserId);
    }

    public User login(LoginDTO dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();
        User connectedUser = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (Objects.equals(password, connectedUser.getPassword())) {
            return connectedUser;
        }
        throw new RuntimeException("Invalid password");
    }
}
