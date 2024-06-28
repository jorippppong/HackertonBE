package com.hufshackerton.app.web.controller;

import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.service.MemberCommandService;
import com.hufshackerton.app.web.dto.MemberResponse;
import com.hufshackerton.global.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
public class MemberController {


}
