package com.chatop.api.dto;

import com.chatop.api.model.Rental;
import lombok.Data;

@Data
public class RentalDTO {
    private String name;
    private Double surface;
    private Double price;
    private String picture;
    private String description;

}
