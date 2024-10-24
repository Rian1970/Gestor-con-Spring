package com.example.product_management.controllers;

import com.example.product_management.domain.Product;
import com.example.product_management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//Controlador para la clase Producto
@RestController
@RequestMapping("/productos")
public class ProductController {

    @Autowired
    ProductService productService;

    //Método GET vacío obtiene todos los productos
    @GetMapping
    public ResponseEntity<?> getProducts(){

        List<Product> products = productService.getProducts();

        return ResponseEntity.ok(products);

    }

    //Método que obtiene un producto según el nombre obtenido del parámetro de la url
    @GetMapping("/{name}")
    public ResponseEntity<?> getProduct(@PathVariable String name){

        if (productService.getProduct(name) != null){
            return ResponseEntity.ok(productService.getProduct(name));
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    //Método para crear un nuevo producto según los valores en el body
    @PostMapping
    public ResponseEntity<?> postProduct(@RequestBody Product product){

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{name}").buildAndExpand(product.getName()).toUri();

        productService.postProduct(product);

        return ResponseEntity.created(location).body(product);

    }

    //Método para actualizar un producto según los valores en el body
    @PutMapping
    public ResponseEntity<?> putProduct(@RequestBody Product product){

        if(productService.putProduct(product) != null){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    //Método para actualizar parcialmente un producto según los valores en el body
    @PatchMapping
    public ResponseEntity<?> patchProduct(@RequestBody Product product){

        if(productService.patchProduct(product) != null){
            return ResponseEntity.ok("Producto modificado satisfactoriamente");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con id: " + product.getId());
        }
    }

    //Método para borrar un producto por su id obtenido del parámetro de la url
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){

        if(productService.deleteProduct(id) != null){
            return ResponseEntity.ok("Producto eliminado satisfactoriamente");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con id: " + id);
        }
    }
}
