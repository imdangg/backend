package com.project.imdang.setting.persistence.adapter;

import com.project.imdang.common.domain.valueobject.BaseId;
import com.project.imdang.common.domain.valueobject.TermsId;
import com.project.imdang.setting.domain.entity.Terms;
import com.project.imdang.setting.domain.ports.output.repository.TermsRepository;
import com.project.imdang.setting.persistence.mapper.TermsPersistenceMapper;
import com.project.imdang.setting.persistence.repository.TermsJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
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
    public List<Terms> findAllByIds(Set<TermsId> termsIds) {
        List<Long> ids = termsIds.stream()
                .map(BaseId::getValue).toList();
        return termsJpaRepository.findAllById(ids).stream()
                .map(termsPersistenceMapper::termsEntityToTerms)
                .collect(Collectors.toList());
    }
}
