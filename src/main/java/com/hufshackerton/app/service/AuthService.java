package com.hufshackerton.app.service;

import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.repository.MemberRepository;
import com.hufshackerton.app.web.dto.AuthRequest;
import com.hufshackerton.global.file.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final S3Uploader s3Uploader;

    public Member signup(AuthRequest.SignupDTO dto, MultipartFile multipartFile) {
        String url = s3Uploader.saveProfileImage(multipartFile);
        // TODO 비밀번호 암호화
        String password = "";
        Member newMember = Member.builder()
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .password(password)
                .profileImageUrl(url)
                .build();
        return memberRepository.save(newMember);
    }


}
