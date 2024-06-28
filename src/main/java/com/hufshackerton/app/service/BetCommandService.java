package com.hufshackerton.app.service;

import com.hufshackerton.app.converter.BetConverter;
import com.hufshackerton.app.domain.BaseballGame;
import com.hufshackerton.app.domain.Bet;
import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.domain.Team;
import com.hufshackerton.app.repository.BaseballGameRepository;
import com.hufshackerton.app.repository.BetRepository;
import com.hufshackerton.app.repository.TeamRepository;
import com.hufshackerton.global.exception.ErrorCode;
import com.hufshackerton.global.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BetCommandService {

    private final BetRepository betRepository;
    private final BaseballGameRepository baseballGameRepository;
    private final TeamRepository teamRepository;

    public Bet createBet(Member member, Long baseballGameId, Long teamId) {

        if(betRepository.findByBaseballGame_Id(baseballGameId).isPresent()) {
            throw new RestApiException(ErrorCode.ALREADY_BET_GAME);
        }

        BaseballGame baseballGame = baseballGameRepository.findById(baseballGameId)
                .orElseThrow(() -> new RestApiException(ErrorCode.NOT_EXIST_GAME));

        Team team = teamRepository.findById(teamId).orElseThrow(() -> new RestApiException(ErrorCode.NOT_EXIST_TEAM));

        return betRepository.save(BetConverter.toBet(member, baseballGame, team));
    }

}
