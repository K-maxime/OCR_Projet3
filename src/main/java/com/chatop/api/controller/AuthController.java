package com.chatop.api.controller;

import com.chatop.api.dto.LoginDTO;
import com.chatop.api.dto.UserDTO;
import com.chatop.api.model.User;
import com.chatop.api.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody UserDTO dto){
        return authService.register(dto);
    }
    @GetMapping("/me")
    public Optional<User> getMyUser(){
        return authService.getMyUser();
    }
    @PostMapping("/login")
    public User login (@RequestBody LoginDTO dto){
        return authService.login(dto);
        // TODO on remplacera User par un token JWT plus tard
    }
}
