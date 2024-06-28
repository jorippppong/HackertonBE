package com.hufshackerton.app.web.dto.response;

import lombok.*;

import java.time.LocalDate;

public class BetResponse {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class createBetResponseDTO {
        Long baseballGameId;
        String teamName;
        LocalDate localDate;
    }
}
