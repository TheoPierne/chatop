package com.openclassrooms.chatop.model;

import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String email;

    @Column(length = 255)
    private String name;

    @Column(length = 255)
    private String password;

    private Timestamp created_at;

    private Timestamp updated_at;

    @OneToMany(mappedBy = "owner")
    private Set<Rental> rentals;

    @OneToMany(mappedBy = "user")
    private Set<Message> messages;
    
}
