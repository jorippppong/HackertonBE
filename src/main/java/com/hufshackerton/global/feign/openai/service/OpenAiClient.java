package com.hufshackerton.global.feign.openai.service;


import com.hufshackerton.global.config.OpenAiClientConfig;
import com.hufshackerton.global.feign.openai.dto.MissionImageAnalysisRequestDto;
import com.hufshackerton.global.feign.openai.dto.MissionImageAnalysisResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "open-ai-client",
        url = "https://api.openai.com/v1/chat/completions",
        configuration = OpenAiClientConfig.class)
public interface OpenAiClient {

    @PostMapping
    MissionImageAnalysisResponseDto requestImageAnalysis(
            @RequestBody MissionImageAnalysisRequestDto request);
}
