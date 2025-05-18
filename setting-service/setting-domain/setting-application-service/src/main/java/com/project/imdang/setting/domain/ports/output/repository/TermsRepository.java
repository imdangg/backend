package com.project.imdang.setting.domain.ports.output.repository;

import com.project.imdang.common.domain.valueobject.TermsId;
import com.project.imdang.setting.domain.entity.Terms;

import java.util.List;
import java.util.Set;

public interface TermsRepository {
    List<Terms> findAll();
    List<Terms> findAllByIds(Set<TermsId> termsIds);
}
