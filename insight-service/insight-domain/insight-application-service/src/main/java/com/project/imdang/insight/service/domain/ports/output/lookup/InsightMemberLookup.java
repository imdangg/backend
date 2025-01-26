package com.project.imdang.insight.service.domain.ports.output.lookup;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.valueobject.MemberInfo;

import java.util.List;
import java.util.Optional;

public interface InsightMemberLookup {
    Optional<MemberInfo> lookupByMemberId(MemberId memberId);
    List<MemberInfo> lookupByMemberIds(List<MemberId> memberIds);
}
