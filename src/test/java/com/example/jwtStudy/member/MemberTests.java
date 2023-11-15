package com.example.jwtStudy.member;

import com.example.jwtStudy.base.member.entity.Member;
import com.example.jwtStudy.base.member.service.MemberService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberTests {
    @Autowired
    MemberService memberService;
    @Test
    @DisplayName("회원 가입 테스트")
    void joinTest() {
        try {
            memberService.join("관리자", "1234");
            Member member = memberService.checkMember("관리자", "1234");
            assertThat(member.getUsername()).isEqualTo("관리자");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
