package com.project.imdang.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class S3Service {

    private static final String DIRECTORY = "image";

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    public String upload(long size, String contentType, InputStream inputStream) throws IOException {
        String filename = DIRECTORY + "/" + UUID.randomUUID();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(size);
        metadata.setContentType(contentType);

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
}
