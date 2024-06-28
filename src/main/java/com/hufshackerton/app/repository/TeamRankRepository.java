package com.hufshackerton.app.repository;

import com.hufshackerton.app.web.dto.repo.SimpleTeamInfo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TeamRankRepository {
    public List<SimpleTeamInfo> sortTeamByMission(LocalDate startDate, LocalDate endDate);
}
