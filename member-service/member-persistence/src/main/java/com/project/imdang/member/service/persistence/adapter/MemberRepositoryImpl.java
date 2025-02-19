package com.project.imdang.member.service.persistence.adapter;

import com.project.imdang.domain.valueobject.BaseId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.persistence.entity.MemberEntity;
import com.project.imdang.member.service.persistence.mapper.MemberPersistenceMapper;
import com.project.imdang.member.service.persistence.repository.MemberJpaRepository;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.ports.output.MemberRepository;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberPersistenceMapper memberPersistenceMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<Member> findByOAuthIdAndOAuthTypeAndIsDeleted(String oAuthId, OAuthType oAuthType, Boolean isDeleted) {
        Optional<MemberEntity> memberEntity = memberJpaRepository.findByAuthIdAndAuthTypeAndIsDeleted(oAuthId, oAuthType, isDeleted);
        return memberEntity.map(memberPersistenceMapper::memberEntityToMember);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Member> findById(MemberId memberId) {
        Optional<MemberEntity> memberEntity = memberJpaRepository.findById(memberId.getValue());
        return memberEntity.map(memberPersistenceMapper::memberEntityToMember);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Member> findAllByIds(List<MemberId> memberIds) {
        List<UUID> ids = memberIds.stream()
                .map(BaseId::getValue)
                .toList();
        return memberJpaRepository.findAllById(ids).stream()
                .map(memberPersistenceMapper::memberEntityToMember)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Member> findByNickname(String nickname) {
        Optional<MemberEntity> memberEntity = memberJpaRepository.findByNickname(nickname);
        return memberEntity.map(memberPersistenceMapper::memberEntityToMember);
    }

    @Override
    public Optional<Member> findByIdAndIsDeleted(MemberId memberId) {
        Optional<MemberEntity> memberEntity = memberJpaRepository.findByIdAndIsDeleted(memberId.getValue(), Boolean.FALSE);
        return memberEntity.map(memberPersistenceMapper::memberEntityToMember);
    }

    @Override
    @Transactional
    public Member save(Member member) {
        MemberEntity memberEntity = memberPersistenceMapper.memberToMemberEntity(member);
        MemberEntity saved = memberJpaRepository.save(memberEntity);
        return memberPersistenceMapper.memberEntityToMember(saved);
    }
}
