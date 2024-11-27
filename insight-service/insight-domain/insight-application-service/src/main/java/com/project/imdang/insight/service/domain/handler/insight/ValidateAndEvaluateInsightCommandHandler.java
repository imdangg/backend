package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.insight.evaluate.ValidateAndEvaluateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.evaluate.ValidateAndEvaluateInsightResponse;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class ValidateAndEvaluateInsightCommandHandler {

    private final InsightDomainService insightDomainService;
    private final InsightDataMapper insightDataMapper;

    @Transactional
    public ValidateAndEvaluateInsightResponse validateAndEvaluateInsight(ValidateAndEvaluateInsightCommand validateAndEvaluateInsightCommand) {
        Insight insight = insightDataMapper.validateAndEvaluateInsightCommandToInsight(validateAndEvaluateInsightCommand);
        Insight validated = insightDomainService.validateAndEvaluateInsight(insight);
        log.info("Insight[id: {}] is validated.", validated.getId().getValue());
        return insightDataMapper.insightToValidateAndEvaluateInsightCommand(validated);
    }
}
