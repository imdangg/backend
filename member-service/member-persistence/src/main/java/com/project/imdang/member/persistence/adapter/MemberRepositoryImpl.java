package com.project.imdang.member.persistence.adapter;

import com.project.imdang.member.persistence.entity.MemberEntity;
import com.project.imdang.member.persistence.mapper.MemberPersistenceMapper;
import com.project.imdang.member.persistence.repository.MemberJpaRepository;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.port.output.repository.MemberRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
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
    public Member findOrCreateMember(OAuthLoginResponse loginResponse) {
        // 새로운 사용자면 새로 생성 후 저장
        MemberEntity findMemberEntity = memberJpaRepository.findByOAuthIdAndOAuthType(loginResponse.getId(), loginResponse.oAuthType())
                .orElse(memberJpaRepository.save(MemberEntity.builder()
                        .id(UUID.randomUUID())
                        .oAuthId(loginResponse.getId())
                        .oAuthType(loginResponse.oAuthType()).build()));
        return mapper.memberEntityToMember(findMemberEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Member findById(UUID memberId) {
        //TODO : 예외처리
        MemberEntity findMemberEntity = memberJpaRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("일치하는 사용자가 존재하지 않습니다."));
        return mapper.memberEntityToMember(findMemberEntity);
    }

    @Override
    @Transactional
    public Member join(Member member) {
        //TODO : 예외처리
        MemberEntity memberEntity = mapper.memberToMemberEntity(member);
        MemberEntity saved = memberJpaRepository.save(memberEntity);
        return mapper.memberEntityToMember(saved);
    }
}
