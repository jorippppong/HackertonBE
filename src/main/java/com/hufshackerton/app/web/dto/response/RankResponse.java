package com.hufshackerton.app.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class RankResponse {

    @Getter
    @Builder
    public static class getTeamByMissionDTO{
        private List<TeamRankInfo> teams;
    }

    @Getter
    @Builder
    public static class TeamRankInfo{
        private Integer rank;
        private String logoUrl;
        private Long completeMission;
        private Long todayVote;
        private Long totalVote;
    }
}
