package com.project.imdang.insight.service.domain.exception;

import com.project.imdang.domain.exception.ApplicationServiceException;
import com.project.imdang.domain.exception.ErrorCode;

public class InsightApplicationServiceException extends ApplicationServiceException {

    public InsightApplicationServiceException(ErrorCode errorCode) {
        // TODO - 수정
        super(errorCode.name());
    }

    public InsightApplicationServiceException(String message) {
        super(message);
    }

    public InsightApplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
