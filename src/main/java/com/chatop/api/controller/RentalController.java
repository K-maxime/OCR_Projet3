package com.chatop.api.controller;

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
    //TODO faire un DTO pour adapter les données utilisateur en données pour l'entité
    public Rental createRental(@RequestBody Rental rental){
        return rentalService.createRental(rental);
    }

    @PutMapping("/{id}")
    //TODO faire un DTO pour adapter les données utilisateur en données pour l'entité
    public Rental updateRental(@PathVariable long id, @RequestBody Rental rental){
        return rentalService.updateRental(id, rental);
    }
}
