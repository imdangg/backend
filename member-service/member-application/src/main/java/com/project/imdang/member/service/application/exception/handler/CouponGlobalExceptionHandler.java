package com.project.imdang.member.service.application.exception.handler;

import com.project.imdang.application.handler.ErrorDTO;
import com.project.imdang.member.service.domain.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class CouponGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {CouponDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(CouponDomainException couponDomainException) {
        String message = couponDomainException.getMessage();
        log.error(message, couponDomainException);
        return ErrorDTO.of(HttpStatus.BAD_REQUEST, message);
    }


    @ResponseBody
    @ExceptionHandler(value = {CouponNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(CouponNotFoundException couponNotFoundException) {
        String message = couponNotFoundException.getMessage();
        log.error(message, couponNotFoundException);
        return ErrorDTO.of(HttpStatus.NOT_FOUND, message);
    }

}
