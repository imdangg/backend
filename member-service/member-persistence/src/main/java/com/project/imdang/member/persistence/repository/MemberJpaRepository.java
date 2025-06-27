package com.project.imdang.member.persistence.repository;

import com.project.imdang.member.persistence.entity.MemberEntity;
import com.project.imdang.common.domain.valueobject.OAuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, UUID> {
    Optional<MemberEntity> findByAuthIdAndAuthTypeAndIsDeleted(String authId, OAuthProvider authType, Boolean isDeleted);
    Optional<MemberEntity> findByNickname(String nickname);
    Optional<MemberEntity> findByIdAndIsDeleted(UUID memberId, Boolean isDeleted);
}
