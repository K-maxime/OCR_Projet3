package com.chatop.api.service;

import com.chatop.api.dto.RentalDTO;
import com.chatop.api.exception.BadRequestException;
import com.chatop.api.exception.RentalNotFoundException;
import com.chatop.api.model.Rental;
import com.chatop.api.repository.RentalRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    private void validateRentalDTO(RentalDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank() ||
                dto.getSurface() == null ||
                dto.getPrice() == null ||
                dto.getPicture() == null || dto.getPicture().isBlank() ||
                dto.getDescription() == null || dto.getDescription().isBlank()) {
            throw new BadRequestException();
        }
    }

    public Rental createRental(RentalDTO dto){

        validateRentalDTO(dto);

        Rental rental = new Rental();
        rental.setName(dto.getName());
        rental.setSurface(dto.getSurface());
        rental.setPrice(dto.getPrice());
        rental.setPicture(dto.getPicture());
        rental.setDescription(dto.getDescription());
        rental.setCreatedAt(LocalDateTime.now());
        rental.setUpdatedAt(LocalDateTime.now());

        return rentalRepository.save(rental);
    }

    public Rental updateRental(Long id, RentalDTO dto){

        validateRentalDTO(dto);

        return rentalRepository.findById(id)
                .map(existingRental -> {
                    existingRental.setName(dto.getName());
                    existingRental.setSurface(dto.getSurface());
                    existingRental.setPrice(dto.getPrice());
                    existingRental.setDescription(dto.getDescription());
                    return rentalRepository.save(existingRental);
                }
                ).orElseThrow(RentalNotFoundException::new);
    }

}
