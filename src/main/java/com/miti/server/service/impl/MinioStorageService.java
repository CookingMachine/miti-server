package com.miti.server.service.impl;

import io.minio.MinioClient;
import io.minio.messages.Bucket;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MinioStorageService {

  @Value("${minio.bucket.name}")
  String defaultBucketName;

  @Value("${spring.minio.bucket.folder}")
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

      // Создаём bucket, если его не существует
      if (!minioClient.bucketExists(defaultBucketName)) {
        minioClient.makeBucket(defaultBucketName);
      }

      minioClient.putObject(defaultBucketName, baseFolder + fileName, file.getAbsolutePath());
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public Map<String, String> uploadFile(MultipartFile multipartFile) {
    Map<String, String> result = new HashMap<>();
    File file = new File("/recipe/" + multipartFile.getName());
    file.canWrite();
    file.canRead();

    try {
      FileOutputStream outputStream = new FileOutputStream(file);
      outputStream.write(multipartFile.getBytes());

      // Создаём bucket, если его не существует
      if (!minioClient.bucketExists(defaultBucketName)) {
        minioClient.makeBucket(defaultBucketName);
      }

      minioClient.putObject(defaultBucketName, baseFolder + multipartFile.getName(), file.getAbsolutePath());
      result.put("key", multipartFile.getOriginalFilename());

      return result;

    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public ResponseEntity<ByteArrayResource> downloadFile(String key) {
    try {
      InputStream obj = minioClient.getObject(defaultBucketName, baseFolder + "/" + key);
      // TODO
    } catch (Exception exception) {
      throw new RuntimeException(exception.getMessage());
    }
    return null;
  }
}
