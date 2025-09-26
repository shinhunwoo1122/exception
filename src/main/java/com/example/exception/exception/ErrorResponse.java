package com.example.exception.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final int code;          // 타입을 int로 변경
    private final String message;
    private final String path;

    public ErrorResponse(LocalDateTime timestamp, int status, String error, int code, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.code = code; // int 값을 받습니다.
        this.message = message;
        this.path = path;
    }

    // Getter 메소드 (필수)
    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public int getCode() { return code; } // int 형으로 반환
    public String getMessage() { return message; }
    public String getPath() { return path; }
}
