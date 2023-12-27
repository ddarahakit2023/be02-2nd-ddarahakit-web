package com.ddarahakit.web.exception;

import com.ddarahakit.web.exception.exception.MemberException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionAdvise extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MemberException.class)
    public ResponseEntity handleMemberException(MemberException e) {
        System.out.println(e.getMessage());
        return makeResponseEntity(e.getErrorCode());
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return makeResponseEntity(ErrorCode.INVALID_INPUT);
    }

    private ResponseEntity makeResponseEntity(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getStatus()).body(
                ErrorResponse.builder()
                        .code(errorCode.name())
                        .message(errorCode.getMessage())
                        .build()
        );
    }

    

}
