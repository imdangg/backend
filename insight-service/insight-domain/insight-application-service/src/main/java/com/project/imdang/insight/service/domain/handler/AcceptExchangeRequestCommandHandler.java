package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.insight.service.domain.dto.AcceptExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.AcceptExchangeRequestResponse;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AcceptExchangeRequestCommandHandler {

    @Transactional
    public AcceptExchangeRequestResponse acceptExchangeRequest(AcceptExchangeRequestCommand acceptExchangeRequestCommand) {
        return null;
    }
}
