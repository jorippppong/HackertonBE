package com.hufshackerton.app.web.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class GameResponse {

    @Getter
    @Builder
    public static class GameScheduleDTO {
        private List<GameInfo> games;
    }

    @Getter
    @Builder
    public static class GameInfo{
        private String place;
        private List<TeamInfo> teams;
    }

    @Getter
    @Builder
    public static class TeamInfo{
        private String name;
        private String logoUrl;
        private Long vote;
        private Boolean isHome;
    }
}
