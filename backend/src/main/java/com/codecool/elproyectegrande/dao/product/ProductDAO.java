package com.codecool.elproyectegrande.dao.product;

import com.codecool.elproyectegrande.dao.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product, Long> {
    Product findProductById(Long id);
}
