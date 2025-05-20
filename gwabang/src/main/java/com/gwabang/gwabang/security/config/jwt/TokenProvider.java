package com.gwabang.gwabang.security.config.jwt;

import com.gwabang.gwabang.member.entity.Member;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.util.*;

@Slf4j
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;
    private final Key key;
    private final long tokenValidityInMilliseconds;

    public TokenProvider(
            JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        // application.properties의 jwt.secret_key를 사용하여 키 생성
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
        this.tokenValidityInMilliseconds = jwtProperties.getAccessTokenValidity();
    }

    public String generateToken(Member member, Duration expiredAt) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiredAt.toMillis());

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) //헤더 type:jwt
                .setIssuer(jwtProperties.getIssuer()) // 내용 issuer
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .setSubject(member.getEmail())
                .claim("id", member.getId()) // 클레임 id : 멤버 ID
                .signWith(key)
                .compact();
    }

    //토큰 검증
    public boolean validToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty");
        }
        return false;
    }

    //토큰 기반으로 인증 정보를 가져오기
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(claims.getSubject(), "", authorities),
                token, authorities);
    }

    //토큰 기반으로 유저ID 가져오는 메서드
    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // 이메일 또는 사용자 식별자
    }
}
