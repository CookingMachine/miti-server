package com.miti.server.service.impl;

import io.minio.MinioClient;
import io.minio.messages.Bucket;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class MinioStorageService {

  @Value("${minio.bucket-name}")
  String defaultBucketName;

  @Value("${spring.minio.default-folder}")
  String baseFolder;

  private final MinioClient minioClient;

  public List<Bucket> getAllBuckets() {
    try {
      return minioClient.listBuckets();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  private void uploadFile(String fileName, byte[] content) {
    File file = new File("/recipe/" + fileName);
    file.canWrite();
    file.canRead();
    try {
      FileOutputStream outputStream = new FileOutputStream(file);
      outputStream.write(content);
      minioClient.putObject(defaultBucketName, baseFolder + fileName, file.getAbsolutePath());
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public Map<String, String> uploadFile(MultipartFile multipartFile) {
    try {
      uploadFile(multipartFile.getName(), multipartFile.getBytes());
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    Map<String, String> result = new HashMap<>();
    result.put("key", multipartFile.getOriginalFilename());
    return result;
  }

  public ResponseEntity<ByteArrayResource> downloadFile() {
    return null;
  }
}
