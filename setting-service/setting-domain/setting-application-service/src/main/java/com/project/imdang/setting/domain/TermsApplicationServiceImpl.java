package com.project.imdang.setting.domain;

import com.project.imdang.setting.domain.dto.AgreeTermsCommand;
import com.project.imdang.setting.domain.dto.TermsResult;
import com.project.imdang.setting.domain.handler.AgreeTermsCommandHandler;
import com.project.imdang.setting.domain.handler.ListTermsCommandHandler;
import com.project.imdang.setting.domain.ports.input.service.TermsApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@RequiredArgsConstructor
@Service
public class TermsApplicationServiceImpl implements TermsApplicationService {

    private final ListTermsCommandHandler listTermsCommandHandler;
    private final AgreeTermsCommandHandler agreeTermsCommandHandler;

    @Override
    public List<TermsResult> listTerms() {
        return listTermsCommandHandler.listTerms();
    }

    @Override
    public Boolean agreeTerms(AgreeTermsCommand agreeTermsCommand) {
        return agreeTermsCommandHandler.agreeTerms(agreeTermsCommand);
    }
}
