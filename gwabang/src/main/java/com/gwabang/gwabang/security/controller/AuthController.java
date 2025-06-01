package com.gwabang.gwabang.security.controller;

import com.gwabang.gwabang.member.entity.Member;
import com.gwabang.gwabang.member.service.MemberService;
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
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        Member member = memberService.findByEmail(loginRequest.getEmail());
        String accessToken = tokenProvider.generateToken(member, Duration.ofMinutes(30));
        String refreshToken = tokenProvider.generateToken(member, Duration.ofDays(7));
        
        refreshTokenService.saveRefreshToken(member.getId(), refreshToken);

        return ResponseEntity.ok(new LoginResponse(accessToken, refreshToken));
    }
} 