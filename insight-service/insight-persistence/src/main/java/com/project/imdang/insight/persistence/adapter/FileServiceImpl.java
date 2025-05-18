package com.project.imdang.insight.persistence.adapter;

import com.project.imdang.aws.s3.S3Service;
import com.project.imdang.common.domain.valueobject.File;
import com.project.imdang.insight.domain.ports.output.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class FileServiceImpl implements FileService {

    private final S3Service s3Service;

    @Override
    public String upload(File file) throws IOException {
        return s3Service.upload(file.size(), file.contentType(), file.inputStream());
    }
}
