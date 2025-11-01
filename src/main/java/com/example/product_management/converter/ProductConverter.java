package com.example.product_management.converter;

import com.example.product_management.dto.ProductDTO;
import com.example.product_management.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter extends AbstractConverter<Product, ProductDTO> {

    @Override
    public Product fromDto(ProductDTO dto) {
        if (dto == null) return null;
        return  Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .build();
    }

    @Override
    public ProductDTO fromEntity(Product entity) {
        if (entity == null) return null;
        return ProductDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .build();

    }
}
