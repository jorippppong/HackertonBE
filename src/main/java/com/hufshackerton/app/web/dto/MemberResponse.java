package com.hufshackerton.app.web.dto;

import lombok.Builder;
import lombok.Getter;

public class MemberResponse {
    @Getter
    @Builder
    public static class ModifyProfileImageDTO{
        private String profileImageUrl;
    }
}
