package com.miti.server.api;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface MinioStorageApi {

  @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  Map<String, String> uploadFile(@RequestPart(value = "file", required = false) MultipartFile file);

  @GetMapping("/download")
  ResponseEntity<ByteArrayResource> downloadFile(@RequestParam(value = "file") String file);

}
