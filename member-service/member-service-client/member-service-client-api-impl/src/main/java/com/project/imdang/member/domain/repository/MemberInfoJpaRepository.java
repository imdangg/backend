package com.project.imdang.member.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MemberInfoJpaRepository extends JpaRepository<MemberInfoEntity, UUID> {
}
