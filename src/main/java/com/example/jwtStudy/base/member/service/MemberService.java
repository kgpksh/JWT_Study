package com.example.jwtStudy.base.member.service;

import com.example.jwtStudy.base.member.entity.Member;
import com.example.jwtStudy.base.member.reposeitory.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLDataException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final  PasswordEncoder passwordEncoder;

    @Transactional
    public Member join(String username, String password) throws IllegalAccessException {
        Optional<Member> checkMember = memberRepository.findMemberByUsername(username);
        if (checkMember.isPresent()) {
            throw new IllegalAccessException("이미 존재 하는 아이디입니다.");
        }
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(passwordEncoder.encode(password));
        memberRepository.save(member);

        return member;
    }

    public Member checkMember(String username, String password) throws Exception {
        Optional<Member> queriedMember = memberRepository.findMemberByUsername(username);
        if (queriedMember.isEmpty()) {
            throw new SQLDataException("존재 하지 않는 회원입니다.");
        }

        Member member = queriedMember.get();

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new SQLDataException("비밀번호가 틀렸습니다.");
        }

        return member;
    }
}
