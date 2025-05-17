package com.gwabang.gwabang.security.config.jwt.repository;

import com.gwabang.gwabang.security.config.jwt.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByMemberId(String memberId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
