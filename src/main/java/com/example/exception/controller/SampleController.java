package com.example.exception.controller;

import com.example.exception.exception.CustomException;
import com.example.exception.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/exception")
public class SampleController {


    /**
     * CustomException 발생 테스트 (400 Bad Request 기대)
     */
    @GetMapping("/custom-exception")
    public ResponseEntity<String> triggerCustomException() {
        log.info("1. SampleController의 /api/exception/custom-exception 엔드포인트 호출됨.");

        // ErrorCode.INVALID_INPUT_VALUE를 사용하여 CustomException 발생
        throw new CustomException(ErrorCode.INVALID_INPUT_VALUE);
    }

    /**
     * 일반 RuntimeException 발생 테스트 (500 Internal Server Error 기대)
     */
    @GetMapping("/runtime-error")
    public ResponseEntity<String> triggerRuntimeException() {
        log.info("1. SampleController의 /api/exception/runtime-error 엔드포인트 호출됨.");

        // 처리되지 않은 NullPointerException 발생
        String s = null;
        return ResponseEntity.ok(s.toLowerCase()); // 의도적인 NullPointerException 발생
    }


}
