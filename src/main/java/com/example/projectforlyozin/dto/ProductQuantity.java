package com.example.projectforlyozin.dto;

import lombok.Value;

@Value
public class ProductQuantity {

    ProductReadDto product;
    Integer quantity;
}
