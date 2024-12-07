package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.insight.service.domain.entity.Request;
import com.project.imdang.insight.service.domain.ports.output.repository.RequestRepository;
import org.springframework.stereotype.Component;

@Component
public class RequestRepositoryImpl implements RequestRepository {
    @Override
    public Request save(Request request) {
        return null;
    }
}
