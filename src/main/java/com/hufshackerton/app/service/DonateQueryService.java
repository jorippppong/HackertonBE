package com.hufshackerton.app.service;

import com.hufshackerton.app.converter.DonateConverter;
import com.hufshackerton.app.domain.Donate;
import com.hufshackerton.app.repository.DonateRepository;
import com.hufshackerton.app.web.dto.response.DonateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DonateQueryService {

    private final DonateRepository donateRepository;

    public List<DonateResponse.DonateResponseDTO> getDonateList() {
        return DonateConverter.toDonateResponseList(donateRepository.findAll());
    }

}
