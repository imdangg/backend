package com.project.imdang.member.persistence.adapter;

import com.project.imdang.member.persistence.entity.MemberEntity;
import com.project.imdang.member.persistence.mapper.MemberPersistenceMapper;
import com.project.imdang.member.persistence.repository.MemberJpaRepository;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.port.output.repository.MemberRespository;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRespository {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberPersistenceMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public boolean existByOauthIdAndType(OAuthLoginResponse response) {
        return memberJpaRepository.existsByOAuthIdAndOAuthType(response.getId(), response.oAuthType());
    }

    @Override
    @Transactional
    public Optional<Member> findMemberByOAuth(String oAuthId, OAuthType oAuthType) {
        Optional<MemberEntity> memberEntity = memberJpaRepository.findByOAuthIdAndOAuthType(oAuthId, oAuthType);
        return memberEntity.map(mapper::memberEntityToMember);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Member> findById(UUID memberId) {
        Optional<MemberEntity> memberEntity = memberJpaRepository.findById(memberId);
        return memberEntity.map(mapper::memberEntityToMember);
    }

    @Override
    @Transactional
    public Member save(Member member) {
        MemberEntity memberEntity = mapper.memberToMemberEntity(member);
        MemberEntity saved = memberJpaRepository.save(memberEntity);
        return mapper.memberEntityToMember(saved);
    }
}
