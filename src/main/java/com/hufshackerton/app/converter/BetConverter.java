package com.hufshackerton.app.converter;

import com.hufshackerton.app.domain.BaseballGame;
import com.hufshackerton.app.domain.Bet;
import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.domain.Team;
import com.hufshackerton.app.web.dto.response.BetResponse;
import org.springframework.stereotype.Component;

@Component
public class BetConverter {

    public static Bet toBet(Member member, BaseballGame baseballGame, Team team) {
        return Bet.builder()
                .baseballGame(baseballGame)
                .team(team)
                .member(member)
                .build();
    }

    public static BetResponse.createBetResponseDTO toCreateBetResponse(Bet bet) {
        return BetResponse.createBetResponseDTO.builder()
                .baseballGameId(bet.getBaseballGame().getId())
                .teamName(bet.getTeam().getName())
                .localDate(bet.getBaseballGame().getDate())
                .build();
    }
}
