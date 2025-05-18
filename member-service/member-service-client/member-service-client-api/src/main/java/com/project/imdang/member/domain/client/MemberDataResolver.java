package com.project.imdang.member.domain.client;

import com.project.imdang.common.domain.valueobject.MemberId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberDataResolver {
    Optional<MemberData> resolve(MemberId memberId);
    List<MemberData> resolve(List<MemberId> memberIds);
}
