package com.gwabang.gwabang.security.config.jwt;

import com.gwabang.gwabang.member.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.util.*;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;
    private static final String SECRET_KEY = "kimchingAndAIkimchingAndAIkimchingAndAI"; //최소 256비트 필요해서..

    public String generateToken(Member member, Duration expiredAt) {
        Date now = new Date();
        Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), member, key);
    }

    //토큰 생성
    private String makeToken(Date expiry, Member member, Key key) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) //헤더 type:jwt
                .setIssuer(jwtProperties.getIssuer()) // 내용 issuer
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setSubject(member.getEmail())
                .claim("id", member.getId()) // 클레임 id : 멤버 ID
                .signWith(key, SignatureAlgorithm.ES256)
                .compact();
    }

    //토큰 검증
    public boolean validToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtProperties.getSecret_key()));

            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
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
        Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtProperties.getSecret_key()));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
