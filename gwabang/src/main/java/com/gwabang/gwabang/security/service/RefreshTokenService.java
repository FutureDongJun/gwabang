package com.gwabang.gwabang.security.service;

import com.gwabang.gwabang.security.config.jwt.entity.RefreshToken;
import com.gwabang.gwabang.security.config.jwt.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                    existingToken.update(refreshToken);
                    return existingToken;
                })
                .orElseGet(() -> {
                    RefreshToken newToken = new RefreshToken(memberId, refreshToken);
                    return refreshTokenRepository.save(newToken);
                });
    }

    @Transactional
    public void deleteRefreshToken(Long memberId) {
        refreshTokenRepository.deleteByMemberId(memberId);
    }
}
