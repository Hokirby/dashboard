package com.example.dashboard.domain.auth.dto;

public record AuthSignInResponse(
        String AccessToken,
        String RefreshToken
) {
}
