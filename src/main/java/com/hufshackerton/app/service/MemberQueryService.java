package com.hufshackerton.app.service;

import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.repository.BetRepository;
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
    private final BetRepository betRepository;

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

    public String calculateMyWinningRate(Member member) {

        Integer totalCount = betRepository.findAllByMember_IdAndSuccessIsNotNull(member.getId()).size();
        Integer winningCount = betRepository.findAllBySuccessIsTrueAndMember_Id(member.getId()).size();

        if (totalCount == 0) {
            return String.valueOf(0);
        }

        return String.format("%.1f", winningCount/totalCount*10);
    }
}