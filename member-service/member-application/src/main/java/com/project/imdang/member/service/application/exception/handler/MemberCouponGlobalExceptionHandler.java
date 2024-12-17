package com.project.imdang.member.service.application.exception.handler;

import com.project.imdang.application.handler.ErrorDTO;
import com.project.imdang.member.service.domain.exception.MemberCouponDomainException;
import com.project.imdang.member.service.domain.exception.MemberCouponNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class MemberCouponGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {MemberCouponDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(MemberCouponDomainException memberCouponDomainException) {
        String message = memberCouponDomainException.getMessage();
        log.error(message, memberCouponDomainException);
        return ErrorDTO.of(HttpStatus.BAD_REQUEST, message);
    }

    @ResponseBody
    @ExceptionHandler(value = {MemberCouponNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(MemberCouponNotFoundException memberCouponNotFoundException) {
        String message = memberCouponNotFoundException.getMessage();
        log.error(message, memberCouponNotFoundException);
        return ErrorDTO.of(HttpStatus.NOT_FOUND, message);
    }
}
