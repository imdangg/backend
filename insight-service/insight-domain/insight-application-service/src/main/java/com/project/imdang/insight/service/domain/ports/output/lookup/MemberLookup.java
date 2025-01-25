package com.project.imdang.insight.service.domain.ports.output.lookup;

import java.util.Optional;
import java.util.UUID;

public interface MemberLookup {
    Optional<MemberInfo> lookupByMemberId(UUID memberId);
}
