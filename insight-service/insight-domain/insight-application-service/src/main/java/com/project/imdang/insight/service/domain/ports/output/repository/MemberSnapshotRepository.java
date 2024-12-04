package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.insight.service.domain.entity.MemberSnapshot;

public interface MemberSnapshotRepository {
    MemberSnapshot save(MemberSnapshot memberSnapshot);
}
