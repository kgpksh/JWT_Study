package com.example.jwtStudy.base.member.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtService {
    private static final long tokenDuration = 60L * 60 * 1000;

    public static String createJwt(String username, String secretKey) throws IllegalAccessException {
        return Jwts.builder()
                .claim("username", username)
                .setExpiration(new Date(System.currentTimeMillis() + tokenDuration))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

    }

    public static boolean isExpired(String token, String secretKey) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public static String getUsername(String token, String secretKey) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("username", String.class);
    }
}
