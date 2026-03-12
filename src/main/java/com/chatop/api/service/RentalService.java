package com.chatop.api.service;

import com.chatop.api.model.Rental;
import com.chatop.api.repository.RentalRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    public List<Rental> getRentals(){
        return rentalRepository.findAll();
    }

    public Optional<Rental> getRental(Long id){
        return rentalRepository.findById(id);
    }

    public Rental createRental(Rental rental){
        return rentalRepository.save(rental);
    }

    public Rental updateRental(Long id, Rental rental){
        return rentalRepository.findById(id)
                .map(existingRental -> {
                    existingRental.setName(rental.getName());
                    existingRental.setSurface(rental.getSurface());
                    existingRental.setPrice(rental.getPrice());
                    existingRental.setDescription(rental.getDescription());
                    return rentalRepository.save(existingRental);
                }
                ).orElseThrow(() -> new RuntimeException("Rental not found"));
    }

}
