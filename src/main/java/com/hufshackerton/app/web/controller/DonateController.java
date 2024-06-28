package com.hufshackerton.app.web.controller;

import com.hufshackerton.app.converter.MemberConverter;
import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.service.DonateQueryService;
import com.hufshackerton.app.web.dto.response.DonateResponse;
import com.hufshackerton.app.web.dto.response.MemberResponse;
import com.hufshackerton.global.annotation.auth.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/donate")
@RequiredArgsConstructor
public class DonateController {

    private final DonateQueryService donateQueryService;

    @Operation(summary = "기부 목록 조회 API", description = "기부 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @GetMapping("")
    public ResponseEntity<List<DonateResponse.DonateResponseDTO>> getDonate(@Parameter(hidden = true) @AuthMember Member member) {
        return ResponseEntity.ok(donateQueryService.getDonateList());
    }

}
