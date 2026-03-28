package com.example.demo.service;

import java.security.Key;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import com.example.demo.model.User;


import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private static final String JWT_KEY = System.getenv("JWT_SECRET");
    private static final long EXPIRATION_TIME = 86400000;

    private Key getSigningKey() {
        try{
        return Keys.hmacShaKeyFor(JWT_KEY.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Error generating signing key", e);
        }
    }

    public String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String extractEmail(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Error extracting email from token", e);
        }
    }
    public Long extractUserId(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("userId", Long.class);
        } catch (Exception e) {
            throw new RuntimeException("Error extracting user ID from token", e);
        }
    }
}
