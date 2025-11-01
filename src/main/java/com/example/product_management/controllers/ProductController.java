package com.example.product_management.controllers;

import com.example.product_management.converter.ProductConverter;
import com.example.product_management.model.Product;
import com.example.product_management.service.product.ProductService;
import com.example.product_management.utils.BaseResponse;
import com.example.product_management.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controlador para la clase Producto
@RestController
@RequestMapping("/productos")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductConverter productConverter;

    @GetMapping
    public ResponseEntity<BaseResponse<List<ProductDTO>>> getAllProducts() {
        List<Product> products = productService.findAll();

        List<ProductDTO> productsDTO = productConverter.fromEntity(products);

        return BaseResponse.success("Productos encontrados", productsDTO);
    }

    @GetMapping("/{name}")
    public ResponseEntity<BaseResponse<List<ProductDTO>>> getProductByName(@PathVariable String name){
        List<Product> products = productService.showByName(name);

        List<ProductDTO> productsDTO = productConverter.fromEntity(products);

        return BaseResponse.success("Producto encontrado", productsDTO);
    }

    //Método para crear un nuevo producto según los valores en el body
    @PostMapping
    public ResponseEntity<BaseResponse<ProductDTO>> postProduct(@RequestBody Product product){

        productService.create(product);

        ProductDTO productDTO = productConverter.fromEntity(product);

        return BaseResponse.success("Producto creado existosamente", productDTO);

    }

    //Método para actualizar un producto según los valores en el body
    @PutMapping
    public ResponseEntity<BaseResponse<ProductDTO>> putProduct(@RequestBody Product product){

        Product productUpdate = productService.edit(product);

        ProductDTO productDTO = productConverter.fromEntity(productUpdate);

        return BaseResponse.success("Producto editado existosamente", productDTO);
    }

    //Método para actualizar parcialmente un producto según los valores en el body
    @PatchMapping
    public ResponseEntity<BaseResponse<ProductDTO>> patchProduct(@RequestBody Product product){
        Product productUpdate = productService.softEdit(product);

        ProductDTO productDTO = productConverter.fromEntity(productUpdate);

        return BaseResponse.success("Producto editado existosamente", productDTO);
    }

    //Método para borrar un producto por su id obtenido del parámetro de la url
    @DeleteMapping("{id}")
    public ResponseEntity<BaseResponse<ProductDTO>> deleteProduct(@PathVariable int id){

        productService.hardDelete(id);

        return BaseResponse.success("Producto eliminado satisfactoriamente", null);

    }
}
