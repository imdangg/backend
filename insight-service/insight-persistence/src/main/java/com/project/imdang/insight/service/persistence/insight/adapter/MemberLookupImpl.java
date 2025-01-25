package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.insight.service.domain.ports.output.lookup.MemberInfo;
import com.project.imdang.insight.service.domain.ports.output.lookup.MemberLookup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class MemberLookupImpl implements MemberLookup {

    @Override
    public Optional<MemberInfo> lookupByMemberId(UUID memberId) {
        return Optional.empty();
    }
}
