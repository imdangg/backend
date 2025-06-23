package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.valueobject.File;
import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.InsightDomainService;
import com.project.imdang.insight.domain.dto.insight.update.UpdateInsightCommand;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.event.InsightUpdatedEvent;
import com.project.imdang.insight.domain.exception.InsightDomainException;
import com.project.imdang.insight.domain.helper.InsightHelper;
import com.project.imdang.insight.domain.ports.output.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class UpdateInsightCommandHandler {

    private final InsightDomainService insightDomainService;
    private final InsightHelper insightHelper;
    private final FileService fileService;

    @Transactional
    public InsightId updateInsight(UpdateInsightCommand updateInsightCommand) {

        InsightId insightId = updateInsightCommand.getInsightId();
        Insight insight = insightHelper.get(insightId);
        List<String> uploadImages = uploadImages(updateInsightCommand.getImages());

        // validation check
        MemberId updatedBy = updateInsightCommand.getMemberId();
//        List<String> mainImage = uploadImages(updateInsightCommand.getMainImage());
        InsightUpdatedEvent insightUpdatedEvent = insightDomainService.updateInsight(
                insight,
                updatedBy,
                uploadImages,
                updateInsightCommand.getTitle(),
                updateInsightCommand.getAddress(),
                updateInsightCommand.getApartmentComplex(),
                updateInsightCommand.getVisitAt(),
                updateInsightCommand.getVisitTimes(),
                updateInsightCommand.getVisitMethods(),
                updateInsightCommand.getAccess(),
                updateInsightCommand.getSummary(),
                updateInsightCommand.getInfra(),
                updateInsightCommand.getComplexEnvironment());
        Insight updated = insightUpdatedEvent.getInsight();
        log.info("Insight[id: {}] is updated.", updated.getId().getValue());
        Insight savedInsight = insightHelper.update(updated);
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

    private List<String> uploadImages(List<File> imageFiles) {
        List<String> uploadedImages = new ArrayList<>();
        for (File imageFile : imageFiles) {
            try {
                String uploadedUrl = fileService.upload(imageFile);
                uploadedImages.add(uploadedUrl);
            } catch (IOException e) {
                throw new InsightDomainException("이미지 업로드 실패: " + e.getMessage());
            }
        }
        return uploadedImages;
    }
}
