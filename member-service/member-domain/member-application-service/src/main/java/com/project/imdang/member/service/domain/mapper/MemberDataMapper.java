package com.project.imdang.member.service.domain.mapper;

import com.project.imdang.member.service.domain.dto.DetailMyPageResponse;
import com.project.imdang.member.service.domain.dto.MemberResponse;
import com.project.imdang.member.service.domain.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberDataMapper {
    public DetailMyPageResponse memberToDetailMyPageResponse(Member member) {
        return DetailMyPageResponse.builder()
                .nickname(member.getNickname())
                .insightCount(member.getInsightCount())
                .requestCount(member.getExchangeCount())
                .build();
    }

    public MemberResponse memberToDetailMemberResponse(Member member){
        return MemberResponse.builder()
                .memberId(member.getId().getValue())
                .nickname(member.getNickname())
                .birthDate(member.getBirthDate())
                .gender(member.getGender().name())
                .deviceToken(member.getDeviceToken())
                .exchangeCount(member.getExchangeCount())
                .rejectedCount(member.getRejectedCount())
                .insightCount(member.getInsightCount())
                .build();
    }
}
