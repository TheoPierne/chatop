package com.openclassrooms.chatop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.repository.RentalRepository;

import lombok.Data;

@Data
@Service
public class RentalService {
    
    @Autowired
    private RentalRepository rentalRepository;

    public Optional<Rental> getRental(final Integer id) {
        return rentalRepository.findById(id);
    }

    public Iterable<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    public void deleteRental(final Integer id) {
        rentalRepository.deleteById(id);
    }

    public Rental saveRental(Rental rental) {
        return rentalRepository.save(rental);
    }
}
