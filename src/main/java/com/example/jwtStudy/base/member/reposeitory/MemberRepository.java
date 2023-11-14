package com.example.jwtStudy.base.member.reposeitory;

import com.example.jwtStudy.base.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
