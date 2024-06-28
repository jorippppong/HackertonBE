package com.hufshackerton.app.web.dto.request;

import lombok.Getter;

public class MissionRequest {

    @Getter
    public static class createMissionRequestDTO {
        String content;
    }
}
