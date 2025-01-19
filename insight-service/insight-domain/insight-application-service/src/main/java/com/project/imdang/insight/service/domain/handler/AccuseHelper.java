package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.Accuse;
import com.project.imdang.insight.service.domain.exception.InsightDomainException;
import com.project.imdang.insight.service.domain.ports.output.repository.AccuseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccuseHelper {

    private final AccuseRepository accuseRepository;

    public Accuse save(Accuse accuse) {
        Accuse saved = accuseRepository.save(accuse);
        if (saved == null) {
            String errorMessage = "Could not save accuse!";
            log.error(errorMessage);
            // TODO - CHECK : AccuseDomainException
            throw new InsightDomainException(errorMessage);
        }
        log.info("Accuse[id: {}] is saved.", saved.getId().getValue());
        return saved;
    }

    public Optional<Accuse> getByAccuseMemberIdAndAccusedInsightId(MemberId accuseMemberId, InsightId accusedInsightId) {
        return accuseRepository.findByAccuseMemberIdAndAccusedInsightId(accuseMemberId, accusedInsightId);
    }
}
