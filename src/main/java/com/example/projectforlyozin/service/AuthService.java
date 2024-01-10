package com.example.projectforlyozin.service;

import com.example.projectforlyozin.config.JwtService;
import com.example.projectforlyozin.dto.LoginDto;
import com.example.projectforlyozin.dto.LoginInfo;
import com.example.projectforlyozin.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public LoginInfo authenticate(LoginDto dto) {
        System.out.println("Try entry");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );
        System.out.println("half entry");

        UserDetails user = userDetailsService.loadUserByUsername(dto.getUsername());
        Customer customer = (Customer) user;
        String jwtToken = jwtService.generateToken(user);
        LoginInfo loginInfo = new LoginInfo(
                customer.getCustomerId(),
                customer.getRole().name(),
                jwtToken
        );
        System.out.println("Entry");
        return loginInfo;
    }
}
