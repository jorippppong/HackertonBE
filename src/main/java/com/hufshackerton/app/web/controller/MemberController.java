package com.hufshackerton.app.web.controller;

import com.hufshackerton.app.converter.MemberConverter;
import com.hufshackerton.app.converter.MissionConverter;
import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.service.MemberQueryService;
import com.hufshackerton.app.web.dto.request.MissionRequest;
import com.hufshackerton.app.web.dto.response.MemberResponse;
import com.hufshackerton.app.web.dto.response.MissionResponse;
import com.hufshackerton.global.annotation.auth.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
public class MemberController {

    private final MemberQueryService memberQueryService;

    @Operation(summary = "내 정보 조회 API", description = "내 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @PostMapping("/")
    public ResponseEntity<MemberResponse.GetMemberDTO> getMission(@Parameter(hidden = true) @AuthMember Member member) {
        return ResponseEntity.ok(MemberConverter.toGetMember(memberQueryService.findMemberById(member.getId())));
    }


}
