package com.project.imdang.insight.domain.helper;

import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.entity.InsightImage;
import com.project.imdang.insight.domain.exception.InsightDomainException;
import com.project.imdang.insight.domain.exception.InsightNotFoundException;
import com.project.imdang.insight.domain.ports.output.repository.InsightImageRepository;
import com.project.imdang.insight.domain.ports.output.repository.InsightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class InsightHelper {

    private final InsightRepository insightRepository;
    private final InsightImageRepository insightImageRepository;

    public Insight get(InsightId insightId) {
        Optional<Insight> insightResult = insightRepository.findById(insightId);
        if (insightResult.isEmpty()) {
            throw new InsightNotFoundException(insightId);
        }
        return insightResult.get();
    }

    public Insight save(Insight insight) {
        Insight saved = insightRepository.save(insight);
        List<InsightImage> imageSaved = insightImageRepository.saveAll(insight.getImages()); // 추가됨.
        if (saved == null || imageSaved == null) {
            String errorMessage = "Could not save insight!";
            log.error(errorMessage);
            throw new InsightDomainException(errorMessage);
        }
        log.info("Insight[id: {}] is saved.", saved.getId().getValue());
        return saved;
    }
}
