package com.hufshackerton.app.web.dto;

import lombok.Builder;
import lombok.Getter;

public class AuthRequest {

    @Getter
    @Builder
    public static class SignupDTO{
        private String email;
        private String nickname;
        private String password;
    }
}
