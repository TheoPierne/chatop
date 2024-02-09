package com.openclassrooms.chatop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.repository.RentalRepository;

import jakarta.persistence.EntityManager;

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

    public Rental updateRental(final Integer id, Rental rentalUpdate) {
        Rental rental = rentalRepository.findById(id).orElseThrow();

        if (rentalUpdate.getName() != null) {
            rental.setName(rentalUpdate.getName());
        }
        if (rentalUpdate.getSurface() != null) {
            rental.setSurface(rentalUpdate.getSurface());
        }
        if (rentalUpdate.getPrice() != null) {
            rental.setPrice(rentalUpdate.getPrice());
        }
        if (rentalUpdate.getPicture() != null) {
            rental.setPicture(rentalUpdate.getPicture());
        }
        if (rentalUpdate.getDescription() != null) {
            rental.setDescription(rentalUpdate.getDescription());
        }
        if (rentalUpdate.getOwner_id() != null) {
            rental.setOwner_id(rentalUpdate.getOwner_id());
        }

        final Rental updatedRental = rentalRepository.save(rental);
        return updatedRental;
    }
}
