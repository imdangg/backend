package com.project.imdang.insight.persistence.adapter;

import com.project.imdang.insight.domain.ports.output.repository.DistrictRepository;
import com.project.imdang.common.domain.valueobject.District;
import com.project.imdang.insight.persistence.repository.DistrictJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DistrictRepositoryImpl implements DistrictRepository {

    private final DistrictJpaRepository districtJpaRepository;

    @Override
    public Page<District> findAllSiGunGuBySiDo(String siDo, PageRequest pageRequest) {
        Page<Object[]> siGunGus = districtJpaRepository.findAllSiGunGuBySiDo(siDo, pageRequest);
        return siGunGus.map(result -> District.builder()
                        .code((String) result[0])
                        .siDo((String) result[1])
                        .siGunGu((String) result[2])
                        .build());
    }

    @Override
    public Page<District> findAllEupMyeonDongBySiDoAndSiGunGu(String siDo, String siGunGu, PageRequest pageRequest) {
        return districtJpaRepository.findAllEupMyeonDongBySiDoAndSiGunGu(siDo, siGunGu, pageRequest)
                .map(result -> District.builder()
                        .code((String) result[0])
                        .siDo((String) result[1])
                        .siGunGu((String) result[2])
                        .eupMyeonDong((String) result[3])
                        .build());
    }
}
