package com.openclassrooms.chatop.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "messages")
public class Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer rental_id;

    private Integer user_id;

    @Column(length = 2000)
    private String message;

    @Column(updatable = false, insertable = false)
    private Timestamp created_at;

    @Column(insertable = false)
    private Timestamp updated_at;

}
