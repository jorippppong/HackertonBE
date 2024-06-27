package com.hufshackerton.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionAdviceHandler {
    @ExceptionHandler(RestApiException.class)
    public ResponseEntity handleRestApiException(RestApiException e){
        log.error("[RestApiException] cause: {}, message:{}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getHttpStatus(), errorCode.getCode(), errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e){
        log.error("[Exception] cause: {}, message:{}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getHttpStatus(), errorCode.getCode(), errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException e){
        log.error("[IllegalArgumentException] cause: {}, message:{}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        ErrorCode errorCode = ErrorCode.ILLEGAL_ARGUMENT_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getHttpStatus(), errorCode.getCode(),
                String.format("%s %s", errorCode.getMessage(), NestedExceptionUtils.getMostSpecificCause(e).getMessage()));
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("[MethodArgumentNotValidException] cause: {}, message:{}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        ErrorCode errorCode = ErrorCode.INVALID_ARGUMENT_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getHttpStatus(), errorCode.getCode(), errorCode.getMessage(),
                e.getBindingResult());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e){
        // 전체 ConstraintViolation 세트를 가져옴
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();

        // 각 ConstraintViolation에서 메시지 추출
        String errorMessage = violations.stream()
                .map(ConstraintViolation::getMessage) // 메시지만 추출
                .collect(Collectors.joining(", ")); // 여러 메시지를 콤마로 구분하여 하나의 문자열로 결합

        log.error("[ConstraintViolationException] cause: {}, message:{}", NestedExceptionUtils.getMostSpecificCause(e), errorMessage);
        ErrorCode errorCode = ErrorCode.INVALID_ARGUMENT_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getHttpStatus(), errorCode.getCode(), errorMessage);
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }


    // 파일 용량 초과
    @ExceptionHandler({FileSizeLimitExceededException.class, MaxUploadSizeExceededException.class})
    public ResponseEntity<ErrorResponse> handleFileSizeLimitExceededException(Exception e){
        log.error("[FileSizeLimitExceededException] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(e),e.getMessage());
        ErrorCode errorCode = ErrorCode.FILE_SIZE_LIMIT_EXCEED;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getHttpStatus(), errorCode.getCode(), errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

    //잘못된 포맷 요청 -> Json으로 안보내다던지
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        log.error("[HttpMessageNotReadableException] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(e),e.getMessage());
        ErrorCode errorCode = ErrorCode.INVALID_FORMAT_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getHttpStatus(), errorCode.getCode(),  errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpMethodException(
            HttpRequestMethodNotSupportedException e,
            HttpServletRequest request
    ) {
        log.error("[HttpRequestMethodNotSupportedException] " +
                        "url: {} | errorType: {} | errorMessage: {} | cause Exception: ",
                request.getRequestURL(), ErrorCode.INVALID_HTTP_METHOD, ErrorCode.INVALID_HTTP_METHOD.getMessage(), e);

        ErrorCode errorCode = ErrorCode.INVALID_HTTP_METHOD;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getHttpStatus(), errorCode.getCode(),  errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

}
