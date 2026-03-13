package com.chatop.api.controller;

import com.chatop.api.dto.RentalDTO;
import com.chatop.api.dto.RentalResponseDTO;
import com.chatop.api.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    @GetMapping
    public List<RentalResponseDTO> getRentals(){
        return rentalService.getRentals();
    }

    @GetMapping("/{id}")
    public RentalResponseDTO getRental(@PathVariable long id){
        return rentalService.getRental(id);
    }

    @PostMapping
    public Map<String, String>  createRental(@RequestBody RentalDTO dto){
        rentalService.createRental(dto);
        return Map.of("message", "Rental created !");
    }

    @PutMapping("/{id}")
    public  Map<String, String> updateRental(@PathVariable long id, @RequestBody RentalDTO dto){
        rentalService.updateRental(id, dto);
        return Map.of("message", "Rental updated !");
    }
}
