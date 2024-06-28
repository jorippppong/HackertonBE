package com.hufshackerton.app.web.controller;

import com.hufshackerton.app.converter.MemberConverter;
import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.service.MemberCommandService;
import com.hufshackerton.app.web.dto.request.AuthRequest;
import com.hufshackerton.app.web.dto.response.AuthResponse;
import com.hufshackerton.global.annotation.ExistEmail;
import com.hufshackerton.global.annotation.ExistNickname;
import com.hufshackerton.global.annotation.auth.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final MemberCommandService memberCommandService;

    @Operation(summary = "회원가입 API", description = "회원가입을 진행합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "성공"),
    })
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthResponse.SignUpMemberResponse> signUpMember(@RequestBody AuthRequest.SignupDTO request) {
        return ResponseEntity.ok(
                MemberConverter.toSignUpMemberResponse(memberCommandService.signUpMember(request)));
    }


    @Operation(summary = "로그인 API", description = "이메일, 비밀번호를 사용한 로그인을 진행합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "성공"),
    })
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthResponse.TokenResponse> loginMember(@RequestBody AuthRequest.LoginDTO request) {
        return ResponseEntity.ok(memberCommandService.login(request));
    }

    @Operation(summary = "회원 탈퇴 API", description = "회원을 탈퇴시킵니다.")
    @ApiResponse(responseCode = "201", description = "성공")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMember(@Parameter(hidden = true) @AuthMember Member member) {
        return ResponseEntity.ok(memberCommandService.deleteMember(member));
    }

    @GetMapping("/check-email")
    public ResponseEntity<Void> duplicationCheckEmail(
            @RequestParam("email") @ExistEmail String email
    ){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/check-nickname")
    public ResponseEntity duplicationCheckNickname(
            @RequestParam("nickname") @ExistNickname String nickname
    ){
        return ResponseEntity.ok().build();
    }

}
