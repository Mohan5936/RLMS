package com.Exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; // ADDED: Safety net for JSON parsing

@Data
@AllArgsConstructor
@NoArgsConstructor // ADDED: Ensures Spring Boot can always parse this object
public class ErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private String details;
}