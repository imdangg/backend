package com.project.imdang.setting.service.persistence.adapter;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.feign.MemberFeignClient;
import com.project.imdang.feign.MemberInfoResponse;
import com.project.imdang.setting.service.domain.ports.output.lookup.SettingMemberLookup;
import com.project.imdang.setting.service.domain.valueobject.MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class SettingMemberLookupImpl implements SettingMemberLookup {

    private final MemberFeignClient memberFeignClient;

    @Override
    public Optional<MemberInfo> lookupByMemberId(MemberId _memberId) {
        UUID memberId = _memberId.getValue();
        try {
            ResponseEntity<MemberInfoResponse> responseEntity = memberFeignClient.getMemberInfo(memberId);
            return Optional.ofNullable(responseEntity)
                    .map(HttpEntity::getBody)
                    .map(memberInfoResponse -> MemberInfo.builder()
                            .memberId(new MemberId(memberInfoResponse.getMemberId()))
                            .deviceToken(memberInfoResponse.getDeviceToken())
                            .build());
        } catch (Exception e) {
            // TODO - 로그
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
