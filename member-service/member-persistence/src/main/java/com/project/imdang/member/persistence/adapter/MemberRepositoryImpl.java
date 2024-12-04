package com.project.imdang.member.persistence.adapter;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.persistence.entity.MemberEntity;
import com.project.imdang.member.persistence.mapper.MemberPersistenceMapper;
import com.project.imdang.member.persistence.repository.MemberJpaRepository;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.ports.output.MemberRespository;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRespository {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberPersistenceMapper memberPersistenceMapper;

    @Override
    @Transactional
    public Optional<Member> findMemberByOAuthIdAndOAuthType(String oAuthId, OAuthType oAuthType) {
        Optional<MemberEntity> memberEntity = memberJpaRepository.findByOAuthIdAndOAuthType(oAuthId, oAuthType);
        return memberEntity.map(memberPersistenceMapper::memberEntityToMember);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Member> findById(MemberId memberId) {
        Optional<MemberEntity> memberEntity = memberJpaRepository.findById(memberId.getValue());
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
