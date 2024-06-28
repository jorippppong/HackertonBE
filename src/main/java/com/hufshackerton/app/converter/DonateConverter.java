package com.hufshackerton.app.converter;

import com.hufshackerton.app.domain.Donate;
import com.hufshackerton.app.web.dto.response.DonateResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DonateConverter {

    public static List<DonateResponse.DonateResponseDTO> toDonateResponseList(List<Donate> donateList) {
        return donateList.stream()
                .map(donate -> toDonateResponse(donate))
                .collect(Collectors.toList());
    }

    public static DonateResponse.DonateResponseDTO toDonateResponse(Donate donate) {
        return DonateResponse.DonateResponseDTO.builder()
                .donateId(donate.getId())
                .content(donate.getContent())
                .price(donate.getPrice())
                .build();
    }
}
