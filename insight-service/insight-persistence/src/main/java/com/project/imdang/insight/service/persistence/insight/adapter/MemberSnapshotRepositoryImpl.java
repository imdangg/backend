package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.MemberSnapshot;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.District;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
import com.project.imdang.insight.service.persistence.insight.entity.MemberSnapshotEntity;
import com.project.imdang.insight.service.persistence.insight.mapper.MemberSnapshotPersistenceMapper;
import com.project.imdang.insight.service.persistence.insight.repository.MemberSnapshotJpaRepository;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MemberSnapshotRepositoryImpl implements MemberSnapshotRepository {

    private final MemberSnapshotJpaRepository memberSnapshotJpaRepository;
    private final MemberSnapshotPersistenceMapper memberSnapshotPersistenceMapper;

    @Override
    public Optional<MemberSnapshot> findByMemberIdAndInsightId(MemberId memberId, InsightId insightId) {
        return memberSnapshotJpaRepository.findByMemberIdAndInsightId(memberId.getValue(), memberId.getValue())
                .map(memberSnapshotPersistenceMapper::memberSnapshotEntityToMemberSnapshot);
    }

    @Override
    public Page<MemberSnapshot> findAllByMemberIdAndDistrict(MemberId memberId, District district, PageRequest pageRequest) {
        return memberSnapshotJpaRepository.findAllByMemberIdAndDistrict(
                memberId.getValue().toString(), district.getSiDo(), district.getSiGunGu(), district.getEupMyeonDong(), pageRequest)
                .map(memberSnapshotPersistenceMapper::memberSnapshotEntityToMemberSnapshot);
    }

    @Override
    public Page<MemberSnapshot> findAllByMemberIdAndApartmentComplex(MemberId memberId, ApartmentComplex apartmentComplex, PageRequest pageRequest) {
        return memberSnapshotJpaRepository.findAllByMemberIdAndApartmentComplexName(memberId.getValue().toString(), apartmentComplex.getName(), pageRequest)
                .map(memberSnapshotPersistenceMapper::memberSnapshotEntityToMemberSnapshot);
    }

    @Override
    public Page<MemberSnapshot> findAllByMemberIdAndDistrictAndSnapshotMemberId(MemberId memberId, District district, MemberId snapshotMemberId, PageRequest pageRequest) {
        return memberSnapshotJpaRepository.findAllByMemberIdAndDistrictAndSnapshotMemberId(
                memberId.getValue().toString(), district.getSiDo(), district.getSiGunGu(), district.getEupMyeonDong(), snapshotMemberId.getValue(), pageRequest)
                .map(memberSnapshotPersistenceMapper::memberSnapshotEntityToMemberSnapshot);
    }

    @Override
    public Page<MemberSnapshot> findAllByMemberIdAndApartmentComplexAndSnapshotMemberId(MemberId memberId, ApartmentComplex apartmentComplex, MemberId snapshotMemberId, PageRequest pageRequest) {
        return memberSnapshotJpaRepository.findAllByMemberIdAndApartmentComplexNameAndSnapshotMemberId(memberId.getValue().toString(), apartmentComplex.getName(), snapshotMemberId.getValue(), pageRequest)
                .map(memberSnapshotPersistenceMapper::memberSnapshotEntityToMemberSnapshot);
    }

    @Override
    public List<Object[]> findAllDistinctDistrictByMemberId(MemberId memberId) {
        return memberSnapshotJpaRepository.findAllDistinctDistrictByMemberId(memberId.getValue().toString());
    }

    @Override
    public List<Object[]> findAllDistinctApartmentComplexAndInsightCountByMemberIdAndDistrict(MemberId memberId, District district) {
        return memberSnapshotJpaRepository.findAllDistinctApartmentComplexAndInsightCountByMemberIdAndDistrict(memberId.getValue().toString(), district.getSiDo(), district.getSiGunGu(), district.getEupMyeonDong());
    }

    @Override
    public Long[] countAllByMemberIdAndDistrict(MemberId memberId, District district) {
        Tuple tuple = memberSnapshotJpaRepository.countAllByMemberIdAndDistrict(memberId.getValue().toString(), district.getSiDo(), district.getSiGunGu(), district.getEupMyeonDong());
        Long apartmentComplexCount = tuple.get("apartment_complex_count", Long.class);
        Long insightCount = tuple.get("insight_count", Long.class);
        return new Long[]{apartmentComplexCount, insightCount};
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
