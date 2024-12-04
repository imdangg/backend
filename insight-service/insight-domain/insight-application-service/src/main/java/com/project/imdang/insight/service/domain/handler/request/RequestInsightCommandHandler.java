package com.project.imdang.insight.service.domain.handler.request;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.insight.service.domain.RequestDomainService;
import com.project.imdang.insight.service.domain.dto.insight.request.RequestInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.request.RequestInsightResponse;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.entity.Request;
import com.project.imdang.insight.service.domain.event.InsightRequestedEvent;
import com.project.imdang.insight.service.domain.exception.InsightNotFoundException;
import com.project.imdang.insight.service.domain.exception.RequestDomainException;
import com.project.imdang.insight.service.domain.mapper.RequestDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class RequestInsightCommandHandler {

    private final RequestDomainService requestDomainService;
    private final RequestRepository requestRepository;
    private final InsightRepository insightRepository;
    private final RequestDataMapper requestDataMapper;

    @Transactional
    public RequestInsightResponse requestInsight(RequestInsightCommand requestInsightCommand) {

        // check
        UUID insightId = requestInsightCommand.getInsightId();
        checkInsight(insightId);

        Request request = requestDataMapper.requestInsightCommandToRequest(requestInsightCommand);
        InsightRequestedEvent insightRequestedEvent = requestDomainService.request(request);
        saveRequest(request);
        log.info("Insight[id: {}] is requested.", insightRequestedEvent.getInsight().getId().getValue());
        // TODO - publish event
        return requestDataMapper.requestToRequestInsightResponse(request);
    }

    private void checkInsight(UUID _insightId) {
        InsightId insightId = new InsightId(_insightId);
        Optional<Insight> insightResult = insightRepository.findById(insightId);
        if (insightResult.isEmpty()) {
            throw new InsightNotFoundException(insightId);
        }
    }

    private void saveRequest(Request request) {
        Request saved = requestRepository.save(request);
        if (saved == null) {
            String errorMessage = "Could not save request!";
            log.error(errorMessage);
            throw new RequestDomainException(errorMessage);
        }
        log.info("Request[id: {}] is saved.", saved.getId().getValue());
    }
}
