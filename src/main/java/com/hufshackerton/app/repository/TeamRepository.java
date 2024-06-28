package com.hufshackerton.app.repository;

import com.hufshackerton.app.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
