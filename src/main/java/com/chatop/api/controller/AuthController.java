package com.chatop.api.controller;

import com.chatop.api.dto.LoginDTO;
import com.chatop.api.dto.TokenDTO;
import com.chatop.api.dto.UserDTO;
import com.chatop.api.dto.UserResponseDTO;
import com.chatop.api.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Authentification")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Créer un compte utilisateur")
    @ApiResponse(responseCode = "200", description = "Token JWT retourné")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @PostMapping("/register")
    public TokenDTO  register(@RequestBody UserDTO dto){

        return new TokenDTO(authService.register(dto));
    }

    @Operation(summary = "Recupere le compte utilisateur actif")
    @ApiResponse(responseCode = "200", description = "Données utilisateur retourné")
    @ApiResponse(responseCode = "401", description = "Non authentifié")
    @GetMapping("/me")
    public UserResponseDTO getMyUser(){

        return authService.getMyUser();
    }

    @Operation(summary = "Connection à un compte utilisateur")
    @ApiResponse(responseCode = "200", description = "Token JWT retourné")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @PostMapping("/login")
    public TokenDTO login (@RequestBody LoginDTO dto){
        return new TokenDTO(authService.login(dto));
    }
}
