package com.project.imdang.setting.domain.ports.input.service;

import com.project.imdang.setting.domain.dto.AgreeTermsCommand;
import com.project.imdang.setting.domain.dto.TermsResult;

import java.util.List;

public interface TermsApplicationService {

    List<TermsResult> listTerms();
    Boolean agreeTerms(AgreeTermsCommand agreeTermsCommand);
}
