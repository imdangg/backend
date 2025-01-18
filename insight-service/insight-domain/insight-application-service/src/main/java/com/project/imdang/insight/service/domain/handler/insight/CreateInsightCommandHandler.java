package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightResponse;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.entity.MemberSnapshot;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.handler.InsightHelper;
import com.project.imdang.insight.service.domain.handler.MemberSnapshotHelper;
import com.project.imdang.insight.service.domain.handler.SnapshotHelper;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateInsightCommandHandler {

    private final InsightDomainService insightDomainService;
    private final InsightHelper insightHelper;
    private final InsightDataMapper insightDataMapper;

    private final SnapshotHelper snapshotHelper;
    private final MemberSnapshotHelper memberSnapshotHelper;

    @Transactional
    public CreateInsightResponse createInsight(CreateInsightCommand createInsightCommand) {
        Insight insight = insightDataMapper.createInsightCommandToInsight(createInsightCommand);
        // TODO - CHECK : event

        // TODO : 이미지 처리
        String mainImage = uploadImage(createInsightCommand.getMainImage());
        Insight created = insightDomainService.createInsight(insight, mainImage);
        
        Insight saved = insightHelper.save(created);
        log.info("Insight[id: {}] is created.", saved.getId().getValue());

        // TODO : event
        // TODO : InsightCreatedEvent
        Snapshot snapshot = insightDomainService.captureInsight(saved);
        // TODO : snapshotDomainService(?)
        Snapshot savedSnapshot = snapshotHelper.save(snapshot);

        // memberSnapshot에 insert
        MemberSnapshot memberSnapshot = MemberSnapshot.builder()
                .memberId(new MemberId(createInsightCommand.getMemberId()))
                .snapshotId(new SnapshotId(savedSnapshot.getId().getValue()))
                .insightId(new InsightId(savedSnapshot.getInsightId().getValue()))
                .createdAt(created.getCreatedAt())
                .build();
        memberSnapshotHelper.save(memberSnapshot);

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
/*
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
        }*/

        return filename;
    }
}
