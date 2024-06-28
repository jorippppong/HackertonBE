package com.hufshackerton.app.web.dto.repo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SimpleMemberInfo {
    private Long memberId;
    private Long completeMissions;
}
