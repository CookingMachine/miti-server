package com.miti.server.controller;

import com.miti.server.api.MinioStorageApi;
import com.miti.server.service.impl.MinioStorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/storage")
public class MinioStorageController implements MinioStorageApi {

  private final MinioStorageService minioStorageService;

  @Override
  public Map<String, String> uploadFile(MultipartFile file) {
    return minioStorageService.uploadFile(file);
  }

  @Override
  public ResponseEntity<ByteArrayResource> downloadFile(String file) {
    return minioStorageService.downloadFile();
  }
}
