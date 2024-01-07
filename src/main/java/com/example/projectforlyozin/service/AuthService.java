package com.example.projectforlyozin.service;

import com.example.projectforlyozin.config.JwtService;
import com.example.projectforlyozin.dto.LoginDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public String authenticate(LoginDto dto) {
        System.out.println("Try entry");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );
        System.out.println("half entry");

        UserDetails user = userDetailsService.loadUserByUsername(dto.getUsername());
        String jwtToken = jwtService.generateToken(user);
        System.out.println("Entry");
        return jwtToken;
    }
}
