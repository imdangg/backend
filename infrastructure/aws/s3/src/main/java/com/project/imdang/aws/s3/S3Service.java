package com.project.imdang.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class S3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    public String uploadFile(String directory, MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            // TODO - 예외 처리
            throw new RuntimeException("No file detected.");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            // TODO - 예외 처리
            throw new RuntimeException("No filename.");
        }
        validateFileExtension(originalFilename);
        return upload(directory, file);
    }

    private String upload(String directory, MultipartFile file) throws IOException {
        String filename = directory + "/" + UUID.randomUUID();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        InputStream inputStream = file.getInputStream();
        byte[] bytes = IOUtils.toByteArray(inputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        try {
            amazonS3.putObject(bucket, filename, byteArrayInputStream, metadata);
        } catch (Exception e) {
            throw new RuntimeException("Failed!");
        } finally {
            byteArrayInputStream.close();
            inputStream.close();
        }
        return amazonS3.getUrl(bucket, filename).toString();
    }

    private void validateFileExtension(String originalFilename) {
        int lastIndex = originalFilename.lastIndexOf(".");
        if (lastIndex == -1) {
            // TODO - 예외 처리
            throw new RuntimeException("The file has no extension.");
        }

        String extension = originalFilename.substring(lastIndex + 1).toLowerCase();
        List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png");

        if (!allowedExtensions.contains(extension)) {
            // TODO - 예외 처리
            throw new RuntimeException("Invalid file type.");
        }
    }
}
