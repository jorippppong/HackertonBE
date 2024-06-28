package com.hufshackerton.app.web.dto.response;

import lombok.*;

public class AuthResponse {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class signupDTO{
        private Long memberId;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpMemberResponse {
        Long memberId;
        String name;
        String email;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TokenResponse {

        Long memberId;
        String accessToken;
        String refreshToken;
    }
}
