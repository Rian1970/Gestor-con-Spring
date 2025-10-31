package com.example.product_management.controllers;

import com.example.product_management.utils.BaseController;
import com.example.product_management.repository.ProductRepo;
import com.example.product_management.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("productos_repo")
public class ProductRepoHibernate {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping
    public ResponseEntity<BaseController<List<Product>>> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return BaseController.success("Productos encontrados", products);
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<BaseController<Product>> getProductByName(@PathVariable String nombre){
        Product product = productRepo.findByNombre(nombre);

        if(product == null){
            return BaseController.fail("No se encontró el producto", null);
        }

        return BaseController.success("Producto encontrado", product);
    }
}
