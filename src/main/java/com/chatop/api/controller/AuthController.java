package com.chatop.api.controller;

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
    //TODO faire un DTO pour adapter les données utilisateur en données pour l'entité
    public User register(@RequestBody User user){
        return authService.register(user);
    }
    @GetMapping("/me")
    public Optional<User> getMyUser(){
        return authService.getMyUser();
    }
    @PostMapping("/login")
    //TODO faire un DTO pour adapter les données utilisateur en données pour l'entité
    public User login (@RequestBody User user){
        return authService.login(user);
        // TODO on remplacera User par un token JWT plus tard
    }
}
