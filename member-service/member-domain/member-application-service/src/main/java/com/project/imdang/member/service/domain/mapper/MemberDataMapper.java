package com.project.imdang.member.service.domain.mapper;

import com.project.imdang.member.service.domain.dto.MemberInfoResponse;
import com.project.imdang.member.service.domain.dto.DetailMyPageResponse;
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

    public MemberInfoResponse memberToDetailMemberResponse(Member member){
        return MemberInfoResponse.builder()
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
