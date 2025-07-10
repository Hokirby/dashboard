package com.example.dashboard.domain.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthSignUpRequest (
        @NotBlank @Email
        String email,
        @NotBlank
        String nickname,
        @NotBlank
        String password
) {
}
