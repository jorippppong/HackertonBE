package com.hufshackerton.app.service;

import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.repository.MemberRepository;
import com.hufshackerton.app.web.dto.AuthRequest;
import com.hufshackerton.global.exception.ErrorCode;
import com.hufshackerton.global.exception.RestApiException;
import com.hufshackerton.global.file.S3Uploader;
import com.hufshackerton.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthCommandService {
    private final MemberRepository memberRepository;
    private final S3Uploader s3Uploader;
    private final SecurityUtil securityUtil;

    @Transactional
    public Member signup(AuthRequest.SignupDTO dto) {
        log.info(dto.getPassword());
        String password = securityUtil.hashPassword(dto.getPassword());
        Member newMember = Member.builder()
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .password(password)
                .build();
        return memberRepository.save(newMember);
    }

    @Transactional
    public Member login(AuthRequest.loginDTO dto) {
        Optional<Member> optionalMember = memberRepository.findMemberByNickname(dto.getNickname());
        if (optionalMember.isEmpty()) {
            throw new RestApiException(ErrorCode.MEMBER_NOT_FOUND);
        }
        Member member = optionalMember.get();
        log.info(member.getPassword());
        log.info(securityUtil.hashPassword(dto.getPassword()));
        if(securityUtil.checkPassword(dto.getPassword(), member.getPassword())){
            return member;
        }else{
            throw new RestApiException(ErrorCode.PASSWORD_WRONG);
        }
    }

    @Transactional
    public void removeMember(Member member) {
        memberRepository.delete(member);
    }


}
