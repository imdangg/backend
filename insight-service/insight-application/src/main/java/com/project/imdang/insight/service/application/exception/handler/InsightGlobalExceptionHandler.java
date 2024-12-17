package com.project.imdang.insight.service.application.exception.handler;

import com.project.imdang.application.handler.ErrorDTO;
import com.project.imdang.insight.service.domain.exception.InsightDomainException;
import com.project.imdang.insight.service.domain.exception.InsightNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class InsightGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {InsightDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(InsightDomainException insightDomainException) {
        String message = insightDomainException.getMessage();
        log.error(message, insightDomainException);
        return ErrorDTO.of(HttpStatus.BAD_REQUEST, message);
    }

    @ResponseBody
    @ExceptionHandler(value = {InsightNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(InsightNotFoundException insightNotFoundException) {
        String message = insightNotFoundException.getMessage();
        log.error(message, insightNotFoundException);
        return ErrorDTO.of(HttpStatus.NOT_FOUND, message);
    }
}
