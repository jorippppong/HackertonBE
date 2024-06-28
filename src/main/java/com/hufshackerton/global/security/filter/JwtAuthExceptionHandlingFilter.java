package com.hufshackerton.global.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import com.hufshackerton.global.exception.ErrorCode;
import com.hufshackerton.global.exception.ErrorResponse;
import com.hufshackerton.global.exception.RestApiException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthExceptionHandlingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (RestApiException e) {
            response.setContentType("application/json; charset=UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            ErrorCode code = e.getErrorCode();

            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.UNAUTHORIZED,
                    code.getHttpStatus().value(),
                    code.getMessage(),
                    null
            );

            ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);

            response.setContentType("application/json; charset=UTF-8");
            response.setStatus(responseEntity.getStatusCodeValue());
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), responseEntity.getBody());
        }
    }
}
