package com.project.imdang.setting.service.domain;

import com.project.imdang.setting.service.domain.ports.input.service.TermsApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@RequiredArgsConstructor
@Service
public class TermsApplicationServiceImpl implements TermsApplicationService {

    @Override
    public void agreeTerms() {

    }
}
