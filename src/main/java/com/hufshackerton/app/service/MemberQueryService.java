package com.hufshackerton.app.service;

import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.repository.MemberRepository;
import com.hufshackerton.global.exception.ErrorCode;
import com.hufshackerton.global.exception.RestApiException;
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

    public Member findMemberById(Long memberId) {
        return memberRepository
                .findById(memberId)
                .orElseThrow(() -> new RestApiException(ErrorCode.MEMBER_NOT_FOUND));
    }
<<<<<<< HEAD

=======
>>>>>>> 893126af94bd5151dcbcc2bd7cc13f7c1a6e5ace
}