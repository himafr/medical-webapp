package com.hema.medical_backend_spring.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.hema.medical_backend_spring.services.S3Service;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
public class ImagesApi {
    
        private final S3Service s3Service;
    
        public ImagesApi(S3Service s3Service) {
            this.s3Service = s3Service;
        }
    
        @PostMapping("/upload")
        public ResponseEntity<Map<String, String>> upload(@RequestParam("file") MultipartFile file) throws IOException {
            String key = s3Service.uploadImage(file);
            return ResponseEntity.ok(Map.of(
                    "key", key,
                    "url", s3Service.getImageUrl(key)
            ));
        }
    
        @DeleteMapping
        public ResponseEntity<Void> delete(@RequestParam("key") String key) {
            s3Service.deleteImage(key);
            return ResponseEntity.noContent().build();
        }
    }



