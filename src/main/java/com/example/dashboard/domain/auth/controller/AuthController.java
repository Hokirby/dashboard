package com.example.dashboard.domain.auth.controller;

import com.example.dashboard.domain.auth.dto.AuthSignInRequest;
import com.example.dashboard.domain.auth.dto.AuthSignInResponse;
import com.example.dashboard.domain.auth.dto.AuthSignUpRequest;
import com.example.dashboard.domain.auth.dto.AuthSignUpResponse;
import com.example.dashboard.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    //회원가입
    @PostMapping("/signup")
    public AuthSignUpResponse signUp (@RequestBody AuthSignUpRequest authSignUpRequest) {
        return authService.signUp(authSignUpRequest);
    }

    //로그인
    @PostMapping("/signin")
    public AuthSignInResponse signIn (@RequestBody AuthSignInRequest authSignInRequest) {
        return authService.signIn(authSignInRequest);
    }

}
