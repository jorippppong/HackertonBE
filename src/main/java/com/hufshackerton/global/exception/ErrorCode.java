package com.hufshackerton.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    /*
     * 에러 코드 자유롭게 추가
     */
    INVALID_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, 400, "올바르지 않은 파라미터입니다."),
    INVALID_FORMAT_ERROR(HttpStatus.BAD_REQUEST,400, "올바르지 않은 포맷입니다."),
    INVALID_TYPE_ERROR(HttpStatus.BAD_REQUEST, 400, "올바르지 않은 타입입니다."),
    ILLEGAL_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, 400, "필수 파라미터가 없습니다"),
    INVALID_HTTP_METHOD(HttpStatus.METHOD_NOT_ALLOWED, 400, "잘못된 Http Method 요청입니다."),
    FILE_SIZE_LIMIT_EXCEED(HttpStatus.BAD_REQUEST, 400, "최대 파일 사이즈를 넘어갔습니다.(단일 파일 최대 5MB, 단일 요청 최대 10MB)"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),

    // 새로운 에러 코드 추가
    MISSING_MEMBER_HEADER_ERROR(HttpStatus.BAD_REQUEST, 400, "멤버Id 헤더가 없습니다."),
    PASSWORD_WRONG(HttpStatus.BAD_REQUEST, 400, "비밀번호가 일치하지 않습니다."),

    // AUTH 200X
    ACCESS_DENIED(HttpStatus.FORBIDDEN, 2000,  "권한이 없습니다."),


    // MEMBER 300X
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, 3001, "해당 멤버는 존재하지 않습니다."),
    NOT_VALID_PASSWORD(HttpStatus.BAD_REQUEST, 3002, "비밀번호는 영문, 숫자, 특수문자를 포함한 9~16글자여야 합니다."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, 3003,"비밀번호가 일치하지 않습니다."),
    NOT_ENOUGH_POINT(HttpStatus.BAD_REQUEST, 3004, "포인트가 부족합니다."),

    // MISSION 400x
    ALREADY_EXIST_MISSION(HttpStatus.BAD_REQUEST, 4001, "이미 오늘의 미션이 존재합니다."),


    // TEAM 500X
    TEAM_NOT_FOUND(HttpStatus.NOT_FOUND, 5001, "해당 팀은 존재하지 않습니다."),
    // DONATE 500x
    NOT_EXIST_DONATE(HttpStatus.BAD_REQUEST, 5001, "존재하지 않는 기부입니다.");



    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
