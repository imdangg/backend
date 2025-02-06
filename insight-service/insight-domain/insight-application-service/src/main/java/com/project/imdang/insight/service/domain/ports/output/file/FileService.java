package com.project.imdang.insight.service.domain.ports.output.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String upload(String directory, MultipartFile file) throws IOException;
}
