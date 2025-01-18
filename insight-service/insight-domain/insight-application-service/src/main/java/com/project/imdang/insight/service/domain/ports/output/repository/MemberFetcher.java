package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.domain.fetch.FetchMemberResponse;
import com.project.imdang.domain.valueobject.MemberId;

public interface MemberFetcher {
    FetchMemberResponse fetchByMemberId(MemberId memberId);
}
