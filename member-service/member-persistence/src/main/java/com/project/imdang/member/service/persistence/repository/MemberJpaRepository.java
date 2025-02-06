package com.project.imdang.member.service.persistence.repository;

import com.project.imdang.member.service.persistence.entity.MemberEntity;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, UUID> {
    Optional<MemberEntity> findByAuthIdAndAuthTypeAndIsDeleted(String authId, OAuthType authType, Boolean isDeleted);
    Optional<MemberEntity> findByNickname(String nickname);
}
