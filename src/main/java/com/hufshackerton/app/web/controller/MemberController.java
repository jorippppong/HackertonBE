package com.hufshackerton.app.web.controller;

import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.service.MemberCommandService;
import com.hufshackerton.app.web.dto.MemberResponse;
import com.hufshackerton.global.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
public class MemberController {
    private final SecurityUtil securityUtil;
    private final MemberCommandService memberCommandService;

    @PatchMapping("/profileImage")
    public ResponseEntity<MemberResponse.ModifyProfileImageDTO> modifyProfileImage(
            @RequestPart("profileImage")MultipartFile profileImage,
            HttpServletRequest request)
    {
        Member member = securityUtil.getMemberFromHeader(request);
        String profileImageUrl = memberCommandService.modifyProfileImage(profileImage, member);
        return ResponseEntity.ok(
                MemberResponse.ModifyProfileImageDTO.builder()
                        .profileImageUrl(profileImageUrl).build()
        );
    }
}
