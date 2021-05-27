package com.miti.server.controller;

import com.miti.server.api.response.MainPageResponse;
import com.miti.server.util.MainPageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/mainPage")
@AllArgsConstructor
public class MainPageController {

  private final MainPageService mainPageConstructor;

  @GetMapping(value = "")
  public MainPageResponse getMainPageElements() {
    return mainPageConstructor.mainPage();
  }
}
