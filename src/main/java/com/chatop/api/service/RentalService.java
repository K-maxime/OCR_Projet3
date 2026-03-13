package com.chatop.api.service;

import com.chatop.api.dto.RentalDTO;
import com.chatop.api.dto.RentalResponseDTO;
import com.chatop.api.exception.BadRequestException;
import com.chatop.api.exception.RentalNotFoundException;
import com.chatop.api.exception.UserNotFoundException;
import com.chatop.api.model.Rental;
import com.chatop.api.model.User;
import com.chatop.api.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final UserService userService;

    public List<RentalResponseDTO> getRentals() {
        return rentalRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public RentalResponseDTO getRental(Long id) {
        return rentalRepository.findById(id)
                .map(this::toResponseDTO)
                .orElseThrow(RentalNotFoundException::new);
    }

    public Optional<Rental> findRentalEntityById(Long id) {
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

    public void createRental(RentalDTO dto) {

        validateRentalDTO(dto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User owner = userService.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        Rental rental = new Rental();
        rental.setName(dto.getName());
        rental.setSurface(dto.getSurface());
        rental.setPrice(dto.getPrice());
        rental.setPicture(dto.getPicture());
        rental.setOwnerId(owner);
        rental.setDescription(dto.getDescription());
        rental.setCreatedAt(LocalDateTime.now());
        rental.setUpdatedAt(LocalDateTime.now());

        rentalRepository.save(rental);
    }

    public void updateRental(Long id, RentalDTO dto) {

        validateRentalDTO(dto);

        rentalRepository.findById(id)
                .map(existingRental -> {
                            existingRental.setName(dto.getName());
                            existingRental.setSurface(dto.getSurface());
                            existingRental.setPrice(dto.getPrice());
                            existingRental.setDescription(dto.getDescription());
                            return rentalRepository.save(existingRental);
                        }
                ).orElseThrow(RentalNotFoundException::new);
    }

    private RentalResponseDTO toResponseDTO(Rental rental) {

        RentalResponseDTO rentalResponseDTO = new RentalResponseDTO();
        rentalResponseDTO.setId(rental.getId());
        rentalResponseDTO.setName(rental.getName());
        rentalResponseDTO.setSurface(rental.getSurface());
        rentalResponseDTO.setPrice(rental.getPrice());
        rentalResponseDTO.setPicture(rental.getPicture());
        rentalResponseDTO.setDescription(rental.getDescription());
        rentalResponseDTO.setOwnerId(rental.getOwnerId().getId());
        rentalResponseDTO.setCreatedAt(rental.getCreatedAt());
        rentalResponseDTO.setUpdatedAt(rental.getUpdatedAt());

        return rentalResponseDTO;
    }

}
