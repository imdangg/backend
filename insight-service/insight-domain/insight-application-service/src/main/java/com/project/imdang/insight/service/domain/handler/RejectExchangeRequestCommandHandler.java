package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.insight.service.domain.dto.RejectExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.RejectExchangeRequestResponse;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RejectExchangeRequestCommandHandler {

    @Transactional
    public RejectExchangeRequestResponse rejectExchangeRequest(RejectExchangeRequestCommand rejectExchangeRequestCommand) {
        return null;
    }
}
