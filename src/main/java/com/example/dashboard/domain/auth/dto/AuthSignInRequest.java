package com.example.dashboard.domain.auth.dto;

public record AuthSignInRequest(
        String email,
        String password
) {
}
