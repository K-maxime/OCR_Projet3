package com.chatop.api.controller;

import com.chatop.api.dto.MessageDTO;
import com.chatop.api.service.MessageService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public Map<String, String> createMessage(@RequestBody MessageDTO dto){
        messageService.createMessage(dto);
        return Map.of("message", "Message send with success");
    }
}
