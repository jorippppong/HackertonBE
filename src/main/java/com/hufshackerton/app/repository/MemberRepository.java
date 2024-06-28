package com.hufshackerton.app.repository;

import com.hufshackerton.app.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    Optional<Member> findMemberByNickname(String nickname);
    Optional<Member> findByEmail(String email);
}
