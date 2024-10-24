package com.example.product_management.service;

import com.example.product_management.domain.Product;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

//Declaración de métodos para los productos
public interface ProductService {

    public List<Product> getProducts();
    public Product getProduct(String name);
    public Product postProduct(Product product);
    public Product putProduct(Product product);
    public Product patchProduct(Product product);
    public Product deleteProduct(int id);

}
