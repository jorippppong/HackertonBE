package com.hufshackerton.app.converter;

import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.domain.Password;
import com.hufshackerton.app.domain.Team;
import com.hufshackerton.app.web.dto.request.AuthRequest;
import com.hufshackerton.app.web.dto.response.AuthResponse;
import com.hufshackerton.app.web.dto.response.MemberResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {

    public static Member toMember(AuthRequest.SignupDTO request, Team team) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return Member.builder()
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(Password.encrypt(request.getPassword(), encoder))
                .team(team)
                .build();
    }

    public static AuthResponse.SignUpMemberResponse toSignUpMemberResponse(Member member) {
        return AuthResponse.SignUpMemberResponse.builder()
                .memberId(member.getId())
                .name(member.getNickname())
                .email(member.getEmail())
                .build();
    }

    public static AuthResponse.TokenResponse toLoginMemberResponse(
            Long memberId, String accessToken, String refreshToken) {
        return AuthResponse.TokenResponse.builder()
                .memberId(memberId)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public static MemberResponse.GetMemberDTO toGetMember(Member member) {
        return MemberResponse.GetMemberDTO.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .point(member.getPoint())
                .build();
    }

    public static MemberResponse.GetMemberWinningRateDTO toGetMemberWinningRate(String winningRate) {
        return MemberResponse.GetMemberWinningRateDTO.builder()
                .winningRate(winningRate)
                .build();
    }

    public static MemberResponse.GetMyPointDTO toGetMyPoint(Member member) {
        return MemberResponse.GetMyPointDTO.builder()
                .myPoint(member.getPoint())
                .accumulateDonatePoint(member.getAccumulateDonatePoint())
                .build();
    }

}
