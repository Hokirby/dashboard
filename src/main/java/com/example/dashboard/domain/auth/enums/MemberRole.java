package com.example.dashboard.domain.auth.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum MemberRole {

    MEMBER_ROLE(Authority.member),
    ADMIN_ROLE(Authority.admin);

    private final String memberRole;

    public static MemberRole of ( String memberRole ) {
        return Arrays.stream(MemberRole.values())
                .filter(r -> r.name().equalsIgnoreCase(memberRole))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid MemberRole"));
    }

    public static class Authority {
        public static final String member = "ROLE_MEMBER";
        public static final String admin = "ROLE_ADMIN";
    }
}
