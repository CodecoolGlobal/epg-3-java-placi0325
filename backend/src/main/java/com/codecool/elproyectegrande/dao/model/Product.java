package com.codecool.elproyectegrande.dao.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private String image;
  
    /*@ManyToOne
    @JsonBackReference(value="seller-product")
    private Client seller;
    @ManyToOne
    @JsonBackReference(value="buyer-product")
    private Client buyer;
    public boolean isAvailable(){return buyer == null;}*/
}
