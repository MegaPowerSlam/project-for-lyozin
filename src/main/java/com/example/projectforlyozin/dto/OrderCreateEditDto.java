package com.example.projectforlyozin.dto;

import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class OrderCreateEditDto {

    Integer orderId;

    LocalDate dateGet;

    List<Integer> productsIds;
}
