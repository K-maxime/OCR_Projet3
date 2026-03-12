package com.chatop.api.controller;

import com.chatop.api.dto.LoginDTO;
import com.chatop.api.dto.TokenDTO;
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
    public TokenDTO  register(@RequestBody UserDTO dto){

        return new TokenDTO(authService.register(dto));
    }

    @GetMapping("/me")
    public User getMyUser(){

        return authService.getMyUser();
    }

    @PostMapping("/login")
    public TokenDTO login (@RequestBody LoginDTO dto){
        return new TokenDTO(authService.login(dto));
    }
}
