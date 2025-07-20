package com.example.dashboard.domain.auth.entity;

import com.example.dashboard.domain.auth.enums.MemberRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class AuthMember {

    private final Long memberId;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthMember(Long memberId, String email, MemberRole memberRole) {

        this.memberId = memberId;
        this.email = email;
        this.authorities = List.of(new SimpleGrantedAuthority(memberRole.name()));
    }
}
