package com.hufshackerton.app.web.controller;

import com.hufshackerton.app.converter.MissionConverter;
import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.service.MissionCommandService;
import com.hufshackerton.app.web.dto.request.MissionRequest;
import com.hufshackerton.app.web.dto.response.MissionResponse;
import com.hufshackerton.global.annotation.auth.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "💬 Mission", description = "미션 관련 API")
@RequestMapping("/missions")
public class MissionController {

    private final MissionCommandService missionCommandService;

    @Operation(summary = "오늘의 미션 할당/조회 API", description = "오늘의 미션이 없으면 미션을 할당하고 조회, 있다면 미션을 조회합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
    })
    @GetMapping("/today")
    public ResponseEntity<MissionResponse.MissionResponseDTO> getMemberMission(
            @Parameter(hidden = true) @AuthMember Member member) {
        return ResponseEntity.ok(
                MissionConverter.toMissionResponse(missionCommandService.getTodayMission(member)));
    }

    @Operation(summary = "미션 생성 API", description = "미션 생성을 합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @PostMapping("/")
<<<<<<< HEAD
    public ResponseEntity<MissionResponse.createMissionResponseDTO> createMission(
=======
    public ResponseEntity<MissionResponse.CreateMissionResponseDTO> createMission(
>>>>>>> 893126af94bd5151dcbcc2bd7cc13f7c1a6e5ace
            @RequestBody MissionRequest.createMissionRequestDTO requset) {
        return ResponseEntity.ok(MissionConverter.toCreateMissionResponseDTO(missionCommandService.createMission(requset)));
    }

}
