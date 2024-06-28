package com.hufshackerton.app.service;

import com.hufshackerton.app.converter.MemberConverter;
import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.repository.MemberRepository;
import com.hufshackerton.app.web.dto.request.AuthRequest;
import com.hufshackerton.app.web.dto.response.AuthResponse;
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
    private final S3Uploader s3Uploader;
    private final JwtAuthProvider jwtAuthProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public Member signUpMember(AuthRequest.SignupDTO request) {
        return memberRepository.save(MemberConverter.toMember(request));
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
        System.out.println(member.getId());
        try {
            memberRepository.delete(member);
            return "삭제완료";
        } catch (RestApiException e) {
            throw new RestApiException(ErrorCode.MEMBER_NOT_FOUND);
        }
    }
}
