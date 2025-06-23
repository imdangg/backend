package com.project.imdang.member.domain.client;

import com.project.imdang.common.domain.valueobject.BaseId;
import com.project.imdang.common.domain.valueobject.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class MemberInfoResolverImpl implements MemberDataResolver {

    private final MemberFeignClient memberFeignClient;

    @Override
    public Optional<MemberData> resolve(MemberId memberId) {
        UUID id = memberId.getValue();
        MemberData memberData = memberFeignClient.getMemberData(id).getBody();
        return Optional.ofNullable(memberData);
    }

    @Override
    public List<MemberData> resolve(List<MemberId> memberIds) {
        List<UUID> ids = memberIds.stream()
                .map(BaseId::getValue)
                .toList();
        return memberFeignClient.listMemberData(ids).getBody();
    }
}
