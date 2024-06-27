package com.hufshackerton.app.web.controller;

import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.service.AuthService;
import com.hufshackerton.app.web.dto.AuthRequest;
import com.hufshackerton.app.web.dto.AuthResponse;
import com.hufshackerton.global.annotation.ExistEmail;
import com.hufshackerton.global.annotation.ExistNickname;
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
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse.signupDTO> signup(
            @RequestPart("data")AuthRequest.SignupDTO dto,
            @RequestPart("profileImage")MultipartFile multipartFile
            )
    {
        Member member = authService.signup(dto, multipartFile);
        return ResponseEntity.ok(
                AuthResponse.signupDTO.builder().memberId(member.getId()).build()
        );
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

//    @PostMapping("/login")
//    public ResponseEntity login(
//            @ResponseBody
//    ){
//
//    }



}
