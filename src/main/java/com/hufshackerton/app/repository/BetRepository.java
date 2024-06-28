package com.hufshackerton.app.repository;

import com.hufshackerton.app.domain.Bet;
import com.hufshackerton.app.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
    Long countBetByBaseballGameDateAndTeam(LocalDate date, Team team);
}
