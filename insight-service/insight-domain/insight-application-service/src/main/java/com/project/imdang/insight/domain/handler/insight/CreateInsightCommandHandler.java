package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.valueobject.File;
import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.insight.domain.InsightDomainService;
import com.project.imdang.insight.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.exception.InsightDomainException;
import com.project.imdang.insight.domain.helper.InsightHelper;
import com.project.imdang.insight.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.domain.ports.output.file.FileService;
import com.project.imdang.insight.domain.ports.output.publisher.InsightCreatedEventMessagePublisher;
import com.project.imdang.insight.messaging.message.InsightCreatedEventMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateInsightCommandHandler {

    private final InsightDomainService insightDomainService;
    private final InsightHelper insightHelper;
    private final InsightDataMapper insightDataMapper;

    private final InsightCreatedEventMessagePublisher insightCreatedEventMessagePublisher;
    private final FileService fileService;

    @Transactional
    public InsightId createInsight(CreateInsightCommand createInsightCommand) {
        Insight insight = insightDataMapper.createInsightCommandToInsight(createInsightCommand);

        String mainImage = uploadImage(createInsightCommand.getMainImage());
        Insight created = insightDomainService.createInsight(insight, mainImage);
        Insight savedInsight = insightHelper.save(created);
        log.info("Insight[id: {}] is created.", savedInsight.getId().getValue());

        // publish
        InsightCreatedEventMessage insightCreatedEventMessage
                = new InsightCreatedEventMessage(savedInsight.getId().getValue(), savedInsight.getMemberId().getValue());
        insightCreatedEventMessagePublisher.publish(insightCreatedEventMessage);
        return savedInsight.getId();
    }

    private String uploadImage(File mainImageFile) {
        String mainImage;
        try {
            mainImage = fileService.upload(mainImageFile);
        } catch (IOException e) {
            throw new InsightDomainException(e.getMessage());
        }
        return mainImage;
    }
}
