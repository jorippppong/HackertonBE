package com.hufshackerton.app.service;

import com.hufshackerton.app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryService {
    private final MemberRepository memberRepository;

    public boolean existByEmail(String email){
        return memberRepository.existsByEmail(email);
    }

    public boolean existByNickname(String nickname){
        return memberRepository.existsByNickname(nickname);
    }

}