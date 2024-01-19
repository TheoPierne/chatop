package com.openclassrooms.chatop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.service.RentalService;


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
    public Rental createRental(@RequestBody Rental rental) {
        return rentalService.saveRental(rental);
    }

    @PutMapping("/rentals")
    public Rental updaRental(Rental rental) {
        return rentalService.saveRental(rental);
    }
    
}
