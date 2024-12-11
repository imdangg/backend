package com.project.imdang.setting.service.persistence.terms.adapter;

import com.project.imdang.domain.valueobject.BaseId;
import com.project.imdang.setting.service.domain.entity.Terms;
import com.project.imdang.setting.service.domain.ports.output.repository.TermsRepository;
import com.project.imdang.setting.service.domain.valueobject.TermsId;
import com.project.imdang.setting.service.persistence.terms.mapper.TermsPersistenceMapper;
import com.project.imdang.setting.service.persistence.terms.repository.TermsJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class TermsRepositoryImpl implements TermsRepository {

    private final TermsJpaRepository termsJpaRepository;
    private final TermsPersistenceMapper termsPersistenceMapper;

    @Override
    public List<Terms> findAll() {
        return termsJpaRepository.findAll().stream()
                .map(termsPersistenceMapper::termsEntityToTerms)
                .collect(Collectors.toList());
    }

    @Override
    public List<Terms> findAllByIds(List<TermsId> termsIds) {
        List<Long> ids = termsIds.stream()
                .map(BaseId::getValue).toList();
        return termsJpaRepository.findAllById(ids).stream()
                .map(termsPersistenceMapper::termsEntityToTerms)
                .collect(Collectors.toList());
    }
}
