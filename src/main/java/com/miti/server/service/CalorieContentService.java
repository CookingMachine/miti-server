package com.miti.server.service;

import com.miti.server.model.entity.CalorieContent;

import java.util.List;

public interface CalorieContentService {
  CalorieContent addCalorieContent(CalorieContent calorieContent);
  void addAllCalorieContent(List<CalorieContent> calorieContents);

  CalorieContent editCalorieContent(Long calorieId, CalorieContent calorieContent);

  CalorieContent getCalorieContentById(Long calorieId);

  List<CalorieContent> getCalorieContentsByCaloriesLessThan(Long calories);

  void deleteCalorieContentById(Long calorieId);
}
