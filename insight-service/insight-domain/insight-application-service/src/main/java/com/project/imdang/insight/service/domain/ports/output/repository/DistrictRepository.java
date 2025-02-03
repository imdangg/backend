package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.insight.service.domain.valueobject.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DistrictRepository {
    Page<District> findAllSiGunGuBySiDo(String siDo, PageRequest pageRequest);
    Page<District> findAllEupMyeonDongBySiDoAndSiGunGu(String siDo, String siGunGu, PageRequest pageRequest);
}
