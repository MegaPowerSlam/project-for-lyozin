package com.example.projectforlyozin.mapper;

import com.example.projectforlyozin.dto.ProductReadDto;
import com.example.projectforlyozin.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductReadMapper implements Mapper<Product, ProductReadDto> {
    @Override
    public ProductReadDto map(Product object) {
        return new ProductReadDto(
                object.getProductId(),
                object.getProductName(),
                object.getPrice(),
                object.getDescription(),
                object.getImageUrl());
    }
}
