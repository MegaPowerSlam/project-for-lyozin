package com.example.projectforlyozin.dto;

import lombok.Value;

@Value
public class OrderProductCreateEditDto {

    Integer orderProductId;

    Integer quantity;

    Integer productId;

    Integer orderId;
}
