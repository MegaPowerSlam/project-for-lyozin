package com.example.projectforlyozin.dto;

import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
public class ProductReadDto {

    Integer productId;

    String productName;

    BigDecimal price;
}
