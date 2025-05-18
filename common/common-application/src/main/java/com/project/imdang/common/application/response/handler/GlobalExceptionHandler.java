package com.project.imdang.common.application.response.handler;

import com.project.imdang.common.application.response.ErrorResponse;
import com.project.imdang.common.application.response.code.ErrorCode;
import com.project.imdang.common.domain.exception.DomainAlreadyExistException;
import com.project.imdang.common.domain.exception.DomainException;
import com.project.imdang.common.domain.exception.DomainNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(DomainException ex) {
        ErrorCode invalidRequest = ErrorCode.INVALID_REQUEST;
        ErrorResponse errorResponse = ErrorResponse.of(invalidRequest, ex.getMessage());
        return new ResponseEntity<>(errorResponse, invalidRequest.getHttpStatus());
    }

    @ExceptionHandler(DomainNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDomainNotFoundException(DomainNotFoundException ex) {
        ErrorCode notFound = ErrorCode.NOT_FOUND;
        ErrorResponse errorResponse = ErrorResponse.of(notFound, ex.getMessage());
        return new ResponseEntity<>(errorResponse, notFound.getHttpStatus());
    }

    @ExceptionHandler(DomainAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleDomainAlreadyExistException(DomainAlreadyExistException ex) {
        ErrorCode alreadyExist = ErrorCode.ALREADY_EXIST;
        ErrorResponse errorResponse = ErrorResponse.of(alreadyExist, ex.getMessage());
        return new ResponseEntity<>(errorResponse, alreadyExist.getHttpStatus());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {

        ErrorCode invalidRequest = ErrorCode.INVALID_REQUEST;

        String message;
        if (ex instanceof ConstraintViolationException) {
            message = extractViolationsFromException((ConstraintViolationException) ex);
        } else {
            message = ex.getMessage();
        }

//        log.error(message, ex);
        ErrorResponse errorResponse = ErrorResponse.of(invalidRequest, message);
        return new ResponseEntity<>(errorResponse, invalidRequest.getHttpStatus());
    }

    private String extractViolationsFromException(ConstraintViolationException validationException) {
        return validationException.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("--"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorCode invalidRequest = ErrorCode.INVALID_REQUEST;
        String message = "Please input " + ex.getParameter().getParameterName();
        ErrorResponse errorResponse = ErrorResponse.of(invalidRequest, message);
        return new ResponseEntity<>(errorResponse, invalidRequest.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorCode internalServerError = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(internalServerError, ex.getMessage());
        return new ResponseEntity<>(errorResponse, internalServerError.getHttpStatus());
    }
}
