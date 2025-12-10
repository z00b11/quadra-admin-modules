package com.wuyunbin.quadra.admin.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);


    private static final String SECRET_KEY = "123456".repeat(10);


    private static final long EXPIRATION_TIME = 5 * 60 * 1000; // 5分钟



    public static String generateToken(Long userId, String username, Boolean isSuper) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userId);
        claims.put("name", username);
        claims.put("is_super", isSuper);

        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    public static Claims parseToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public static Long getUserIdFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("id", Long.class);
    }


    public static String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("name", String.class);
    }

    public static Boolean getIsSuperFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("is_super", Boolean.class);
    }


    public static boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            log.error("JWT令牌验证失败: {}", e.getMessage());
            return false;
        }
    }

    public static boolean isTokenExpired(String token) {
        Claims claims = parseToken(token);
        return claims.getExpiration().before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return parseToken(token).getExpiration();
    }
}
