package com.example.projectforlyozin.dto;

import lombok.Value;
import java.util.List;

@Value
public class CustomerReadDto {

    Integer customerId;

    String firstName;

    String lastName;

    String phoneNumber;

    String city;

    String street;

    Integer house;

    Integer apartment;

    List<OrderReadDto> orders;
}
