package com.miti.server.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

  @Value("${spring.minio.access-key}")
  private String accessKey;

  @Value("${spring.minio.access-secret}")
  private String accessSecret;

  @Value("${spring.minio.url}")
  private String url;

  @Bean
  public MinioClient generateMinioClient() {
    try {
      MinioClient client = new MinioClient(url, accessKey, accessSecret);
      return client;
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
