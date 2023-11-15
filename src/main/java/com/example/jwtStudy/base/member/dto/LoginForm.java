package com.example.jwtStudy.base.member.dto;

import lombok.Data;

@Data
public class LoginForm {
    private final String username;
    private final String password;
}
