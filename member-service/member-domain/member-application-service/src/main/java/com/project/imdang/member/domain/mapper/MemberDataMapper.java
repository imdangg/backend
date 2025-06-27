package com.project.imdang.member.domain.mapper;

import com.project.imdang.member.domain.dto.member.MemberResult;
import com.project.imdang.member.domain.dto.member.MyPageInfoResult;
import com.project.imdang.member.domain.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberDataMapper {

    public MyPageInfoResult memberToDetailMyPageResponse(Member member) {
        return MyPageInfoResult.builder()
                .nickname(member.getNickname())
                // TODO
                .insightCount(null)
                .build();
    }

    public MemberResult memberToDetailMemberResponse(Member member){
        return MemberResult.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .birthDate(member.getBirthDate())
                .gender(member.getGender() != null ? member.getGender().name() : null)
                .deviceToken(member.getDeviceToken())
                // TODO
                .insightCount(null)
                .build();
    }
}
