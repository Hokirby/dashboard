package com.example.dashboard.domain.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AuthSignUpRequest (
        @NotBlank(message = "Email Must Be Entered")
        @Email(message = "Invalid Email Form")
        String email,
        @NotBlank(message = "Email Must Be Entered")
        String nickname,
        @NotBlank(message = "Password Must Be Entered")
        @Size(min = 8, max = 20, message = "Passwords are not less than 8 characters but not more than 20 characters")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,20}$",
                message = "Passwords must contain all English characters, numbers, and special characters."
        )
        String password
) {
}
