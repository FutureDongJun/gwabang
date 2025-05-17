package com.gwabang.gwabang.security.controller;

import com.gwabang.gwabang.member.entity.Member;
import com.gwabang.gwabang.security.config.jwt.TokenProvider;
import com.gwabang.gwabang.security.dto.LoginRequest;
import com.gwabang.gwabang.security.dto.LoginResponse;
import com.gwabang.gwabang.security.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        Member member = (Member) authentication.getPrincipal();
        String accessToken = tokenProvider.generateToken(member, Duration.ofHours(2));
        String refreshToken = tokenProvider.generateToken(member, Duration.ofDays(7));
        
        refreshTokenService.saveRefreshToken(member.getId(), refreshToken);
        
        return ResponseEntity.ok(new LoginResponse(accessToken, refreshToken));
    }
} 