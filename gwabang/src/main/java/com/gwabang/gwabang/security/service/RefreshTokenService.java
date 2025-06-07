package com.gwabang.gwabang.security.service;

import com.gwabang.gwabang.security.config.jwt.entity.RefreshToken;
import com.gwabang.gwabang.security.config.jwt.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }

    public RefreshToken saveRefreshToken(Long memberId, String refreshToken) {
        RefreshToken token = new RefreshToken(memberId, refreshToken);
        return refreshTokenRepository.save(token);
    }

    @Transactional
    public RefreshToken saveOrUpdate(Long memberId, String refreshToken) {
        return refreshTokenRepository.findByMemberId(memberId)
                .map(existingToken -> {
                    log.info("기존 RefreshToken 있음, 업데이트 진행 - memberId: {}", memberId);
                    existingToken.update(refreshToken);
                    return existingToken;
                })
                .orElseGet(() -> {
                    log.info("새 RefreshToken 생성 - memberId: {}", memberId);
                    RefreshToken newToken = new RefreshToken(memberId, refreshToken);
                    return refreshTokenRepository.save(newToken);
                });
    }


    @Transactional
    public void deleteRefreshToken(Long memberId) {
        refreshTokenRepository.deleteByMemberId(memberId);
    }
}
