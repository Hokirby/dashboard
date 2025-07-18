package com.example.dashboard.domain.auth.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class AuthMember {

    private final Long memberId;
    private final String email;
    private final String nickname;
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthMember(Long memberId, String email, String nickname, String memberRole) {

        this.memberId = memberId;
        this.email = email;
        this.nickname = nickname;
        this.authorities = List.of(new SimpleGrantedAuthority(memberRole));
    }
}
