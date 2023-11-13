package com.example.jwtStudy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class JwtTests {
	@Value("${custom.jwt.secretKey}")
	private String secretKey;

	@Test
	@DisplayName("secretKey 존재 여부")
	void isSecretKeyExists() {
		assertThat(secretKey).isNotNull();
	}

}
