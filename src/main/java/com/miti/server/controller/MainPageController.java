package com.miti.server.controller;

import com.miti.server.model.MainPageContent;
import com.miti.server.util.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/mainPage")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainPageController {

  private final MainPageService mainPageConstructor;

  @GetMapping(value = "")
  public MainPageContent getMainPageElements() {
    return mainPageConstructor.mainPage();
  }
}
