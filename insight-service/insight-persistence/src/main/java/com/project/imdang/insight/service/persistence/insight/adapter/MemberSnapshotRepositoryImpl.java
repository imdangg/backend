package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.MemberSnapshot;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import com.project.imdang.insight.service.persistence.insight.entity.MemberSnapshotEntity;
import com.project.imdang.insight.service.persistence.insight.mapper.MemberSnapshotPersistenceMapper;
import com.project.imdang.insight.service.persistence.insight.repository.MemberSnapshotJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberSnapshotRepositoryImpl implements MemberSnapshotRepository {

    private final MemberSnapshotJpaRepository memberSnapshotJpaRepository;
    private final MemberSnapshotPersistenceMapper memberSnapshotPersistenceMapper;

    @Override
    public Page<MemberSnapshot> findAllByMemberId(MemberId memberId, PageRequest pageRequest) {
        return memberSnapshotJpaRepository.findAllByMemberId(memberId.getValue(), pageRequest)
                .map(memberSnapshotPersistenceMapper::memberSnapshotEntityToMemberSnapshot);
    }

    @Override
    public MemberSnapshot save(MemberSnapshot memberSnapshot) {
        MemberSnapshotEntity memberSnapshotEntity = memberSnapshotPersistenceMapper.memberSnapshotToMemberSnapshotEntity(memberSnapshot);
        MemberSnapshotEntity saved = memberSnapshotJpaRepository.save(memberSnapshotEntity);
        return memberSnapshotPersistenceMapper.memberSnapshotEntityToMemberSnapshot(saved);
    }
}
