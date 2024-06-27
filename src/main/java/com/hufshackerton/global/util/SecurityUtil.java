package com.hufshackerton.global.util;

import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.repository.MemberRepository;
import com.hufshackerton.global.exception.ErrorCode;
import com.hufshackerton.global.exception.RestApiException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtil {
    private final MemberRepository memberRepository;

    public Member getMemberFromHeader(HttpServletRequest request){
        String memberId = request.getHeader("memberId");
        if(memberId == null){
            throw new RestApiException(ErrorCode.MISSING_MEMBER_HEADER_ERROR);
        }

        return memberRepository.findById(Long.valueOf(memberId)).orElseThrow(() -> new RestApiException(ErrorCode.MEMBER_NOT_FOUND));
    }

    public String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String inputPassword, String hashedPassword){
        return BCrypt.checkpw(inputPassword, hashedPassword);
    }
}
