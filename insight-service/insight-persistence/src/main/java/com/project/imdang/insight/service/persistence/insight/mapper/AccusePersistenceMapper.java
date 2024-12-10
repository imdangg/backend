package com.project.imdang.insight.service.persistence.insight.mapper;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.Accuse;
import com.project.imdang.insight.service.domain.valueobject.AccuseId;
import com.project.imdang.insight.service.persistence.insight.entity.AccuseEntity;
import org.springframework.stereotype.Component;

@Component
public class AccusePersistenceMapper {

    public AccuseEntity accuseToAccuseEntity(Accuse accuse) {
        return AccuseEntity.builder()
                .id(accuse.getId().getValue())
                .accuseMemberId(accuse.getAccuseMemberId().getValue())
                .accusedMemberId(accuse.getAccusedMemberId().getValue())
                .createdAt(accuse.getCreatedAt())
                .build();
    }

    public Accuse accuseEntityToAccuse(AccuseEntity accuseEntity) {
        return Accuse.builder()
                .id(new AccuseId(accuseEntity.getId()))
                .accuseMemberId(new MemberId(accuseEntity.getAccuseMemberId()))
                .accusedMemberId(new MemberId(accuseEntity.getAccusedMemberId()))
                .createdAt(accuseEntity.getCreatedAt())
                .build();
    }
}