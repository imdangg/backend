package com.project.imdang.insight.service.persistence.adapter;

import com.project.imdang.domain.valueobject.BaseId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.feign.MemberFeignClient;
import com.project.imdang.insight.service.domain.ports.output.lookup.InsightMemberLookup;
import com.project.imdang.insight.service.domain.valueobject.MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class InsightMemberLookupImpl implements InsightMemberLookup {

    private final MemberFeignClient memberFeignClient;

    @Override
    public Optional<MemberInfo> lookupByMemberId(MemberId _memberId) {
        UUID memberId = _memberId.getValue();
        try {
            return Optional.ofNullable(memberFeignClient.getMemberInfo(memberId))
                    .map(HttpEntity::getBody)
                    .map(memberInfoResponse -> MemberInfo.builder()
                            .memberId(memberInfoResponse.getMemberId())
                            .nickname(memberInfoResponse.getNickname())
                            .birthDate(memberInfoResponse.getBirthDate())
                            .gender(memberInfoResponse.getGender())
                            .deviceToken(memberInfoResponse.getDeviceToken())
                            .accusedCount(memberInfoResponse.getAccusedCount())
                            .exchangeCount(memberInfoResponse.getExchangeCount())
                            .insightCount(memberInfoResponse.getInsightCount())
                            .rejectedCount(memberInfoResponse.getRejectedCount())
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<MemberInfo> lookupByMemberIds(List<MemberId> _memberIds) {
        List<UUID> memberIds = _memberIds.stream()
                .map(BaseId::getValue)
                .toList();
        try {
            return memberFeignClient.listMemberInfo(memberIds).getBody().stream()
                    .map(memberInfoResponse -> MemberInfo.builder()
                            .nickname(memberInfoResponse.getNickname())
                            .birthDate(memberInfoResponse.getBirthDate())
                            .gender(memberInfoResponse.getGender())
                            .deviceToken(memberInfoResponse.getDeviceToken())
                            .accusedCount(memberInfoResponse.getAccusedCount())
                            .exchangeCount(memberInfoResponse.getExchangeCount())
                            .insightCount(memberInfoResponse.getInsightCount())
                            .rejectedCount(memberInfoResponse.getRejectedCount())
                            .build())
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
