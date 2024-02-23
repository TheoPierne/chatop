package com.openclassrooms.chatop.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "messages")
public class Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 2000)
    private String message;

    @Column(updatable = false, insertable = false)
    private Timestamp created_at;

    @Column(insertable = false)
    private Timestamp updated_at;

    public Message(Rental rental, User user, String message) {
        this.rental = rental;
        this.user = user;
        this.message = message;
    }
}
