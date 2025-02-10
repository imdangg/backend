package com.project.imdang.insight.service.persistence.adapter;

import com.project.imdang.aws.s3.S3Service;
import com.project.imdang.insight.service.domain.ports.output.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class FileServiceImpl implements FileService {

    private final S3Service s3Service;

    @Override
    public String upload(String directory, MultipartFile file) throws IOException {
        return s3Service.uploadFile(directory, file);
    }
}
