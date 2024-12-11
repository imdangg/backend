package com.project.imdang.setting.service.domain;

import com.project.imdang.setting.service.domain.dto.AgreeTermsCommand;
import com.project.imdang.setting.service.domain.dto.TermsResponse;
import com.project.imdang.setting.service.domain.handler.AgreeTermsCommandHandler;
import com.project.imdang.setting.service.domain.handler.ListTermsCommandHandler;
import com.project.imdang.setting.service.domain.ports.input.service.TermsApplicationService;
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
    public List<TermsResponse> listTerms() {
        return listTermsCommandHandler.listTerms();
    }

    @Override
    public void agreeTerms(AgreeTermsCommand agreeTermsCommand) {
        agreeTermsCommandHandler.agreeTerms(agreeTermsCommand);
    }
}
