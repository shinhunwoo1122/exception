package com.example.exception.handler;

import com.example.exception.exception.CustomException;
import com.example.exception.exception.ErrorCode;
import com.example.exception.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * CustomException 처리
     * CustomException 발생 시 정의된 ErrorCode에 따라 응답을 생성합니다.
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handlerCustomException(CustomException ex, HttpServletRequest request){
        // 1. CustomException에서 ErrorCode 객체를 추출
        ErrorCode errorCode = ex.getErrorCode();

        log.info("2. GlobalExceptionHandler의 CustomException 처리기 호출됨: {} " , errorCode.getCode());

        // 2. ErrorResponse 객체 생성
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                errorCode.getHttpStatus().value(),
                errorCode.getHttpStatus().getReasonPhrase(),
                errorCode.getCode(),
                errorCode.getMessage(),
                request.getRequestURI()
        );

        // 3. 적절한 HTTP 상태 코드와 ErrorResponse를 반환
        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

    /**
     * 처리되지 않은 모든 RuntimeException 처리 (최후의 보루)
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleAllRuntimeExceptions(RuntimeException ex, HttpServletRequest request) {

        log.info("2. GlobalExceptionHandler의 RuntimeException (일반 오류) 처리기 호출됨.");
        ex.printStackTrace(); // 서버 로그에 스택 트레이스를 기록

        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                errorCode.getHttpStatus().value(),
                errorCode.getHttpStatus().getReasonPhrase(),
                errorCode.getCode(),
                // 실제 오류 메시지 대신 일반적인 500 오류 메시지를 클라이언트에 전달
                "서버 처리 중 알 수 없는 오류가 발생했습니다. (자세한 내용은 서버 로그를 확인하세요.)",
                request.getRequestURI()
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
