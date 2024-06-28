package com.hufshackerton.app.web.controller;

import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.service.MemberCommandService;
import com.hufshackerton.app.web.dto.MemberResponse;
import com.hufshackerton.global.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(summary = "프로필 사진 변경 API",
            parameters = {
                    @Parameter(
                            name = "memberId",
                            description = "사용자의 ID",
                            required = true,
                            in = ParameterIn.HEADER,
                            schema = @Schema(type = "string")
                    )
            })
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
