package com.chatop.api.controller;

import com.chatop.api.dto.RentalDTO;
import com.chatop.api.dto.RentalResponseDTO;
import com.chatop.api.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Rental", description = "Location")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    @Operation(summary = "Récuperation de la liste des locations")
    @ApiResponse(responseCode = "200", description = "Liste des locations retournée")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "401", description = "Non authentifié")
    @GetMapping
    public List<RentalResponseDTO> getRentals(){
        return rentalService.getRentals();
    }

    @Operation(summary = "Récuperation des données d'une location")
    @ApiResponse(responseCode = "200", description = "Données de la location retournée")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "401", description = "Non authentifié")
    @GetMapping("/{id}")
    public RentalResponseDTO getRental(@PathVariable long id){
        return rentalService.getRental(id);
    }

    @Operation(summary = "Création d'une location")
    @ApiResponse(responseCode = "200", description = "Rental created !")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "401", description = "Non authentifié")
    @PostMapping
    public Map<String, String>  createRental(@RequestBody RentalDTO dto){
        rentalService.createRental(dto);
        return Map.of("message", "Rental created !");
    }

    @Operation(summary = "Mise à jour des données d'une location")
    @ApiResponse(responseCode = "200", description = "Rental updated !")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "401", description = "Non authentifié")
    @PutMapping("/{id}")
    public  Map<String, String> updateRental(@PathVariable long id, @RequestBody RentalDTO dto){
        rentalService.updateRental(id, dto);
        return Map.of("message", "Rental updated !");
    }
}
