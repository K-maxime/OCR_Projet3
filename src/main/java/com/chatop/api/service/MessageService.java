package com.chatop.api.service;

import com.chatop.api.dto.MessageDTO;
import com.chatop.api.model.Message;
import com.chatop.api.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final RentalService rentalService;
    private final UserService userService;

    public Message createMessage( MessageDTO dto){
        Message message = new Message();
        message.setRental(rentalService.getRental(dto.getRentalId())
                .orElseThrow(() -> new RuntimeException("Rental not found")));
        message.setUser(userService.getUser(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        message.setMessage(dto.getMessage());
        message.setCreatedAt(LocalDateTime.now());
        message.setUpdatedAt(LocalDateTime.now());

        return messageRepository.save(message);
    }
}
