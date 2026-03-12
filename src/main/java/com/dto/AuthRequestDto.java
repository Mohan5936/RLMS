package com.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequestDto {

    @NotBlank(message = "Email is required for login")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Password is required for login")
    private String password;
}