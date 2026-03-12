package com.chatop.api.controller;

import com.chatop.api.dto.RentalDTO;
import com.chatop.api.model.Rental;
import com.chatop.api.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    @GetMapping
    public List<Rental> getRentals(){
        return rentalService.getRentals();
    }

    @GetMapping("/{id}")
    public Optional<Rental> getRental(@PathVariable long id){
        return rentalService.getRental(id);
    }

    @PostMapping
    public Rental createRental(@RequestBody RentalDTO dto){
        return rentalService.createRental(dto);
    }

    @PutMapping("/{id}")
    public Rental updateRental(@PathVariable long id, @RequestBody RentalDTO dto){
        return rentalService.updateRental(id, dto);
    }
}
