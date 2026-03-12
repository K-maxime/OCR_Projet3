package com.chatop.api.dto;

import lombok.Data;

@Data
public class MessageDTO {
    private Long rentalId;
    private Long userId;
    private String message;
}
