package com.example.projectforlyozin.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ProductReadDto {

    Integer productId;

    String productName;

    BigDecimal price;

    String description;
}
