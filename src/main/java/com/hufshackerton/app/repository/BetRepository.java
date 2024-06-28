package com.hufshackerton.app.repository;

import com.hufshackerton.app.domain.Bet;
import com.hufshackerton.app.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {

    List<Bet> findAllBySuccessIsTrueAndMember_Id(Long memberId);

    List<Bet> findAllByMember_IdAndSuccessIsNotNull(Long memberId);

    Long countBetByBaseballGameDateAndTeam(LocalDate date, Team team);
}
