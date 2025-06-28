package com.project.imdang.member.persistence.repository;

import com.project.imdang.common.domain.valueobject.OAuthProvider;
import com.project.imdang.member.persistence.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberJpaRepository extends JpaRepository<MemberEntity, UUID> {
    @Query("select m from MemberEntity m where m.oAuthId = :oAuthId and m.oAuthProvider = :oAuthProvider and m.isDeleted = :isDeleted")
    Optional<MemberEntity> findByOAuthIdAndOAuthProviderAndIsDeleted(String oAuthId, OAuthProvider oAuthProvider, Boolean isDeleted);
    Optional<MemberEntity> findByNickname(String nickname);
    Optional<MemberEntity> findByIdAndIsDeleted(UUID memberId, Boolean isDeleted);
}
