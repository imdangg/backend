package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.insight.service.domain.entity.MemberSnapshot;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberSnapshotRepositoryImpl implements MemberSnapshotRepository {

    @Override
    public MemberSnapshot save(MemberSnapshot memberSnapshot) {
        return null;
    }
}
