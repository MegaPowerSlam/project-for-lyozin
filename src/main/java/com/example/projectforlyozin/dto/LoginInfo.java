package com.example.projectforlyozin.dto;

import com.example.projectforlyozin.entity.Role;
import lombok.Value;

@Value
public class LoginInfo {
    Integer userId;
    Integer currentOrderId;
    String address;
    String role;
    String token;
}
