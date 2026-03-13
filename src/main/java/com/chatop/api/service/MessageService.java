package com.chatop.api.service;

import com.chatop.api.dto.MessageDTO;
import com.chatop.api.exception.BadRequestException;
import com.chatop.api.exception.RentalNotFoundException;
import com.chatop.api.exception.UserNotFoundException;
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

    public void createMessage( MessageDTO dto){
        if (dto.getRentalId() == null || dto.getUserId() == null || dto.getMessage() == null || dto.getMessage().isBlank()){
            throw new BadRequestException();
        }
        Message message = new Message();
        message.setRental(rentalService.findRentalEntityById(dto.getRentalId())
                .orElseThrow(RentalNotFoundException::new));
        message.setUser(userService.findUserEntityById(dto.getUserId())
                .orElseThrow(UserNotFoundException::new));
        message.setMessage(dto.getMessage());
        message.setCreatedAt(LocalDateTime.now());
        message.setUpdatedAt(LocalDateTime.now());

        messageRepository.save(message);
    }
}
