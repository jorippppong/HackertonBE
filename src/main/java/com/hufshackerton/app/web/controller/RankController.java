package com.hufshackerton.app.web.controller;

import com.hufshackerton.app.service.RankQueryService;
import com.hufshackerton.app.web.dto.response.RankResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/rank")
@RequiredArgsConstructor
public class RankController {
    private final RankQueryService rankQueryService;

    @GetMapping("/team/mission")
    public ResponseEntity<RankResponse.getTeamByMissionDTO> getTeamByMission(
            @RequestParam("date")LocalDate localDate
            ){
        RankResponse.getTeamByMissionDTO dto = rankQueryService.getTeamByMission(localDate);
        return ResponseEntity.ok(dto);
    }
}
