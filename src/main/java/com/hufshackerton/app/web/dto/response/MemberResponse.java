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

    @Getter
    @Builder
    public static class GetMemberWinningRateDTO {
        String winningRate;
    }

    @Getter
    @Builder
    public static class GetMyPointDTO {
        Integer myPoint;
        Integer accumulateDonatePoint;
    }

    @Getter
    @Builder
    public static class changePreferTeamDTO{
        private String teamUrl;
    }
}
