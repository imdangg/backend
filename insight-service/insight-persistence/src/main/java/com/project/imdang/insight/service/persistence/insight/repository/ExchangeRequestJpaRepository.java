package com.project.imdang.insight.service.persistence.insight.repository;

import com.project.imdang.insight.service.persistence.insight.entity.ExchangeRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExchangeRequestJpaRepository extends JpaRepository<ExchangeRequestEntity, UUID> {
}
