package com.project.imdang.setting.service.domain.ports.input.service;

import com.project.imdang.setting.service.domain.dto.AgreeTermsCommand;
import com.project.imdang.setting.service.domain.dto.TermsResponse;

import java.util.List;

public interface TermsApplicationService {

    List<TermsResponse> listTerms();
    void agreeTerms(AgreeTermsCommand agreeTermsCommand);
}
