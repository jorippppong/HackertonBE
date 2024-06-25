package com.hufshackerton.global.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
@JsonPropertyOrder({"success", "httpStatus", "code", "message", "errors"})
public class ErrorResponse {
    private final boolean success = false;
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ValidationError> errors;

    public static ErrorResponse of(HttpStatus httpStatus, int code, String message){
        return ErrorResponse.builder()
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .build();
    }

    public static ErrorResponse of(HttpStatus httpStatus, int code, String message, BindingResult result){
        return ErrorResponse.builder()
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .errors(ValidationError.of(result))
                .build();
    }

    @Getter
    public static class ValidationError{
        private final String field;
        private final String message;

        private ValidationError(FieldError fieldError){
            this.field = fieldError.getField();
            this.message = fieldError.getDefaultMessage();
        }

        public static List<ValidationError> of(BindingResult result) {
            return result.getFieldErrors().stream()
                    .map(ValidationError::new)
                    .toList();
        }
    }



}
