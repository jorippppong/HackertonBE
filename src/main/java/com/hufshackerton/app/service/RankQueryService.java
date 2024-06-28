package com.hufshackerton.app.service;

import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.domain.Team;
import com.hufshackerton.app.repository.*;
import com.hufshackerton.app.web.dto.repo.SimpleMemberInfo;
import com.hufshackerton.app.web.dto.repo.SimpleTeamInfo;
import com.hufshackerton.app.web.dto.response.RankResponse;
import com.hufshackerton.global.exception.ErrorCode;
import com.hufshackerton.global.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RankQueryService {
    private final TeamRankRepositoryImpl teamRankRepository;
    private final MemberRankRepository memberRankRepository;
    private final TeamRepository teamRepository;
    private final BetRepository betRepository;
    private final MemberRepository memberRepository;

    public RankResponse.getTeamByMissionDTO getTeamByMission(LocalDate localDate) {
        LocalDate firstDayOfMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
        List<SimpleTeamInfo> teamInfo = teamRankRepository.sortTeamByMission(firstDayOfMonth, lastDayOfMonth);

        List<RankResponse.TeamRankInfo> teamRankInfos = new ArrayList<>();
        int rank = 1;
        for(SimpleTeamInfo t:teamInfo){
            Team team = teamRepository.findById(t.getTeamId()).orElseThrow(() -> new RestApiException(ErrorCode.TEAM_NOT_FOUND));
            RankResponse.TeamRankInfo teamDtoInfo = RankResponse.TeamRankInfo.builder()
                    .rank(rank)
                    .logoUrl(team.getImageUrl())
                    .completeMission(t.getCompleteMissions())
                    .todayVote(betRepository.countBetByBaseballGameDateAndTeam(localDate, team))
                    .totalVote(betRepository.countBetByBaseballGameDateBetweenAndTeam(firstDayOfMonth, lastDayOfMonth, team))
                    .build();
            teamRankInfos.add(teamDtoInfo);
            rank++;
        }

        return RankResponse.getTeamByMissionDTO.builder().teams(teamRankInfos).build();
    }


    public RankResponse.getMemberByMission getMemberByMission(LocalDate localDate){
        LocalDate firstDayOfMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
        List<SimpleMemberInfo> memberInfo = memberRankRepository.sortMemberByMission(firstDayOfMonth, lastDayOfMonth);

        List<RankResponse.MemberRankInfo> memberRankInfos = new ArrayList<>();
        int rank = 1;
        for(SimpleMemberInfo m:memberInfo){
            Member member = memberRepository.findById(m.getMemberId()).orElseThrow(() -> new RestApiException(ErrorCode.MEMBER_NOT_FOUND));
            RankResponse.MemberRankInfo memberDtoInfo = RankResponse.MemberRankInfo.builder()
                    .rank(rank)
                    .logoUrl(member.getTeam().getImageUrl())
                    .completeMission(m.getCompleteMissions())
                    .totalVote(betRepository.countBetByBaseballGameDateBetweenAndTeam(firstDayOfMonth, lastDayOfMonth, member.getTeam()))
                    .build();
            memberRankInfos.add(memberDtoInfo);
            rank++;
        }

        return RankResponse.getMemberByMission.builder().user(memberRankInfos).build();
    }

}
