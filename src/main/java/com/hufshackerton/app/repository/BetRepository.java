package com.hufshackerton.app.repository;

import com.hufshackerton.app.domain.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {

    List<Bet> findAllBySuccessIsTrueAndMember_Id(Long memberId);

    List<Bet> findAllByMember_IdAndSuccessIsNotNull(Long memberId);

}
