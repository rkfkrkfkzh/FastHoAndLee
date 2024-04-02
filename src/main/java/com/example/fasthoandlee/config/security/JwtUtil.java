package com.example.fasthoandlee.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    // 1. JWT 생성 메서드
    public String generateToken(String username) {
        // Base64로 인코딩된 시크릿 키 사용
        byte[] secretBytes = Base64.getEncoder().encode(secretKey.getBytes());
        Key key = Keys.hmacShaKeyFor(secretBytes);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(key)
                .compact();
    }


    // 2. JWT 토큰 유효성 검사 메서드
    public Boolean validateToken(String token, String username) {
        final String usernameInToken = getUsernameFromToken(token);
        return (username.equals(usernameInToken) && !isTokenExpired(token));
    }

    // 3. JWT 토큰에서 사용자 이름 추출 메서드
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // 4. JWT 토큰 만료 여부 확인 메서드
    private Boolean isTokenExpired(String token) {
        final Date expiration = getClaimFromToken(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    // 5. JWT 토큰에서 정보 추출 메서드
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // 6. JWT 토큰에서 모든 클레임 추출 메서드
    private Claims getAllClaimsFromToken(String token) {
        // Base64로 인코딩된 시크릿 키 사용
        byte[] secretBytes = Base64.getEncoder().encode(secretKey.getBytes());
        Key key = Keys.hmacShaKeyFor(secretBytes);
        // 토큰 파싱 시 시크릿 키 설정
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        return claimsJws.getBody();
    }
}
