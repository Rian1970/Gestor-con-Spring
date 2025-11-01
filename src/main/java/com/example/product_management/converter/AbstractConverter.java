package com.example.product_management.converter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<E,D> {
    public abstract E fromDto(D dto);
    public abstract D fromEntity(E entity);

    public List<D> fromEntity(List<E> entities) {
        return entities.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public List<E> fromDto(List<D> dtos){
        return dtos.stream()
                .map(this::fromDto)
                .collect(Collectors.toList());
    }
}
