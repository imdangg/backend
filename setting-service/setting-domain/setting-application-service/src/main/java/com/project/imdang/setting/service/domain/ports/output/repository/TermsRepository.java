package com.project.imdang.setting.service.domain.ports.output.repository;

import com.project.imdang.setting.service.domain.entity.Terms;
import com.project.imdang.setting.service.domain.valueobject.TermsId;

import java.util.List;

public interface TermsRepository {
    List<Terms> findAll();
    List<Terms> findAllByIds(List<TermsId> termsIds);
}
