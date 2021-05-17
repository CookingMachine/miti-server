package com.miti.server.service;

import com.miti.data.model.CalorieContent;
import java.util.List;

public interface CalorieContentService {

  CalorieContent addCalorieContent(CalorieContent calorieContent);

  void addAllCalorieContent(List<CalorieContent> calorieContents);

  CalorieContent editCalorieContent(CalorieContent calorieContent);

  CalorieContent getCalorieContentById(Long calorieId);

  List<CalorieContent> getCalorieContentsByCaloriesLessThan(Long calories);

  void deleteCalorieContentById(Long calorieId);
}
