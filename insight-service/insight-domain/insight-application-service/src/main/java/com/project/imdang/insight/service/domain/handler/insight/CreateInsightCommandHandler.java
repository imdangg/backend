package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightResponse;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.entity.MemberSnapshot;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.exception.InsightDomainException;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateInsightCommandHandler {

    private final InsightDomainService insightDomainService;
    private final InsightRepository insightRepository;
    private final SnapshotRepository snapshotRepository;
    private final MemberSnapshotRepository memberSnapshotRepository;

    private final InsightDataMapper insightDataMapper;

    @Transactional
    public CreateInsightResponse createInsight(CreateInsightCommand createInsightCommand) {
        Insight insight = insightDataMapper.createInsightCommandToInsight(createInsightCommand);
        // TODO - CHECK : event

        // TODO : 이미지 처리
        String mainImage = uploadImage(createInsightCommand.getMainImage());
        Insight created = insightDomainService.createInsight(insight, mainImage);
        
        Insight saved = saveInsight(created);
        log.info("Insight[id: {}] is created.", saved.getId().getValue());

        // TODO : event
        // TODO : InsightCreatedEvent
        Snapshot snapshot = insightDomainService.captureInsight(saved);
        // TODO : snapshotDomainService(?)
        Snapshot savedSnapshot = saveSnapshot(snapshot);
        // memberSnapshot에 insert
        MemberSnapshot memberSnapshot = MemberSnapshot.builder()
                .memberId(new MemberId(createInsightCommand.getMemberId()))
                .snapshotId(new SnapshotId(savedSnapshot.getId().getValue()))
                .insightId(new InsightId(savedSnapshot.getInsightId().getValue()))
                .createdAt(created.getCreatedAt())
                .build();
        saveMemberSnapshot(memberSnapshot);

        return insightDataMapper.insightToCreateInsightResponse(insight);
    }

    private String uploadImage(MultipartFile image) {

        if (image.isEmpty()) {
            // TODO : 예외 처리
            throw new IllegalArgumentException("File is empty!");
        }

        String filename = image.getOriginalFilename();
//        String extension = filename.substring(filename.lastIndexOf("."));
//        String newFileName = UUID.randomUUID().toString() + extension;

        try {

            String uploadDir = "/uploads";
            // 업로드 디렉토리 확인 및 생성
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(filename);
            image.transferTo(filePath.toFile());

        } catch (IOException e) {
            // TODO : 예외 처리
            throw new RuntimeException(e);
        }

        return filename;
    }

    private Insight saveInsight(Insight insight) {
        Insight saved = insightRepository.save(insight);
        if (saved == null) {
            String errorMessage = "Could not save insight!";
            log.error(errorMessage);
            throw new InsightDomainException(errorMessage);
        }
        log.info("Insight[id: {}] is saved.", saved.getId().getValue());
        return saved;
    }

    private Snapshot saveSnapshot(Snapshot snapshot) {
        Snapshot saved = snapshotRepository.save(snapshot);
        if (saved == null) {
            String errorMessage = "Could not save snapshot!";
            log.error(errorMessage);
            throw new InsightDomainException(errorMessage);
        }
        log.info("Snapshot[id: {}] is saved.", saved.getId().getValue());
        return saved;
    }

    private void saveMemberSnapshot(MemberSnapshot memberSnapshot) {
        MemberSnapshot saved = memberSnapshotRepository.save(memberSnapshot);
        if (saved == null) {
            String errorMessage = "Could not save memberSnapshot!";
            log.error(errorMessage);
            throw new InsightDomainException(errorMessage);
        }
        log.info("memberSnapshot[id: {}] is saved.", saved.getId().getValue());
    }
}
