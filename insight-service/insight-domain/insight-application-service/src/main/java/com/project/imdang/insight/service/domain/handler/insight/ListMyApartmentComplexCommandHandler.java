package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListMyApartmentComplexCommandHandler {
    public List<ApartmentComplex> listMyApartmentComplex(UUID memberId) {
        return null;
    }
}
