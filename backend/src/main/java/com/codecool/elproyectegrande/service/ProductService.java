package com.codecool.elproyectegrande.service;

import com.codecool.elproyectegrande.controller.dto.NewProductDTO;
import com.codecool.elproyectegrande.dao.model.Product;
import com.codecool.elproyectegrande.dao.product.ProductDAO;
import com.codecool.elproyectegrande.dao.user.ClientDAO;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private ProductDAO productDAO;
    private final ClientDAO clientDAO;

    @Autowired
    public ProductService(ProductDAO productDAO,
                          ClientDAO clientDAO) {
        this.productDAO = productDAO;
        this.clientDAO = clientDAO;
    }
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }
    public Product getProductById(Long id) {
        return productDAO.findProductById(id);
    }
    public void addNewProduct(NewProductDTO product) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"q\":\""+product.name()+"\"}");
        Request request = new Request.Builder()
                .url("https://google.serper.dev/search")
                .method("POST", body)
                .addHeader("X-API-KEY", "d3dcc6ef3f2d2bbe3c5b8ad78d0995f62f74e224")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        String responseData = response.body().string();

        String imgURL = responseData.substring(responseData.indexOf("imageUrl"),responseData.length());
        String imageUrl = imgURL.substring(11,imgURL.indexOf("\","));

        Product newProduct = Product.builder()
                .name(product.name())
                .description(product.description())
                .price(product.price())
                .image(imageUrl)
                //.seller(product.seller())
                //.buyer(product.buyer())
                .build();
        productDAO.save(newProduct);
    }

    public void updateProductById(Long id, Product updateProduct){
        Product currentProduct = getProductById(id);
        if (updateProduct.getName() != null) currentProduct.setName(updateProduct.getName());
        if (updateProduct.getDescription() != null) currentProduct.setDescription(updateProduct.getDescription());
        if (updateProduct.getPrice() != 0) currentProduct.setPrice(updateProduct.getPrice());
        /*if (updateProduct.getSeller() != null) currentProduct.setSeller(updateProduct.getSeller());
        if (updateProduct.getBuyer() != null) currentProduct.setBuyer(updateProduct.getBuyer());*/
    }

    public void deleteProductById(Long id){
        productDAO.deleteById(id);
    }

    /*public List<Product> getAllAvailableProducts(){
        return getAllProducts().stream().filter(product -> product.isAvailable()).toList();
    }*/
}