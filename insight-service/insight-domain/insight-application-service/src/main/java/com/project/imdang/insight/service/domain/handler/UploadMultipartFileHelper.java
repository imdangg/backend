package com.project.imdang.insight.service.domain.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RequiredArgsConstructor
@Component
public class UploadMultipartFileHelper {

    public String uploadFile(MultipartFile file) {

        if (file.isEmpty()) {
            // TODO : 예외 처리
            throw new IllegalArgumentException("File is empty!");
        }

        String filename = file.getOriginalFilename();
//        String extension = filename.substring(filename.lastIndexOf("."));
//        String newFileName = UUID.randomUUID().toString() + extension;
/*
        try {

            String uploadDir = "/uploads";
            // 업로드 디렉토리 확인 및 생성
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(filename);
            file.transferTo(filePath.toFile());

        } catch (IOException e) {
            // TODO : 예외 처리
            throw new RuntimeException(e);
        }*/

        return filename;
    }
}
