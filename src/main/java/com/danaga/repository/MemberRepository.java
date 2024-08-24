package com.danaga.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danaga.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByUserName(String MemberId);
	Optional<Member> findByEmail(String MemberEmail);
	Optional<Member> findByPhoneNo(String MemberPhoneNo);
	Optional<Member> findByNickname(String nickname);
	
}
