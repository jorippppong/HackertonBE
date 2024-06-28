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
import com.hufshackerton.global.feign.openai.dto.MissionImageAnalysisRequestDto;
import com.hufshackerton.global.feign.openai.dto.MissionImageAnalysisResponseDto;
import com.hufshackerton.global.feign.openai.service.OpenAiClient;
import com.hufshackerton.global.file.S3Uploader;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final OpenAiClient openAiClient;
    private final S3Uploader s3Uploader;

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

    private Boolean isMissionSuccessful(Mission mission, String image) {
        MissionImageAnalysisResponseDto response =
                openAiClient.requestImageAnalysis(
                        MissionImageAnalysisRequestDto.from(mission.getContent(), image));

        return Boolean.parseBoolean(response.getAnswer());
    }

    public MemberMission accomplishMission(
            Member member, Long missionId, MultipartFile missionImage) {
        MemberMission memberMission =
                memberMissionRepository
                        .findById(missionId)
                        .orElseThrow(() -> new RestApiException(ErrorCode.MISSION_NOT_FOUND));

        if (memberMission.getIsCompleted()) {
            throw new RestApiException(ErrorCode.MISSION_ALREADY_ACCOMPLISHED);
        }

        String base64Image;
        try {
            base64Image =
                    "data:"
                            + missionImage.getContentType()
                            + ";base64,"
                            + Base64.getEncoder().encodeToString(missionImage.getBytes());
        } catch (IOException e) {
            throw new RestApiException(ErrorCode.MISSION_ANALYSIS_FAILED);
        }

        if (!isMissionSuccessful(memberMission.getMission(), base64Image)) {
            return memberMission;
        }

        String imageUrl = s3Uploader.saveMissionImage(missionImage);
        memberMission.missionComplete(imageUrl);

        return memberMission;
    }

}
