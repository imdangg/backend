package com.project.imdang.member.domain.client;

import com.project.imdang.common.domain.valueobject.BaseId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.repository.MemberInfoEntity;
import com.project.imdang.member.domain.repository.MemberInfoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Component
public class MemberInfoResolverImpl implements MemberDataResolver {

    private final MemberInfoJpaRepository memberInfoJpaRepository;

    @Override
    public Optional<MemberData> resolve(MemberId memberId) {
        Optional<MemberInfoEntity> memberEntity = memberInfoJpaRepository.findById(memberId.getValue());
        return memberEntity.map(entity -> MemberData.builder()
                .memberId(entity.getId())
                .nickname(entity.getNickname())
                .birthDate(entity.getBirthDate())
                .gender(entity.getGender().name())
                .deviceToken(entity.getDeviceToken())
                .accusedCount(entity.getAccusedCount())
                .latestInsightCreateDate(entity.getLatestInsightCreateDate())
                .build());
    }

    @Override
    public List<MemberData> resolve(List<MemberId> memberIds) {
        List<UUID> ids = memberIds.stream()
                .map(BaseId::getValue)
                .toList();
        return memberInfoJpaRepository.findAllById(ids).stream()
                .map(entity -> MemberData.builder()
                        .memberId(entity.getId())
                        .nickname(entity.getNickname())
                        .birthDate(entity.getBirthDate())
                        .gender(entity.getGender().name())
                        .deviceToken(entity.getDeviceToken())
                        .accusedCount(entity.getAccusedCount())
                        .latestInsightCreateDate(entity.getLatestInsightCreateDate())
                        .build())
                .collect(Collectors.toList());
    }
}
