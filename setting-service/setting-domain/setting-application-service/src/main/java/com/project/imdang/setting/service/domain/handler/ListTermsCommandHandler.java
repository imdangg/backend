package com.project.imdang.setting.service.domain.handler;

import com.project.imdang.setting.service.domain.dto.TermsResponse;
import com.project.imdang.setting.service.domain.mapper.TermsDataMapper;
import com.project.imdang.setting.service.domain.ports.output.repository.TermsRepository;
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
    public List<TermsResponse> listTerms() {
        return termsRepository.findAll().stream()
                .map(termsDataMapper::termsToTermsResponse)
                .collect(Collectors.toList());
    }
}
