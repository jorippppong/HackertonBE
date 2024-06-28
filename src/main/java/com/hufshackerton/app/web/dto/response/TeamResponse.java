package com.hufshackerton.app.web.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class TeamResponse {
    @Getter
    @Builder
    public static class GetBaseballTeamsDTO {
        private List<TeamInitInfo> teams;
    }

    @Getter
    @Builder
    public static class TeamInitInfo {
        private Long id;
        private String name;
        private String logoUrl;
    }
}
