package com.hufshackerton.app.converter;

import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.domain.Mission;
import com.hufshackerton.app.domain.mapping.MemberMission;
import com.hufshackerton.app.web.dto.request.MissionRequest;
import com.hufshackerton.app.web.dto.response.MissionResponse;
import org.springframework.stereotype.Component;

@Component
public class MissionConverter {

    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder().mission(mission).member(member).build();
    }

    public static MissionResponse.MissionResponseDTO toMissionResponse(MemberMission memberMission) {
        return MissionResponse.MissionResponseDTO.builder()
                .missionId(memberMission.getId())
                .content(memberMission.getMission().getContent())
                .isCompleted(memberMission.getIsCompleted())
                .imageUrl(memberMission.getImageUrl())
                .build();
    }

    public static Mission toMission(MissionRequest.createMissionRequestDTO request) {
        return Mission.builder()
                .content(request.getContent())
                .build();
    }

    public static MissionResponse.CreateMissionResponseDTO toCreateMissionResponseDTO(Mission mission) {
        return MissionResponse.CreateMissionResponseDTO.builder()
                .missionId(mission.getId())
                .content(mission.getContent())
                .build();
    }

    public static MissionResponse.MissionResultDto toMissionResultDto(MemberMission memberMission) {
        return MissionResponse.MissionResultDto.builder().isCompleted(memberMission.getIsCompleted()).build();
    }

}
