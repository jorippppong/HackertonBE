package com.hufshackerton.app.web.dto;

import com.hufshackerton.global.annotation.ExistEmail;
import com.hufshackerton.global.annotation.ExistNickname;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

public class AuthRequest {

    @Getter
    @Builder
    public static class SignupDTO{
        @NotBlank @ExistEmail @Email
        private String email;
        @NotBlank @ExistNickname
        private String nickname;
        @NotBlank
        private String password;
    }

    @Getter
    @Builder
    public static class loginDTO{
        @NotBlank
        private String nickname;
        @NotBlank
        private String password;
    }
}