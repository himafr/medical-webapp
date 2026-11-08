package com.hema.medical_backend_spring.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Value("${aws.region}")
    private String region;

    @Value("${aws.s3.folder}")
    private String folder;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("الملف لازم يكون صورة");
        }

        String key = folder + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
        
        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .contentType(contentType)
                        .build(),
                RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        return key;
        
    }

    public String getImageUrl(String key) {
        return "https://" + bucket + ".s3." + region + ".amazonaws.com/" + key;
    }

    public void deleteImage(String key) {
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build());
    }

    public void deleteImageByUrl(String imageUrl) {
        if (imageUrl == null || imageUrl.isBlank()) {
            return;
        }

        URL url;
        try {
            url = new URL(imageUrl);
        } catch (MalformedURLException e) {
            return;
        }

        String expectedHost = bucket + ".s3." + region + ".amazonaws.com";
        if (!"https".equalsIgnoreCase(url.getProtocol())
                || !expectedHost.equalsIgnoreCase(url.getHost())
                || url.getQuery() != null
                || url.getRef() != null) {
            return;
        }

        String normalizedFolder = folder.replaceAll("^/+|/+$", "");
        String path = url.getPath();
        String folderPrefix = "/" + normalizedFolder + "/";
        if (!path.startsWith(folderPrefix)) {
            return;
        }

        String key = URLDecoder.decode(path.substring(1).replace("+", "%2B"), StandardCharsets.UTF_8);
        deleteImage(key);
    }
}