package com.gwabang.gwabang.security.service;

import com.gwabang.gwabang.member.entity.Member;
import com.gwabang.gwabang.member.service.MemberService;
import com.gwabang.gwabang.security.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final MemberService memberService;

    public String createNewAccessToken(String refreshToken) {
        //토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long memberId = Long.valueOf(refreshTokenService.findByRefreshToken(refreshToken).getMemberId());
        Member member = memberService.findById(memberId);


        return tokenProvider.generateToken(member, Duration.ofHours(2));
    }
}