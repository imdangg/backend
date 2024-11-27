package com.project.imdang.insight.service.domain;

import com.project.imdang.insight.service.domain.valueobject.ComplexEnvironment;
import com.project.imdang.insight.service.domain.valueobject.Infra;
import com.project.imdang.insight.service.domain.valueobject.ObjectiveItem;
import com.project.imdang.insight.service.domain.valueobject.Opinion;
import com.project.imdang.insight.service.persistence.insight.entity.InsightEntity;
import com.project.imdang.insight.service.persistence.insight.repository.InsightJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;
import java.util.UUID;

@SpringBootTest
class InsightJpaRepositoryTest {

    @Autowired
    private InsightJpaRepository insightJpaRepository;

//    @Test
    void save() {
        InsightEntity insightEntity = new InsightEntity();
        UUID id = UUID.randomUUID();

        Opinion<Set<Infra.Transportation>> transportation = new Opinion<>(Set.of(Infra.Transportation.주차_편리, Infra.Transportation.버스정류장_가까움), "주차 편리");
        Infra infra = new Infra(transportation, null, null, null, null, null, null);

        Opinion<ObjectiveItem> buildingCondition = new Opinion<>(ObjectiveItem.좋아요, "좋아요");
        ComplexEnvironment complexEnvironment = new ComplexEnvironment(buildingCondition, null, null, null);

        insightEntity.setId(id);
        insightEntity.setInfra(infra);
        insightEntity.setComplexEnvironment(complexEnvironment);
        insightJpaRepository.save(insightEntity);
    }
}
