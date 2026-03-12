package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Security.JwtService; // Assuming this is your custom JWT utility class
import com.dto.AuthRequestDto;

import jakarta.validation.Valid; // FIXED: Imported Valid

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    // Constructor Injection
    public AuthController(AuthenticationManager authenticationManager, 
                          UserDetailsService userDetailsService, 
                          JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    // FIXED: Added @Valid to ensure the AuthRequestDto isn't empty
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody AuthRequestDto request) {
        
        // 1. Check the password against the database
        // If it fails, Spring throws BadCredentialsException automatically
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // 2. Load the user details
        UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());

        // 3. Generate the JWT token
        String token = jwtService.generateToken(user);

        // 4. Send the token back to the user in a JSON response
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        
        return ResponseEntity.ok(response);
    }
}