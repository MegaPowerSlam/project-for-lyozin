package com.example.projectforlyozin.controller;


import com.example.projectforlyozin.dto.CustomerCreateEditDto;
import com.example.projectforlyozin.dto.CustomerReadDto;
import com.example.projectforlyozin.dto.LoginDto;
import com.example.projectforlyozin.dto.LoginInfo;
import com.example.projectforlyozin.service.AuthService;
import com.example.projectforlyozin.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final CustomerService customerService;
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody CustomerCreateEditDto dto) {
        System.out.println(dto.toString());
        CustomerReadDto customerReadDto = customerService.create(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        LoginInfo loginInfo = authService.authenticate(dto);
        return ResponseEntity
                .ok()
                .body(loginInfo);
    }
}
