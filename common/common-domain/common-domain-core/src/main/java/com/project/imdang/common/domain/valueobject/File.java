package com.project.imdang.common.domain.valueobject;

import lombok.Builder;

import java.io.InputStream;

@Builder
public record File(
        String originalFilename,
        long size,
        String contentType,
        InputStream inputStream
) {
}
