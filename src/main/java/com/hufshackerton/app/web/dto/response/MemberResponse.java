package com.hufshackerton.app.web.dto.response;

import lombok.Builder;
import lombok.Getter;

public class MemberResponse {
    @Getter
    @Builder
    public static class ModifyProfileImageDTO{
        private String profileImageUrl;
    }

    @Getter
    @Builder
    public static class GetMemberDTO {
        String nickname;
        String email;
        Integer point;
    }
}
