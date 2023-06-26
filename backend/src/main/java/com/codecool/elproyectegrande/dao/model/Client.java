package com.codecool.elproyectegrande.dao.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientName;
    private String password;
    /*@OneToMany( mappedBy = "seller")
    @JsonManagedReference(value="seller-product")
    private List<Product> productsToSell;

    @OneToMany( mappedBy = "buyer")
    @JsonManagedReference(value="buyer-product")
    private List<Product> productsBought;*/
}
