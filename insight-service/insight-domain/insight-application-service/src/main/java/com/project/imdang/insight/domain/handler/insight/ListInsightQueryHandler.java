package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.utils.PagingUtils;
import com.project.imdang.common.domain.valueobject.*;
import com.project.imdang.insight.domain.dto.insight.list.*;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.entity.InsightImage;
import com.project.imdang.insight.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.domain.ports.output.repository.InsightImageRepository;
import com.project.imdang.insight.domain.ports.output.repository.InsightRepository;
import com.project.imdang.member.domain.client.MemberData;
import com.project.imdang.member.domain.client.MemberDataResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Component
public class ListInsightQueryHandler {

    private final InsightRepository insightRepository;
    private final InsightImageRepository insightImageRepository;
    private final InsightDataMapper insightDataMapper;

    private final MemberDataResolver memberResolver;

    public Page<InsightResult> list(ListInsightQuery listInsightQuery) {
        PageRequest pageRequest = PagingUtils.getPageRequest(
                listInsightQuery.getPageNumber(), listInsightQuery.getPageSize(), listInsightQuery.getDirection(), listInsightQuery.getProperties());

        Page<Insight> paged = insightRepository.findAll(pageRequest);
        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(paged.getContent());
        return paged.map(insight -> {
            String memberNickname = memberNicknameMap.get(insight.getMemberId());
            return insightDataMapper.insightToInsightResponse(insight, memberNickname);
        });
    }

    public Page<InsightResult> listWithImages(ListInsightQuery listInsightQuery) {
        PageRequest pageRequest = PagingUtils.getPageRequest(
                listInsightQuery.getPageNumber(), listInsightQuery.getPageSize(),
                listInsightQuery.getDirection(), listInsightQuery.getProperties());

        Page<Insight> paged = insightRepository.findAll(pageRequest);

        List<Insight> insights = paged.getContent();
        List<InsightId> insightIds = insights.stream()
                .map(Insight::getId)
                .toList();

        // 이미지 조회 및 맵핑
        List<InsightImage> images = insightImageRepository.findByInsightIdIn(insightIds);
        Map<InsightId, List<InsightImage>> imageMap = images.stream()
                .collect(Collectors.groupingBy(InsightImage::getInsightId));

        // 닉네임 조회
        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(insights);

        return paged.map(insight -> {
            List<InsightImage> matchedImages = imageMap.getOrDefault(insight.getId(), List.of());
            String nickname = memberNicknameMap.get(insight.getMemberId());
            return insightDataMapper.insightToInsightResponse(insight, nickname, matchedImages);
        });
    }

    public Page<InsightResult> listByDate(ListInsightByDateQuery listInsightByDateQuery) {
        PageRequest pageRequest = PagingUtils.getPageRequest(
                listInsightByDateQuery.getPageNumber(), listInsightByDateQuery.getPageSize(), listInsightByDateQuery.getDirection(), listInsightByDateQuery.getProperties());

        Page<Insight> paged = insightRepository.findAllByDate(listInsightByDateQuery.getDate(), pageRequest);
        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(paged.getContent());
        return paged.map(insight -> {
            String memberNickname = memberNicknameMap.get(insight.getMemberId());
            return insightDataMapper.insightToInsightResponse(insight, memberNickname);
        });
    }

    public Page<InsightResult> listWithImagesByApartmentComplex(ListInsightByApartmentComplexQuery listInsightByApartmentComplexQuery) {

        PageRequest pageRequest = PagingUtils.getPageRequest(
                listInsightByApartmentComplexQuery.getPageNumber(), listInsightByApartmentComplexQuery.getPageSize(), listInsightByApartmentComplexQuery.getDirection(), listInsightByApartmentComplexQuery.getProperties());
        ApartmentComplex apartmentComplex = ApartmentComplex.builder()
                .name(listInsightByApartmentComplexQuery.getApartmentComplexName())
                .build();

        Page<Insight> paged = insightRepository.findAllByApartmentComplex(apartmentComplex, pageRequest);
        List<Insight> insights = paged.getContent();
        List<InsightId> insightIds = insights.stream()
                .map(Insight::getId)
                .toList();

        // 이미지 조회 및 맵핑
        List<InsightImage> images = insightImageRepository.findByInsightIdIn(insightIds);
        Map<InsightId, List<InsightImage>> imageMap = images.stream()
                .collect(Collectors.groupingBy(InsightImage::getInsightId));

        // 닉네임 조회
        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(insights);

        return paged.map(insight -> {
            List<InsightImage> matchedImages = imageMap.getOrDefault(insight.getId(), List.of());
            String nickname = memberNicknameMap.get(insight.getMemberId());
            return insightDataMapper.insightToInsightResponse(insight, nickname, matchedImages);
        });
    }

    public Page<InsightResult> listByApartmentComplex(ListInsightByApartmentComplexQuery listInsightByApartmentComplexQuery) {
        PageRequest pageRequest = PagingUtils.getPageRequest(
                listInsightByApartmentComplexQuery.getPageNumber(), listInsightByApartmentComplexQuery.getPageSize(), listInsightByApartmentComplexQuery.getDirection(), listInsightByApartmentComplexQuery.getProperties());
        ApartmentComplex apartmentComplex = ApartmentComplex.builder()
                .name(listInsightByApartmentComplexQuery.getApartmentComplexName())
                .build();

        Page<Insight> paged = insightRepository.findAllByApartmentComplex(apartmentComplex, pageRequest);
        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(paged.getContent());
        return paged.map(insight -> {
            String memberNickname = memberNicknameMap.get(insight.getMemberId());
            return insightDataMapper.insightToInsightResponse(insight, memberNickname);
        });
    }

    public Page<InsightResult> listInsightWithImagesByAddress(ListInsightByAddressQuery listInsightByAddressQuery) {

        PageRequest pageRequest = PagingUtils.getPageRequest(
                listInsightByAddressQuery.getPageNumber(), listInsightByAddressQuery.getPageSize(), listInsightByAddressQuery.getDirection(), listInsightByAddressQuery.getProperties());

        Address address = Address.builder()
                .siDo(listInsightByAddressQuery.getSiDo())
                .siGunGu(listInsightByAddressQuery.getSiGunGu())
                .eupMyeonDong(listInsightByAddressQuery.getEupMyeonDong())
                .build();

        Page<Insight> paged = insightRepository.findAllByAddress(address, pageRequest);
        List<Insight> insights = paged.getContent();
        List<InsightId> insightIds = insights.stream()
                .map(Insight::getId)
                .toList();

        // 이미지 조회 및 맵핑
        List<InsightImage> images = insightImageRepository.findByInsightIdIn(insightIds);
        Map<InsightId, List<InsightImage>> imageMap = images.stream()
                .collect(Collectors.groupingBy(InsightImage::getInsightId));

        // 닉네임 조회
        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(insights);

        return paged.map(insight -> {
            List<InsightImage> matchedImages = imageMap.getOrDefault(insight.getId(), List.of());
            String nickname = memberNicknameMap.get(insight.getMemberId());
            return insightDataMapper.insightToInsightResponse(insight, nickname, matchedImages);
        });
    }

    public Page<InsightResult> listByDistrict(ListInsightByDistrictQuery listInsightByDistrictQuery) {
        PageRequest pageRequest = PagingUtils.getPageRequest(
                listInsightByDistrictQuery.getPageNumber(), listInsightByDistrictQuery.getPageSize(), listInsightByDistrictQuery.getDirection(), listInsightByDistrictQuery.getProperties());

        District district = District.builder()
                .siDo(listInsightByDistrictQuery.getSiDo())
                .siGunGu(listInsightByDistrictQuery.getSiGunGu())
                .eupMyeonDong(listInsightByDistrictQuery.getEupMyeonDong())
                .build();
        Page<Insight> paged = insightRepository.findAllByDistrict(district, pageRequest);
        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(paged.getContent());
        return paged.map(insight -> {
            String memberNickname = memberNicknameMap.get(insight.getMemberId());
            return insightDataMapper.insightToInsightResponse(insight, memberNickname);
        });
    }

    private List<InsightResult> getInsightResponses(List<Insight> insights) {
        Map<MemberId, String> memberNicknameMap = getMemberNicknameMap(insights);
        return insights.stream().map(insight -> {
            String memberNickname = memberNicknameMap.get(insight.getMemberId());
            return insightDataMapper.insightToInsightResponse(insight, memberNickname);
        }).toList();
    }

    private Map<MemberId, String> getMemberNicknameMap(List<Insight> insights) {
        List<MemberId> memberIds = insights.stream()
                .map(Insight::getMemberId)
                .toList();

        List<MemberData> resolved = memberResolver.resolve(memberIds);
        if (resolved == null) {
            log.warn("memberResolver.resolve() returned null for memberIds: {}", memberIds);
            return Collections.emptyMap(); // null 반환에 대한 방어
        }

        return resolved.stream()
                .collect(Collectors.toMap(
                        memberData -> new MemberId(memberData.getMemberId()),
                        MemberData::getNickname));

//        return memberResolver.resolve(memberIds).stream()
//                .collect(Collectors.toMap(memberData -> new MemberId(memberData.getMemberId()), MemberData::getNickname));
    }
}
