package com.hufshackerton.app.web.dto;

import lombok.Builder;
import lombok.Getter;

public class AuthResponse {

    @Getter
    @Builder
    public static class signupDTO{
        private Long memberId;
    }
}
