package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.utils.PagingUtils;
import com.project.imdang.insight.service.domain.ports.output.repository.DistrictRepository;
import com.project.imdang.insight.service.domain.valueobject.District;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListDistrictCommandHandler {

    private final DistrictRepository districtRepository;

    @Transactional(readOnly = true)
    public Page<District> listDistrict(String siDo, String siGunGu, Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PagingUtils.getPageRequest(pageNumber, pageSize, null, null);
        if (siGunGu == null || siGunGu.isEmpty()) {
            return districtRepository.findAllSiGunGuBySiDo(siDo, pageRequest);
        }
        return districtRepository.findAllEupMyeonDongBySiDoAndSiGunGu(siDo, siGunGu, pageRequest);
    }
}
