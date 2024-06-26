package com.hufshackerton.app.service;

import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.repository.MemberRepository;
import com.hufshackerton.global.file.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MemberCommandService {
    private final MemberRepository memberRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public String modifyProfileImage(MultipartFile profileImage, Member member){
        String url = "";
        if(profileImage != null && !profileImage.isEmpty()){
            url = s3Uploader.saveProfileImage(profileImage);
        }
        member.modifyProfileImageUrl(url);
        return url;
    }
}
