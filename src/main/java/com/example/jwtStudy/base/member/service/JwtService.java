package com.example.jwtStudy.base.member.service;

import com.example.jwtStudy.base.member.dto.LoginForm;
import com.example.jwtStudy.base.member.entity.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    private static final long tokenDuration = 60L * 60 * 1000;
    private final MemberService memberService;
    @Value("${custom.jwt.secretKey}")
    private String secretKey;
    public String createJwt(LoginForm loginForm) throws IllegalAccessException {
        try {
            Member member = memberService.checkMember(loginForm.getUsername(), loginForm.getPassword());
            return Jwts.builder()
                    .claim("username", member.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + tokenDuration))
                    .signWith(SignatureAlgorithm.HS512, secretKey)
                    .compact();
        } catch (Exception e) {
            throw new IllegalAccessException("잘못된 아이디 혹은 비밀번호 입니다.");
        }

    }

    public boolean isExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
