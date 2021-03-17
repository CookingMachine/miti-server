package com.miti.server.controller;

import com.miti.server.model.MainPageContent;
import com.miti.server.util.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "api/v1/mainPage")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainPageController {

  private final MainPageService mainPageConstructor;

  @GetMapping
  public MainPageContent getMainPageElements(@RequestParam Long calories, @RequestParam int time) {
    return mainPageConstructor.mainPage(calories, time);
  }
}
