package com.hufshackerton.app.repository;

import com.hufshackerton.app.domain.Bet;
import com.hufshackerton.app.domain.Team;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {

    List<Bet> findAllBySuccessIsTrueAndMember_Id(Long memberId);

    List<Bet> findAllByMember_IdAndSuccessIsNotNull(Long memberId);

    Long countBetByBaseballGameDateAndTeam(LocalDate date, Team team);

    Long countBetByBaseballGameDateBetweenAndTeam(LocalDate startDate, LocalDate endDate, Team team);

    Optional<Bet> findByBaseballGame_IdAndMember_Id(Long baseballGameId, Long memberId);
}
