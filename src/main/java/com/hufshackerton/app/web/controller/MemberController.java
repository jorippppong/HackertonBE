package com.hufshackerton.app.web.controller;


import com.hufshackerton.app.converter.MemberConverter;
import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.service.MemberCommandService;
import com.hufshackerton.app.service.MemberQueryService;
import com.hufshackerton.app.web.dto.response.MemberResponse;
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
    private final MemberCommandService memberCommandService;

    @Operation(summary = "내 정보 조회 API", description = "내 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @GetMapping("")
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

    @Operation(summary = "기부 API", description = "내 포인트를 통해 기부를 합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @PatchMapping("/donation/{donateId}")
    public ResponseEntity<MemberResponse.GetMyPointDTO> donate(@Parameter(hidden = true) @AuthMember Member member, @RequestParam(name = "donateId") Long donateId) {
        return ResponseEntity.ok(MemberConverter.toGetMyPoint(memberCommandService.donatePoint(member, donateId)));
    }

    @PatchMapping("/preferTeam")
    public ResponseEntity<MemberResponse.changePreferTeamDTO> changePreferTeam(
            @Parameter(hidden = true) @AuthMember Member member,
            @RequestParam("teamId") Long teamId
    ){
        String url = memberCommandService.changePreferTeam(member, teamId);
        return ResponseEntity.ok(
                MemberResponse.changePreferTeamDTO.builder().teamUrl(url).build()
        );
    }


}
