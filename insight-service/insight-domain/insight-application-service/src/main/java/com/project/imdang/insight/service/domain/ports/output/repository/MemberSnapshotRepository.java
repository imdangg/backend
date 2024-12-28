package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.MemberSnapshot;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface MemberSnapshotRepository {

//    Optional<MemberSnapshot> findByMemberIdAndInsightId(MemberId memberId, InsightId insightId);
    Page<MemberSnapshot> findAllByMemberId(MemberId memberId, PageRequest pageRequest);
    Page<MemberSnapshot> findAllByMemberIdAndApartmentComplex(MemberId memberId, ApartmentComplex apartmentComplex, PageRequest pageRequest);

    MemberSnapshot save(MemberSnapshot memberSnapshot);
    void updateSnapshotIdByMemberIdAndInsightId(SnapshotId snapshotId, MemberId memberId, InsightId insightId);
    void deleteByMemberIdAndInsightId(MemberId memberId, InsightId insightId);
}
