package com.hufshackerton.app.web.controller;

import com.hufshackerton.app.service.TeamService;
import com.hufshackerton.app.web.dto.response.TeamResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @GetMapping()
    public ResponseEntity<TeamResponse.GetBaseballTeamsDTO> getBaseballTeams(){
        List<TeamResponse.TeamInitInfo> teaminfo = teamService.getBaseballTeams();
        return ResponseEntity.ok(
                TeamResponse.GetBaseballTeamsDTO.builder()
                        .teams(teaminfo)
                        .build()
        );
    }


}
