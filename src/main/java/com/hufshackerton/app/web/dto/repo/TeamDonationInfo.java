package com.hufshackerton.app.web.dto.repo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeamDonationInfo{
    private Integer rank;
    private String logoUrl;
    private Long point;
}
