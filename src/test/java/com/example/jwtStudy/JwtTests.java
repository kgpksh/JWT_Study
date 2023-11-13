package com.example.jwtStudy;

import com.example.jwtStudy.jwt.JwtProvider;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class JwtTests {
	@Value("${custom.jwt.secretKey}")
	private String secretKeyPlain;
	@Autowired
	JwtProvider jwtProvider;

	@Test
	@DisplayName("secretKey 존재 여부")
	void isSecretKeyExists() {
		assertThat(secretKeyPlain).isNotNull();
	}

	@Test
	@DisplayName("secretKey 원문으로 hmac암호화 알고리즘에 맞는 SecretKey 객체 얻기")
	void getSecretKeyObject() {
		String keyBase64Encoded = Base64.getEncoder().encodeToString(secretKeyPlain.getBytes());
		SecretKey secretKey = Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());

		assertThat(secretKey).isNotNull();
	}

	@Test
	@DisplayName("SecretKey 객체가 단 한번만 생성되는지 테스트")
	void createSecertKeyOnlyOnce() {
		SecretKey secretKey1 = jwtProvider.getSecretKey();
		SecretKey secretKey2 = jwtProvider.getSecretKey();

		assertThat(secretKey1 == secretKey2).isTrue();
	}

	@Test
	@DisplayName("JWT 생성")
	void createJWT() {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", 1L);
		claims.put("username", "admin");

		String accessToken = jwtProvider.genToken(claims, 60 * 60 * 5);

		System.out.println("accessToken : " + accessToken);
		assertThat(accessToken).isNotNull();
	}

	@Test
	@DisplayName("만료된 토큰 검사.")
	void checkToken() {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", 1L);
		claims.put("username", "admin");

		String accessToken = jwtProvider.genToken(claims, -1);

		System.out.println("accessToken : " + accessToken);

		assertThat(jwtProvider.verify(accessToken)).isFalse();
	}
}
