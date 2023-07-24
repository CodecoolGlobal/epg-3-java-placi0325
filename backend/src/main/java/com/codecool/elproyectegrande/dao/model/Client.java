package com.codecool.elproyectegrande.dao.model;

import com.codecool.elproyectegrande.security.Role;
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
   // @Enumerated(EnumType.STRING)
    private String role;

    /*@OneToMany( mappedBy = "seller")
    @JsonManagedReference(value="seller-product")
    private List<Product> productsToSell;

    @OneToMany( mappedBy = "buyer")
    @JsonManagedReference(value="buyer-product")
    private List<Product> productsBought;*/
}
