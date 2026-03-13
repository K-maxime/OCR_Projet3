package com.chatop.api.controller;

import com.chatop.api.dto.UserResponseDTO;
import com.chatop.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Long id){
        return userService.getUser(id);
    }


}
