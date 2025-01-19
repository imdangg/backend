package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.MemberSnapshot;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import com.project.imdang.insight.service.domain.valueobject.Address;
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
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Page<MemberSnapshot> findAllByMemberIdAndAddress(MemberId memberId, Address address, PageRequest pageRequest) {
        return memberSnapshotJpaRepository.findAllByMemberIdAndAddress(
                memberId.getValue().toString(), address.getSiDo(), address.getSiGunGu(), address.getEupMyeonDong(), address.getRoadName(), address.getBuildingNumber(), address.getDetail(), pageRequest)
                .map(memberSnapshotPersistenceMapper::memberSnapshotEntityToMemberSnapshot);
    }

    @Override
    public Page<MemberSnapshot> findAllByMemberIdAndApartmentComplex(MemberId memberId, ApartmentComplex apartmentComplex, PageRequest pageRequest) {
        return memberSnapshotJpaRepository.findAllByMemberIdAndApartmentComplexName(memberId.getValue().toString(), apartmentComplex.getName(), pageRequest)
                .map(memberSnapshotPersistenceMapper::memberSnapshotEntityToMemberSnapshot);
    }

    @Override
    public Page<MemberSnapshot> findAllByMemberIdAndAddressAndSnapshotMemberId(MemberId memberId, Address address, MemberId snapshotMemberId, PageRequest pageRequest) {
        return memberSnapshotJpaRepository.findAllByMemberIdAndAddressAndSnapshotMemberId(
                memberId.getValue().toString(), address.getSiDo(), address.getSiGunGu(), address.getEupMyeonDong(), address.getRoadName(), address.getBuildingNumber(), address.getDetail(), snapshotMemberId.getValue(), pageRequest)
                .map(memberSnapshotPersistenceMapper::memberSnapshotEntityToMemberSnapshot);
    }

    @Override
    public Page<MemberSnapshot> findAllByMemberIdAndApartmentComplexAndSnapshotMemberId(MemberId memberId, ApartmentComplex apartmentComplex, MemberId snapshotMemberId, PageRequest pageRequest) {
        return memberSnapshotJpaRepository.findAllByMemberIdAndApartmentComplexNameAndSnapshotMemberId(memberId.getValue().toString(), apartmentComplex.getName(), snapshotMemberId.getValue(), pageRequest)
                .map(memberSnapshotPersistenceMapper::memberSnapshotEntityToMemberSnapshot);
    }

    @Override
    public List<Address> findAllDistinctAddressByMemberId(MemberId memberId) {
        List<Object[]> results = memberSnapshotJpaRepository.findAllDistinctAddressByMemberId(memberId.getValue().toString());
        return results.stream()
                .map(result -> new Address(
                        (String) result[0],
                        (String) result[1],
                        (String) result[2],
                        null, null, null
//                        (String) result[3],
//                        (String) result[4],
//                        (String) result[5]
                        )
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentComplex> findAllDistinctApartmentComplexByMemberIdAndAddress(MemberId memberId, Address address) {
        return memberSnapshotJpaRepository.findAllDistinctApartmentComplexByMemberIdAndAddress(memberId.getValue().toString(), address.getSiDo(), address.getSiGunGu(), address.getEupMyeonDong(), address.getRoadName(), address.getBuildingNumber(), address.getDetail()).stream()
                .map(ApartmentComplex::new)
                .collect(Collectors.toList());
    }

    @Override
    public int countAllByMemberIdAndAddress(MemberId memberId, Address address) {
        return memberSnapshotJpaRepository.countAllByMemberIdAndAddress(memberId.getValue().toString(), address.getSiDo(), address.getSiGunGu(), address.getEupMyeonDong(), address.getRoadName(), address.getBuildingNumber(), address.getDetail());
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
