package com.example.jwtStudy.base.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
    @GetMapping("/join")
    public String join() {
        return "join.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }
}
