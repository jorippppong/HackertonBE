package com.hufshackerton.app.web.dto.response;

import lombok.Builder;
import lombok.Getter;

public class MemberResponse {
    @Getter
    @Builder
    public static class ModifyProfileImageDTO{
        private String profileImageUrl;
    }
<<<<<<< HEAD
=======

    @Getter
    @Builder
    public static class GetMemberDTO {
        String nickname;
        String email;
        Integer point;
    }
>>>>>>> 893126af94bd5151dcbcc2bd7cc13f7c1a6e5ace
}
