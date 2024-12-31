package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.MemberSnapshot;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
import com.project.imdang.insight.service.persistence.insight.entity.MemberSnapshotEntity;
import com.project.imdang.insight.service.persistence.insight.mapper.MemberSnapshotPersistenceMapper;
import com.project.imdang.insight.service.persistence.insight.repository.MemberSnapshotJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public Page<MemberSnapshot> findAllByMemberIdAndApartmentComplex(MemberId memberId, ApartmentComplex apartmentComplex, PageRequest pageRequest) {
        return memberSnapshotJpaRepository.findAllByMemberIdAndApartmentComplex(memberId.getValue(), apartmentComplex.getKey(), pageRequest)
                .map(memberSnapshotPersistenceMapper::memberSnapshotEntityToMemberSnapshot);
    }

    @Override
    public Page<MemberSnapshot> findAllByMemberIdAndSnapshotMemberId(MemberId memberId, MemberId snapshotMemberId, PageRequest pageRequest) {
        return memberSnapshotJpaRepository.findAllByMemberIdAndSnapshotMemberId(memberId.getValue(), snapshotMemberId.getValue(), pageRequest)
                .map(memberSnapshotPersistenceMapper::memberSnapshotEntityToMemberSnapshot);
    }

    @Override
    public Page<MemberSnapshot> findAllByMemberIdAndApartmentComplexAndSnapshotMemberId(MemberId memberId, ApartmentComplex apartmentComplex, MemberId snapshotMemberId, PageRequest pageRequest) {
        return memberSnapshotJpaRepository.findAllByMemberIdAndApartmentComplexAndSnapshotMemberId(memberId.getValue(), apartmentComplex.getKey(), snapshotMemberId.getValue(), pageRequest)
                .map(memberSnapshotPersistenceMapper::memberSnapshotEntityToMemberSnapshot);
    }

    @Override
    public List<ApartmentComplex> findAllDistinctApartmentComplexByMemberId(MemberId memberId) {
        return memberSnapshotJpaRepository.findAllDistinctApartmentComplexByMemberId(memberId.getValue());
    }

    @Override
    public MemberSnapshot save(MemberSnapshot memberSnapshot) {
        MemberSnapshotEntity memberSnapshotEntity = memberSnapshotPersistenceMapper.memberSnapshotToMemberSnapshotEntity(memberSnapshot);
        MemberSnapshotEntity saved = memberSnapshotJpaRepository.save(memberSnapshotEntity);
        return memberSnapshotPersistenceMapper.memberSnapshotEntityToMemberSnapshot(saved);
    }

    @Override
    public void updateSnapshotIdByMemberIdAndInsightId(SnapshotId snapshotId, MemberId memberId, InsightId insightId) {
        MemberSnapshotEntity memberSnapshotEntity = memberSnapshotJpaRepository.findByMemberIdAndInsightId(memberId.getValue(), insightId.getValue())
                // TODO - 예외 처리
                .orElseThrow(RuntimeException::new);
        memberSnapshotEntity.setSnapshotId(snapshotId.getValue());
    }

    @Override
    public void deleteByMemberIdAndInsightId(MemberId memberId, InsightId insightId) {
        memberSnapshotJpaRepository.deleteByMemberIdAndInsightId(memberId.getValue(), insightId.getValue());
    }
}
