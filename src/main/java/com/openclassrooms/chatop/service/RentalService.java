package com.openclassrooms.chatop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.repository.RentalRepository;

import jakarta.persistence.EntityManager;
import lombok.Data;

@Data
@Service
public class RentalService {
    
    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private EntityManager entityManager;

    public Optional<Rental> getRental(final Integer id) {
        return rentalRepository.findById(id);
    }

    public Iterable<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    public void deleteRental(final Integer id) {
        rentalRepository.deleteById(id);
    }

    @Transactional
    public Rental saveRental(Rental rental) {
        rental = rentalRepository.save(rental);

        // On refresh l'objet rental avant de le retourner, sinon "created_at" et "updated_at" auront pour valeur null
        // puisque ces deux valeurs sont créées par MySQL
        entityManager.refresh(rental);
        return rental;
    }
}
