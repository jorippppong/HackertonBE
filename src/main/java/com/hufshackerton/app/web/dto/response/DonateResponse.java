package com.hufshackerton.app.web.dto.response;

import lombok.Builder;
import lombok.Getter;

public class DonateResponse {

    @Getter
    @Builder
    public static class DonateResponseDTO {
        Long donateId;
        String content;
        Integer price;
    }

}
