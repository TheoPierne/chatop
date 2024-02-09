package com.openclassrooms.chatop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.service.RentalService;
import com.openclassrooms.chatop.util.ResponseHandler;


@RestController
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping("/rentals")
    public Iterable<Rental> getRentals() {
        return rentalService.getRentals();
    }

    @GetMapping("/rentals/{id}")
    public Optional<Rental> getRental(@PathVariable("id") final Integer id) {
        return rentalService.getRental(id);
    }

    @PostMapping("/rentals")
    public ResponseEntity<Object> createRental(@RequestBody Rental rental) {
        Rental savedResponse = rentalService.saveRental(rental);

        if (savedResponse != null) {
            return ResponseHandler.generateResponse("Rental created !", HttpStatus.OK, null);
        } else {
            return ResponseHandler.generateResponse("An error occurred while saving the rental.", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/rentals/{id}")
    public ResponseEntity<Object> updaRental(@PathVariable("id") final Integer id, @RequestBody Rental rental) {
        Rental savedResponse = rentalService.updateRental(id, rental);
        
        if (savedResponse != null) {
            return ResponseHandler.generateResponse("Rental updated !", HttpStatus.OK, null);
        } else {
            return ResponseHandler.generateResponse("An error occurred while updating the rental.", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    
}
