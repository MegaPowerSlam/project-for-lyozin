package com.example.projectforlyozin.dto;

import lombok.Value;
import java.math.BigDecimal;


@Value
public class ProductCreateEditDto {

    Integer productId;

    String productName;

    BigDecimal price;
}
