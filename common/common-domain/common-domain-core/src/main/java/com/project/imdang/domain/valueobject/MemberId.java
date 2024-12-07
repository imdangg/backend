package com.project.imdang.domain.valueobject;

import java.util.UUID;

public class MemberId extends BaseId<UUID> {
    public MemberId(UUID value) {
        super(value);
    }
}
