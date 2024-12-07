package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.MemberSnapshot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface MemberSnapshotRepository {
    Page<MemberSnapshot> findAllByMemberId(MemberId memberId, PageRequest pageRequest);
    MemberSnapshot save(MemberSnapshot memberSnapshot);
}
