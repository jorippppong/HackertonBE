package com.hufshackerton.app.web.dto.response;

import com.hufshackerton.app.web.dto.repo.TeamDonationInfo;
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

    @Getter
    @Builder
    public static class getMemberByMission{
        private List<MemberRankInfo> user;
    }

    @Getter
    @Builder
    public static class MemberRankInfo{
        private Integer rank;
        private String nickname;
        private String logoUrl;
        private Long completeMission;
        private Long totalVote;
    }

    @Getter
    @Builder
    public static class getTeamByDonation{
        private List<TeamDonationInfo> teams;
    }

//    @Getter
//    @Builder
//    public static class TeamDonationInfo{
//        private Long rank;
//        private String logoUrl;
//        private Long point;
//    }

}
