package com.example.dashboard.domain.auth.entity;

import com.example.dashboard.domain.auth.enums.MemberRole;
import com.example.dashboard.common.TimeStamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    @Column
    private String nickname;
    @Column
    private String password;
    @Enumerated(EnumType.STRING)
    private MemberRole role = MemberRole.MEMBER_ROLE;

    public Member(String email, String nickname, String password, MemberRole role) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
    }
}
