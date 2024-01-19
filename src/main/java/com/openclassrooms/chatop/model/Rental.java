package com.openclassrooms.chatop.model;

import java.math.BigDecimal;
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
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String name;

    private BigDecimal surface;

    private BigDecimal price;

    @Column(length = 255)
    private String picture;

    @Column(length = 2000)
    private String description;

    // @Column(nullable = false)
    // private Integer owner_id;

    private Timestamp created_at;

    private Timestamp updated_at;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
    
}
