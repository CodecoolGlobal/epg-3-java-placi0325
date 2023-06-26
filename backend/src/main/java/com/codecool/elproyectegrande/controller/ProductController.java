package com.codecool.elproyectegrande.controller;


import com.codecool.elproyectegrande.controller.dto.NewProductDTO;
import com.codecool.elproyectegrande.dao.model.Product;
import com.codecool.elproyectegrande.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> getAllProducts(){
       return productService.getAllProducts();
    }

     /*@GetMapping("/available")
    public List<Product> getAvailableProducts(){
       return productService.getAllAvailableProducts();
    }*/

    @GetMapping("/{id}")
    public Product getProductByID(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @PostMapping
    public ResponseEntity addNewProduct(@RequestBody NewProductDTO productDTO) throws IOException {
        productService.addNewProduct(productDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public void updateProductById(@PathVariable("id") Long id, @RequestBody Product updatedProduct) {
        productService.updateProductById(id, updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long id){
        productService.deleteProductById(id);
    }

}
