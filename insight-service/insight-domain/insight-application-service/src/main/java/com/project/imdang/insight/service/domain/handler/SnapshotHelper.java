package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.exception.InsightDomainException;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SnapshotHelper {

    private final SnapshotRepository snapshotRepository;

    public Snapshot save(Snapshot snapshot) {
        Snapshot saved = snapshotRepository.save(snapshot);
        if (saved == null) {
            String errorMessage = "Could not save snapshot!";
            log.error(errorMessage);
            throw new InsightDomainException(errorMessage);
        }
        log.info("Snapshot[id: {}] is saved.", saved.getId().getValue());
        return saved;
    }
}
