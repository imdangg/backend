package com.project.imdang.setting.service.domain.handler;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.setting.service.domain.TermsDomainService;
import com.project.imdang.setting.service.domain.dto.AgreeTermsCommand;
import com.project.imdang.setting.service.domain.entity.Terms;
import com.project.imdang.setting.service.domain.entity.TermsAgreement;
import com.project.imdang.setting.service.domain.exception.NotificationDomainException;
import com.project.imdang.setting.service.domain.ports.output.repository.TermsAgreementRepository;
import com.project.imdang.setting.service.domain.ports.output.repository.TermsRepository;
import com.project.imdang.setting.service.domain.valueobject.TermsId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class AgreeTermsCommandHandler {

    private final TermsDomainService termsDomainService;
    private final TermsRepository termsRepository;
    private final TermsAgreementRepository termsAgreementRepository;
//    private final TermsDataMapper termsDataMapper;

    @Transactional
    public void agreeTerms(AgreeTermsCommand agreeTermsCommand) {
        List<TermsId> termsIds = agreeTermsCommand.getTermsIds().stream()
                .map(TermsId::new)
                .toList();
        MemberId memberId = new MemberId(agreeTermsCommand.getMemberId());
        List<Terms> terms = termsRepository.findAllByIds(termsIds);
        // TODO - 일괄 동의 or ASYNC
        terms.forEach(agreed -> {
            TermsAgreement termsAgreement = termsDomainService.agreeTerms(agreed, memberId);
            // TODO - CHECK : TermsAgreement 도메인을 꼭 생성해야 하는가?
            save(termsAgreement);
            log.info("Terms[id: {}] is agreed by Member[id: {}].", termsAgreement.getTermsId().getValue(), termsAgreement.getMemberId().getValue());
        });
    }

    private void save(TermsAgreement termsAgreement) {
        TermsAgreement saved = termsAgreementRepository.save(termsAgreement);
        if (saved == null) {
            String errorMessage = "Could not save termsAgreement!";
            log.error(errorMessage);
            throw new NotificationDomainException(errorMessage);
        }
        log.info("TermsAgreement[id: {}] is saved.", saved.getId().getValue());
    }
}
