package com.example.product_management.validator;

import com.example.product_management.exceptions.ValidateServiceException;
import com.example.product_management.model.Product;

public class ProductoValidator {
    public static void validate(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new ValidateServiceException("El nombre del producto es requerido");
        }
        if(product.getName().length() > 100){
            throw new ValidateServiceException("El nombre del producto es demasiado largo");
        }
        if (product.getStock() == null) {
            throw new ValidateServiceException("El stock del producto es requerido");
        }
        if (product.getStock() < 0) {
            throw new ValidateServiceException("El stock del debe ser mayor que 0");
        }
        if(product.getPrice() == null) {
            throw new ValidateServiceException("El precio del producto es requerido");
        }
        if(product.getPrice() < 0) {
            throw new ValidateServiceException("El precio del producto debe  ser mayor que 0");
        }
    }
}
