package com.hufshackerton.app.web.controller;

import com.hufshackerton.app.converter.MissionConverter;
import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.domain.mapping.MemberMission;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping("")
    public ResponseEntity<MissionResponse.CreateMissionResponseDTO> createMission(
            @RequestBody MissionRequest.createMissionRequestDTO request) {
        return ResponseEntity.ok(MissionConverter.toCreateMissionResponseDTO(missionCommandService.createMission(request)));
    }

    @Operation(summary = "미션 수행 API", description = "미션 수행 사진을 받아 수행 여부를 결정합니다.")
    @ApiResponse(description = "성공", responseCode = "201")
    @PostMapping(
            value = "/{missionId}/completed",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MissionResponse.MissionResultDto> accomplishMission(
            @Parameter(hidden = true) @AuthMember Member member,
            @PathVariable("membermissionId") Long missionId,
            @RequestPart("image") MultipartFile missionImage) {
        MemberMission memberMission =
                missionCommandService.accomplishMission(member, missionId, missionImage);
        return ResponseEntity.ok(MissionConverter.toMissionResultDto(memberMission));
    }

}
