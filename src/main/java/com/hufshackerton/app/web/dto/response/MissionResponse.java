package com.hufshackerton.app.web.dto.response;

import lombok.*;

public class MissionResponse {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MissionResponseDTO {
        Long missionId;
        String content;
        Boolean isCompleted;
        String imageUrl;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateMissionResponseDTO {
        Long missionId;
        String content;
    }

    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @Builder
    public static class MissionResultDto {
        private Boolean isCompleted;
    }

}

