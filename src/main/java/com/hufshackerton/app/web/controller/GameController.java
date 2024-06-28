package com.hufshackerton.app.web.controller;

import com.hufshackerton.app.service.GameQueryService;
import com.hufshackerton.app.web.dto.response.GameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
    private final GameQueryService gameQueryService;

    @GetMapping
    public ResponseEntity<GameResponse.GameScheduleDTO> getGameSchedule(
            @RequestParam("date") LocalDate localDate
    ){
        GameResponse.GameScheduleDTO response = gameQueryService.getGameSchedule(localDate);
        return ResponseEntity.ok(response);
    }

}
