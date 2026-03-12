package com.chatop.api.service;

import com.chatop.api.model.Message;
import com.chatop.api.repository.MessageRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public Message createMessage( Message message){
        return messageRepository.save(message);
    }
}
