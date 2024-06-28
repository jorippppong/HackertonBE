package com.hufshackerton.app.web.controller;

import com.hufshackerton.app.converter.BetConverter;
import com.hufshackerton.app.converter.MemberConverter;
import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.service.BetCommandService;
import com.hufshackerton.app.web.dto.response.BetResponse;
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
@RequestMapping("/bet")
@RequiredArgsConstructor
public class BetController {

    private final BetCommandService betCommandService;

    @Operation(summary = "배팅 API", description = "배팅을 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @PostMapping()
    public ResponseEntity<BetResponse.createBetResponseDTO> createBet(@Parameter(hidden = true) @AuthMember Member member, @RequestParam(name = "baseballGameId") Long baseballGameId, @RequestParam(name = "teamId") Long teamId) {
        return ResponseEntity.ok(BetConverter.toCreateBetResponse(betCommandService.createBet(member, baseballGameId, teamId)));
    }

}
