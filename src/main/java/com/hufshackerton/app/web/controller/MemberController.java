package com.hufshackerton.app.web.controller;


import com.hufshackerton.app.converter.MemberConverter;
import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.service.MemberQueryService;
import com.hufshackerton.app.web.dto.response.MemberResponse;
import com.hufshackerton.global.annotation.auth.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
public class MemberController {
    private final MemberQueryService memberQueryService;

    @Operation(summary = "내 정보 조회 API", description = "내 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @GetMapping("/")
    public ResponseEntity<MemberResponse.GetMemberDTO> getMission(@Parameter(hidden = true) @AuthMember Member member) {
        return ResponseEntity.ok(MemberConverter.toGetMember(memberQueryService.findMemberById(member.getId())));
    }


    @Operation(summary = "내 승률 조회 API", description = "나의 배팅 승률을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @GetMapping("/winningrate")
    public ResponseEntity<MemberResponse.GetMemberWinningRateDTO> getMemberWinningRate(@Parameter(hidden = true) @AuthMember Member member) {
        return ResponseEntity.ok(MemberConverter.toGetMemberWinningRate(memberQueryService.calculateMyWinningRate(member)));
    }


}
