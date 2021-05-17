package com.miti.server.service.impl;

import com.miti.data.model.CalorieContent;
import com.miti.data.repository.CalorieContentRepository;
import com.miti.server.service.CalorieContentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CalorieContentServiceImpl implements CalorieContentService {

  private final CalorieContentRepository calorieContentRepository;

  @Override
  public CalorieContent addCalorieContent(CalorieContent calorieContent) {
    return calorieContentRepository.save(new CalorieContent(calorieContent.getCalories(),
        calorieContent.getProtein(),
        calorieContent.getFat(),
        calorieContent.getCarbohydrates()));
  }

  @Override
  public void addAllCalorieContent(List<CalorieContent> calorieContents) {
    calorieContentRepository.saveAll(calorieContents);
  }

  @Override
  public CalorieContent editCalorieContent(CalorieContent newCalorieContent) {
    return calorieContentRepository.findById(newCalorieContent.getId()).map(calorieContent ->
    {
      calorieContent.setCalories(newCalorieContent.getCalories());
      calorieContent.setProtein(newCalorieContent.getProtein());
      calorieContent.setFat(newCalorieContent.getFat());
      calorieContent.setCarbohydrates(newCalorieContent.getCarbohydrates());
      return calorieContentRepository.save(calorieContent);
    }).orElseThrow(()
        -> new RuntimeException("Calories with id: " + newCalorieContent.getId() + " doesn't exist!"));
  }

  @Override
  public CalorieContent getCalorieContentById(Long calorieId) {
    return calorieContentRepository.findById(calorieId).orElseThrow(() ->
        new RuntimeException("Calorie with id: " + calorieId + " doesn't exist!"));
  }

  @Override
  public List<CalorieContent> getCalorieContentsByCaloriesLessThan(Long calories) {
    return calorieContentRepository.getCalorieContentsByCaloriesLessThan(calories);
  }

  @Override
  public void deleteCalorieContentById(Long calorieId) {
    calorieContentRepository.deleteById(calorieId);
  }
}
