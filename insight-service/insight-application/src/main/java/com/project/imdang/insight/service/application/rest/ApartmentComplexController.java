package com.project.imdang.insight.service.application.rest;

import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightQuery;
import com.project.imdang.insight.service.domain.valueobject.Address;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/apartment-complexes")
@RequiredArgsConstructor
@RestController
public class ApartmentComplexController {

    // 주소에 해당하는 apartmentComplexName 리스트 API
    @GetMapping
    public ResponseEntity<List<ApartmentComplex>> listByAddress(@ModelAttribute Address address) {

    }

    // 내가 다녀온 apartmentComplexName 리스트 API
    @GetMapping("/my-visited")
    public ResponseEntity<List<ApartmentComplex>> listByMyVisited(@ModelAttribute ListInsightQuery listInsightQuery) {

    }
}
