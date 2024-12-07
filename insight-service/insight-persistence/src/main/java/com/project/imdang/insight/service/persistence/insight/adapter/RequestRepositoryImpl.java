package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.insight.service.domain.entity.Request;
import com.project.imdang.insight.service.domain.ports.output.repository.RequestRepository;
import com.project.imdang.insight.service.persistence.insight.entity.RequestEntity;
import com.project.imdang.insight.service.persistence.insight.mapper.RequestPersistenceMapper;
import com.project.imdang.insight.service.persistence.insight.repository.RequestJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RequestRepositoryImpl implements RequestRepository {

    private final RequestJpaRepository requestJpaRepository;
    private final RequestPersistenceMapper requestPersistenceMapper;

    @Override
    public Request save(Request request) {
        RequestEntity requestEntity = requestPersistenceMapper.requestToRequestEntity(request);
        RequestEntity saved = requestJpaRepository.save(requestEntity);
        return requestPersistenceMapper.requestEntityToRequest(saved);
    }
}
