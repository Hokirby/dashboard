package com.example.dashboard.domain.auth.service;

import com.example.dashboard.config.JwtUtil;
import com.example.dashboard.domain.auth.dto.AuthSignInResponse;
import com.example.dashboard.domain.auth.dto.AuthSignUpResponse;
import com.example.dashboard.domain.auth.dto.AuthSignInRequest;
import com.example.dashboard.domain.auth.dto.AuthSignUpRequest;
import com.example.dashboard.domain.auth.entity.Member;
import com.example.dashboard.domain.auth.enums.MemberRole;
import com.example.dashboard.domain.auth.repository.MemberRepository;
import com.example.dashboard.exception.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    //회원가입
    public AuthSignUpResponse signUp (AuthSignUpRequest authSignUpRequest) {

        if ( memberRepository.existsByEmail((authSignUpRequest.email())) )  {
            throw new InvalidRequestException("Email Already Exists");
        }

        Member member = new Member(
                authSignUpRequest.email(),
                authSignUpRequest.nickname(),
                authSignUpRequest.password(),
                MemberRole.MEMBER_ROLE
        );

        Member savedMember = memberRepository.save(member);

        return new AuthSignUpResponse(savedMember.getId(), savedMember.getEmail(), savedMember.getNickname(), savedMember.getRole(), savedMember.getCreatedAt());
    }

    //로그인
    public AuthSignInResponse signIn (AuthSignInRequest authSignInRequest) {

        Member member = memberRepository.findByEmail(authSignInRequest.email())
                .orElseThrow(() -> new InvalidRequestException("Email Doesn't Exists"));

        if ( !member.getPassword().matches(authSignInRequest.password()) ) {
            throw new InvalidRequestException("Password Doesn't Matches");
        }

        String accessToken = jwtUtil.createAccessToken(member.getId(), member.getEmail(), member.getNickname(), member.getRole());
        String refreshToken = jwtUtil.createRefreshToken(member.getId(), member.getEmail(), member.getNickname(), member.getRole());

        return new AuthSignInResponse(accessToken, refreshToken);
    }
}
