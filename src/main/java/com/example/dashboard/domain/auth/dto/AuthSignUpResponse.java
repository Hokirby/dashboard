package com.example.dashboard.domain.auth.dto;

import com.example.dashboard.domain.auth.enums.MemberRole;

import java.sql.Timestamp;

public record AuthSignUpResponse(
        Long id,
        String email,
        String nickname,
        MemberRole role,
        Timestamp createdAt
) {
}
