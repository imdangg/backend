package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.insight.service.domain.entity.Request;

public interface RequestRepository {
    Request save(Request request);
}
