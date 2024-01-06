package com.example.projectforlyozin.dto;

import lombok.Value;
import java.time.LocalDate;

@Value
public class DeliveryListReadDto {

    Integer deliveryId;

    LocalDate dateArrived;

    String paymentMethod;

    OrderReadDto order;
}
