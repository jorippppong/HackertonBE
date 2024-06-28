package com.hufshackerton.app.web.dto.request;

import lombok.Getter;

public class BetRequest {

    @Getter
    public static class createBetRequestDTO {
        Long baseballGameId;
        Long teamId;
    }

}
