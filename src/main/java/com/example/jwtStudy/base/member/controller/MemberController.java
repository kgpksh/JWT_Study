package com.example.jwtStudy.base.member.controller;

import com.example.jwtStudy.base.member.dto.JoinForm;
import com.example.jwtStudy.base.member.dto.LoginForm;
import com.example.jwtStudy.base.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/join")
    public String join() {
        return "member/join.html";
    }

    @PostMapping("/join")
    public String joinSubmit(JoinForm joinForm) {
        try {
            memberService.join(joinForm.getUsername(), joinForm.getPassword());
        } catch (Exception e) {
            return e.getMessage();
        }

        return "main/main.html";
    }

    @GetMapping("/login")
    public String login() {
        return "member/login.html";
    }

    @PostMapping("/login")
    public ResponseEntity<String> sendLogin(LoginForm loginForm) {
        return ResponseEntity.ok().body(memberService.login(loginForm));
    }
}
