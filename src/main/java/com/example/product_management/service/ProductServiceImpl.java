package com.example.product_management.service;

import com.example.product_management.domain.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

//Clase de servicios con la lógica de negocio
@Service
public class ProductServiceImpl implements ProductService{

    //Método para leer el JSON y obtener una lista de los objetos almacenados
    private List<Product> readJSON(){
        //Lista vacía de productos
        List<Product> products;

        try {
            File file = new File("products.json");

            // Verificar si el archivo existe
            if (!file.exists()) {
                // Si el archivo no existe, inicializa la lista vacía
                return List.of();

            }

            // Leer el archivo JSON y almacenarlo en una lista de objetos Producto
            products = new ObjectMapper().readValue(file, new TypeReference<List<Product>>() {});

            return products;

        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo JSON", e);
        }
    }

    //Método para actualizar el archivo JSON, tiene como parámetro la lista actualizada
    private void updateJSON(List<Product> products){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            objectMapper.writeValue(new File("products.json"), products);
        } catch (Exception e){
            throw new RuntimeException(e);

        }
    }

    //Método que obtiene todos los productos del archivo JSON
    @Override
    public List<Product> getProducts() {

        return readJSON();
    }

    //Método que devuelve un producto dependiendo su nombre
    @Override
    public Product getProduct(String name) {

        List<Product> products = readJSON();

        for (Product p: products){
            if(p.getName().equalsIgnoreCase(name)){
                return p;
            }
        }
        return null;
    }

    //Método para almacenar un nuevo producto
    @Override
    public Product postProduct(Product product) {

        List<Product> products = readJSON();

        products.add(product);

        updateJSON(products);

        return product;

    }

    //Método para actualizar un producto
    @Override
    public Product putProduct(Product product) {

        List<Product> products = readJSON();

        for (Product p: products){
            if(p.getId() == product.getId()){
                p.setName(product.getName());
                p.setPrice(product.getPrice());
                p.setStock(product.getStock());

                updateJSON(products);

                return p;
            }
        }
        return null;

    }

    //Método para actualizar parcialmente un producto
    @Override
    public Product patchProduct(Product product) {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> products = readJSON();

        for (Product p: products){
            if(p.getId() == product.getId()){
                if(product.getName() != null){
                   p.setName(product.getName());
                }
                if(product.getPrice() != null){
                    p.setPrice(product.getPrice());
                }
                if(product.getStock() != null){
                    p.setStock(product.getStock());
                }

                updateJSON(products);

                return p;
            }
        }

        return null;
    }

    //Método que elimina un producto
    @Override
    public Product deleteProduct(int id) {

        List<Product> products = readJSON();

        for(Product p: products){
            if(p.getId() == id){
                products.remove(p);

                updateJSON(products);

                return p;
            }
        }

        return null;
    }
}
