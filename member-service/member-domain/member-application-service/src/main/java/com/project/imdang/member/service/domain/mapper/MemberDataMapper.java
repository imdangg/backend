package com.project.imdang.member.service.domain.mapper;

import com.project.imdang.member.service.domain.dto.DetailMemberResponse;
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

    public DetailMemberResponse memberToDetailMemberResponse(Member member){
        return DetailMemberResponse.builder()
                .nickname(member.getNickname())
                .birthDate(member.getBirthDate())
                .gender(member.getGender())
                .deviceToken(member.getDeviceToken())
                .exchangeCount(member.getExchangeCount())
                .rejectedCount(member.getRejectedCount())
                .insightCount(member.getInsightCount())
                .build();
    }
}
