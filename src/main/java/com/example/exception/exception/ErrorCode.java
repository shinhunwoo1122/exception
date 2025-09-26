package com.example.exception.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, 4001, "유효하지 않은 입력 값입니다. 데이터를 다시 확인하세요."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, 4041, "요청하신 리소스를 찾을 수 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 4011, "인증 정보가 유효하지 않거나 누락되었습니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, 4031, "해당 리소스에 접근할 권한이 없습니다."),
    // 5XX 서버 오류 (Server Errors)
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 5000, "서버 내부에서 알 수 없는 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    ErrorCode(HttpStatus httpStatus, int code, String message){
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getHttpStatus() { return httpStatus; }
    public int getCode() { return code; }
    public String getMessage() { return message; }

}
