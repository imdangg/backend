package com.project.imdang.member.persistence.repository;

import com.project.imdang.member.persistence.entity.ActualLivingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActualLivingJpaRepository extends JpaRepository<ActualLivingEntity, Long> {

}
