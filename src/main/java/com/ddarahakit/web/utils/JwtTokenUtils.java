package com.ddarahakit.web.utils;

import com.ddarahakit.web.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtils {
    @Value("${jwt.secret-key}")
    private static String secretKey;
    @Value("${jwt.token.expired-time-ms}")
    private static Integer expiredTimeMs;

    public JwtTokenUtils(@Value("${jwt.secret-key}") String secretKey, @Value("${jwt.token.expired-time-ms}") Integer expiredTimeMs) {
        this.secretKey = secretKey;
        this.expiredTimeMs = expiredTimeMs;
    }

    public static String generateCeoAccessToken(String username) {
        Claims claims = Jwts.claims();
        claims.put("username", username);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTimeMs))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    // 토큰 생성
    public static String generateAccessToken(User user) {
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("name", user.getName());
        claims.put("roles", user.getAuthority());

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTimeMs))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    public static Key getSignKey() {

        return Keys.hmacShaKeyFor(secretKey.getBytes());

    }
    public static Claims getClaims(String token) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }

    public static Long getId(String token) {
        Long id = getClaims(token).get("id", Long.class);

        return id;
    }
    public static String getName(String token) {
        String name = getClaims(token).get("name", String.class);

        return name;
    }
    public static String getRoles(String token) {
        String roles = getClaims(token).get("roles", String.class);

        return roles;
    }
}
