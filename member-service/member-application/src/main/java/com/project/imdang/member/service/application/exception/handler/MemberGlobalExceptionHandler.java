package com.project.imdang.member.service.application.exception.handler;

import com.project.imdang.application.handler.ErrorDTO;
import com.project.imdang.member.service.domain.exception.MemberCouponDomainException;
import com.project.imdang.member.service.domain.exception.MemberCouponNotFoundException;
import com.project.imdang.member.service.domain.exception.MemberDomainException;
import com.project.imdang.member.service.domain.exception.MemberNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class MemberGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {MemberDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(MemberDomainException memberDomainException) {
        String message = memberDomainException.getMessage();
        log.error(message, memberDomainException);
        return ErrorDTO.of(HttpStatus.BAD_REQUEST, message);
    }

    @ResponseBody
    @ExceptionHandler(value = {MemberCouponNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(MemberNotFoundException memberNotFoundException) {
        String message = memberNotFoundException.getMessage();
        log.error(message, memberNotFoundException);
        return ErrorDTO.of(HttpStatus.NOT_FOUND, message);
    }
}
