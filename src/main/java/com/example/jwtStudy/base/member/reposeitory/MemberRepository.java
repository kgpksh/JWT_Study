package com.example.jwtStudy.base.member.reposeitory;

import com.example.jwtStudy.base.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByUsername(String username);
}
