package com.hufshackerton.app.web.dto.repo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SimpleTeamInfo {
    private Long teamId;
    private Long completeMissions;
}
