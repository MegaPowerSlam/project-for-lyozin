package com.example.projectforlyozin.dto;

import com.example.projectforlyozin.entity.Role;
import lombok.Value;

import java.util.List;

@Value
public class CustomerCreateEditDto {

    Integer customerId;

    String firstName;

    String lastName;

    String phoneNumber;

    String city;

    String street;

    Integer house;

    Integer apartment;

    String username;

    String password;

    String role;

    List<Integer> orderIds;
}
