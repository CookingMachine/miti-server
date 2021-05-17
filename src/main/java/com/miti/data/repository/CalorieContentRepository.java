package com.miti.data.repository;

import com.miti.data.model.CalorieContent;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalorieContentRepository extends JpaRepository<CalorieContent, Long> {

  List<CalorieContent> getCalorieContentsByCaloriesLessThan(Long calories);
}
