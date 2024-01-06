package com.example.projectforlyozin.dto;

import lombok.Value;

import java.time.LocalDate;
@Value
public class DeliveryListCreateEditDto {

        Integer deliveryId;

        LocalDate dateArrived;

        String paymentMethod;

        Integer orderId;
}
