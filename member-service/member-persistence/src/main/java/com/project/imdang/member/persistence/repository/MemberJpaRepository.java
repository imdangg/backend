package com.project.imdang.member.persistence.repository;

import com.project.imdang.member.persistence.entity.MemberEntity;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, UUID> {
    Optional<MemberEntity> findByAuthIdAndAuthType(String authId, OAuthType authType);
    Optional<MemberEntity> findByNickname(String nickname);
}
