package com.hufshackerton.app.service;

import com.hufshackerton.app.domain.BaseballGame;
import com.hufshackerton.app.domain.Team;
import com.hufshackerton.app.repository.BaseballGameRepository;
import com.hufshackerton.app.repository.BetRepository;
import com.hufshackerton.app.web.dto.response.GameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GameQueryService {
    private final BaseballGameRepository baseballGameRepository;
    private final BetRepository betRepository;

    public GameResponse.GameScheduleDTO getGameSchedule(LocalDate localDate){
        List<BaseballGame> game = baseballGameRepository.findAllByDate(localDate);
        return GameResponse.GameScheduleDTO.builder()
                .games(
                        game.stream().map(
                                g -> GameResponse.GameInfo.builder()
                                        .place(g.getLocation())
                                        .teams(toTeamInfos(g, localDate))
                                        .build()
                                ).toList()
                ).build();
    }

    private Long getTodayTeamVotes(Team team, LocalDate date){
        return betRepository.countBetByBaseballGameDateAndTeam(date, team);
    }

    private List<GameResponse.TeamInfo> toTeamInfos(BaseballGame game, LocalDate date){
        List<GameResponse.TeamInfo> teams = new ArrayList<>();
        teams.add(toTeamInfo(game.getHomeTeam(), true, date));
        teams.add(toTeamInfo(game.getAwayTeam(), false, date));
        return teams;
    }

    private GameResponse.TeamInfo toTeamInfo(Team team, boolean isHome, LocalDate date){
        return GameResponse.TeamInfo.builder()
                .name(team.getName())
                .logoUrl(team.getImageUrl())
                .vote(getTodayTeamVotes(team, date))
                .isHome(isHome)
                .build();
    }
}
