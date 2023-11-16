package com.example.jwtStudy.base.post.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/post")
public class PostController {
    @GetMapping("/list")
    @ResponseBody
    String showPosts(Authentication authentication) {
        return authentication.getName();
    }
}
