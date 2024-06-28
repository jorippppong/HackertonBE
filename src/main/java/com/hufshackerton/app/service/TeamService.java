package com.hufshackerton.app.service;

import com.hufshackerton.app.domain.Team;
import com.hufshackerton.app.repository.TeamRepository;
import com.hufshackerton.app.web.dto.response.TeamResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {
    private final TeamRepository teamRepository;

    public List<TeamResponse.TeamInitInfo> getBaseballTeams(){
        List<Team> teams = teamRepository.findAll();
        return teams.stream().map(
                t -> TeamResponse.TeamInitInfo.builder()
                        .id(t.getId())
                        .name(t.getName())
                        .logoUrl(t.getImageUrl())
                        .build()
        ).toList();
    }
}
