package com.example.product_management.service.product;

import com.example.product_management.model.Product;

import java.util.List;

//Declaración de métodos para los productos
public interface ProductService {

    public List<Product> findAll();
    public List<Product> showByName(String name);
    public Product create(Product product);
    public Product edit(Product product);
    public Product softEdit(Product product);
    public Product hardDelete(int id);

}
