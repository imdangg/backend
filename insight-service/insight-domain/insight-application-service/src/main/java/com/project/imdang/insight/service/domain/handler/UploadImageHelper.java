package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.insight.service.domain.ports.output.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class UploadImageHelper {

    private static final String DIRECTORY = "image";

    private final FileService fileService;

    public String uploadImage(MultipartFile image) {

        if (image == null || image.isEmpty()) {
            return null;
        }

        try {
            return fileService.upload(DIRECTORY, image);
        } catch (IOException e) {
            String errorMessage = "Failed to upload image!";
            log.error(errorMessage);
            // TODO - 예외 처리
            throw new RuntimeException(errorMessage);
        }
    }
}
