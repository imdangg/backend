package com.project.imdang.insight.service.domain.valueobject;

import com.project.imdang.domain.valueobject.BaseId;

import java.util.UUID;

public class RequestId extends BaseId<UUID> {
    public RequestId(UUID value) {
        super(value);
    }
}
