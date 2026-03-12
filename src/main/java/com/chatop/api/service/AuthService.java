package com.chatop.api.service;


import com.chatop.api.dto.LoginDTO;
import com.chatop.api.dto.UserDTO;
import com.chatop.api.exception.BadRequestException;
import com.chatop.api.exception.InvalidCredentialsException;
import com.chatop.api.exception.UserNotFoundException;
import com.chatop.api.model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {


    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;



    public String register(UserDTO dto){
        if (dto.getEmail() == null || dto.getName() == null || dto.getPassword() == null ||
                dto.getEmail().isBlank() || dto.getName().isBlank() || dto.getPassword().isBlank()){
            throw new BadRequestException();
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        User savedUser = userService.createUser(user);
        return jwtService.generateToken(savedUser);
    }

    public User getMyUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return userService.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    public String login(LoginDTO dto) {
        if (dto.getEmail() == null || dto.getPassword() == null ||
                dto.getEmail().isBlank() || dto.getPassword().isBlank()){
            throw new BadRequestException();
        }
        String email = dto.getEmail();
        String password = dto.getPassword();
        User connectedUser = userService.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        if (passwordEncoder.matches(password, connectedUser.getPassword())) {

            return jwtService.generateToken(connectedUser);
        }
        throw new InvalidCredentialsException();
    }
}
