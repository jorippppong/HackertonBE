package com.hufshackerton.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import com.hufshackerton.global.exception.ErrorCode;
import com.hufshackerton.global.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;


@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException)
            throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(HttpStatus.FORBIDDEN.value());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.FORBIDDEN,
                HttpStatus.FORBIDDEN.value(),
                ErrorCode.ACCESS_DENIED.getMessage(),
                null
        );

        ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);

        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(responseEntity.getStatusCodeValue());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), responseEntity.getBody());
    }
}