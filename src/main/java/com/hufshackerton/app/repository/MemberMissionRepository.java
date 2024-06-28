package com.hufshackerton.app.repository;

import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.domain.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Optional<MemberMission> findMemberMissionByMemberAndCreatedAtBetween(
            Member member, LocalDateTime start, LocalDateTime end);

    Optional<MemberMission> findMemberMissionByMember_Id(Long memberId);

}
