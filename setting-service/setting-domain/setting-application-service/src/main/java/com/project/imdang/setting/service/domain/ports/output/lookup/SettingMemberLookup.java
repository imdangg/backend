package com.project.imdang.setting.service.domain.ports.output.lookup;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.setting.service.domain.valueobject.MemberInfo;

import java.util.Optional;

public interface SettingMemberLookup {
    Optional<MemberInfo> lookupByMemberId(MemberId memberId);
}
