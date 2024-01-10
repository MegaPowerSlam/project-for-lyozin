package com.example.projectforlyozin.dto;

import com.example.projectforlyozin.entity.Role;
import lombok.Value;

@Value
public class LoginInfo {
    Integer userId;
    String role;
    String token;
}
