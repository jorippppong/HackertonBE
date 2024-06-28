package com.hufshackerton.app.repository;

import com.hufshackerton.app.domain.BaseballGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BaseballGameRepository extends JpaRepository<BaseballGame, Long> {
    List<BaseballGame> findAllByDate(LocalDate date);
}
