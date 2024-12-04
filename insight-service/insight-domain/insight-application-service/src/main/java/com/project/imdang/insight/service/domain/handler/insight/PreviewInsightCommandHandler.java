package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.insight.service.domain.dto.insight.preview.PreviewInsightQuery;
import com.project.imdang.insight.service.domain.dto.insight.preview.PreviewInsightResponse;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.exception.InsightNotFoundException;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class PreviewInsightCommandHandler {

    private final InsightRepository insightRepository;
    private final InsightDataMapper insightDataMapper;

    @Transactional(readOnly = true)
    public PreviewInsightResponse previewInsight(PreviewInsightQuery previewInsightQuery) {
        InsightId insightId = new InsightId(previewInsightQuery.getInsightId());
        Optional<Insight> insightResult = insightRepository.findById(insightId);
        if (insightResult.isEmpty()) {
            throw new InsightNotFoundException(insightId);
        }
        return insightDataMapper.insightToPreviewInsightResponse(insightResult.get());
    }
}
