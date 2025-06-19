package com.project.imdang.setting.domain.handler;

import com.project.imdang.setting.domain.dto.TermsResult;
import com.project.imdang.setting.domain.mapper.TermsDataMapper;
import com.project.imdang.setting.domain.ports.output.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Component
public class ListTermsCommandHandler {

    private final TermsRepository termsRepository;
    private final TermsDataMapper termsDataMapper;

    @Transactional(readOnly = true)
    public List<TermsResult> listTerms() {
        return termsRepository.findAll().stream()
                .map(termsDataMapper::termsToTermsResult)
                .collect(Collectors.toList());
    }
}
