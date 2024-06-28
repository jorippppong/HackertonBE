package com.hufshackerton.app.service;

import com.hufshackerton.app.converter.MissionConverter;
import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.domain.Mission;
import com.hufshackerton.app.domain.mapping.MemberMission;
import com.hufshackerton.app.repository.MemberMissionRepository;
import com.hufshackerton.app.repository.MissionRepository;
import com.hufshackerton.app.web.dto.request.MissionRequest;
import com.hufshackerton.global.exception.ErrorCode;
import com.hufshackerton.global.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MemberMission getTodayMission(Member member) {

        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();

        return memberMissionRepository
                .findMemberMissionByMemberAndCreatedAtBetween(member, startOfDay, LocalDateTime.now())
                .orElseGet(
                        () -> {
                            return memberMissionRepository.save(
                                    MissionConverter.toMemberMission(member, missionRepository.findRandomMission()));
                        });
    }

    public Mission createMission(MissionRequest.createMissionRequestDTO request) {
        return missionRepository.save(MissionConverter.toMission(request));
    }

}
