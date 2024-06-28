package com.hufshackerton.app.service;

import com.hufshackerton.app.converter.MemberConverter;
import com.hufshackerton.app.domain.Donate;
import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.domain.Team;
import com.hufshackerton.app.repository.DonateRepository;
import com.hufshackerton.app.repository.MemberRepository;
import com.hufshackerton.app.repository.TeamRepository;
import com.hufshackerton.app.web.dto.request.AuthRequest;
import com.hufshackerton.app.web.dto.response.AuthResponse;
import com.hufshackerton.app.web.dto.response.MemberResponse;
import com.hufshackerton.global.exception.ErrorCode;
import com.hufshackerton.global.exception.RestApiException;
import com.hufshackerton.global.file.S3Uploader;
import com.hufshackerton.global.security.provider.JwtAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandService {

    private final MemberRepository memberRepository;
    private final JwtAuthProvider jwtAuthProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DonateRepository donateRepository;
    private final TeamRepository teamRepository;

    public Member signUpMember(AuthRequest.SignupDTO request) {
        Team team = teamRepository.findById(request.getTeamId()).orElseThrow(() -> new RestApiException(ErrorCode.NOT_EXIST_TEAM));
        return memberRepository.save(MemberConverter.toMember(request, team));
    }

    public AuthResponse.TokenResponse login(AuthRequest.LoginDTO request) {
        Member member =
                memberRepository
                        .findMemberByNickname(request.getNickname())
                        .orElseThrow(() -> new RestApiException(ErrorCode.MEMBER_NOT_FOUND));

        if (!(member.getPassword().isSamePassword(request.getPassword(), bCryptPasswordEncoder))) {
            throw new RestApiException(ErrorCode.PASSWORD_MISMATCH);
        }

        String accessToken = jwtAuthProvider.generateAccessToken(member.getId());
        String refreshToken = jwtAuthProvider.generateRefreshToken(member.getId());

        return MemberConverter.toLoginMemberResponse(member.getId(), accessToken, refreshToken);
    }

    public String deleteMember(Member member) {
        try {
            memberRepository.delete(member);
            return "삭제완료";
        } catch (RestApiException e) {
            throw new RestApiException(ErrorCode.MEMBER_NOT_FOUND);
        }
    }

    public Member donatePoint(Member member, Long donateId) {
        Donate donate = donateRepository.findById(donateId)
                .orElseThrow(() -> new RestApiException(ErrorCode.NOT_EXIST_DONATE));

        if (member.getPoint() < donate.getPrice()) {
            throw new RestApiException(ErrorCode.NOT_ENOUGH_POINT);
        }

        member.setPoint(member.getPoint() - donate.getPrice());
        member.setAccumulateDonatePoint(member.getAccumulateDonatePoint() + donate.getPrice());

        return memberRepository.save(member);
    }

    public String changePreferTeam(Member member, Long teamId){
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new RestApiException(ErrorCode.TEAM_NOT_FOUND));
        member.setTeam(team);
        return team.getImageUrl();
    }
}
