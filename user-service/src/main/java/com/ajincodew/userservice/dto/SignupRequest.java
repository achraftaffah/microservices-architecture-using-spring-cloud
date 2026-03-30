package com.ajincodew.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(

        @NotBlank
        String username,

        @NotBlank
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password
) {
}