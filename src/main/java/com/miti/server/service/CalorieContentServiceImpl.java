package com.miti.server.service;

import com.miti.server.api.CalorieContentService;
import com.miti.server.model.entity.CalorieContent;
import com.miti.server.repository.CalorieContentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
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
