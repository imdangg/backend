package com.project.imdang.setting.service.domain.exception;

import com.project.imdang.domain.exception.ApplicationServiceException;
import com.project.imdang.domain.exception.ErrorCode;

public class SettingApplicationServiceException extends ApplicationServiceException {

    public SettingApplicationServiceException(ErrorCode errorCode) {
        // TODO - 수정
        super(errorCode.name());
    }

    public SettingApplicationServiceException(String message) {
        super(message);
    }

    public SettingApplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
