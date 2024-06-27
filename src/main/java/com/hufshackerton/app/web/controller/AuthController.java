package com.hufshackerton.app.web.controller;

import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.service.AuthCommandService;
import com.hufshackerton.app.web.dto.AuthRequest;
import com.hufshackerton.app.web.dto.AuthResponse;
import com.hufshackerton.global.annotation.ExistEmail;
import com.hufshackerton.global.annotation.ExistNickname;
import com.hufshackerton.global.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final AuthCommandService authCommandService;
    private final SecurityUtil securityUtil;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse.signupDTO> signup(
            @Valid @RequestPart("data")AuthRequest.SignupDTO dto,
            @RequestPart("profileImage")MultipartFile multipartFile
            )
    {
        Member member = authCommandService.signup(dto, multipartFile);
        return ResponseEntity.ok(
                AuthResponse.signupDTO.builder().memberId(member.getId()).build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse.signupDTO> login(@RequestBody AuthRequest.loginDTO dto) {
        Member member = authCommandService.login(dto);
        return ResponseEntity.ok(
                AuthResponse.signupDTO.builder().memberId(member.getId()).build()
        );
    }

    @DeleteMapping("/termination")
    public ResponseEntity removeMember(
            HttpServletRequest request
    ){
        Member member = securityUtil.getMemberFromHeader(request);
        authCommandService.removeMember(member);
        return ResponseEntity.ok().build();
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
