package com.miti.server.repository;

import com.miti.server.model.entity.CalorieContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalorieContentRepository extends JpaRepository<CalorieContent, Long> {

  List<CalorieContent> getCalorieContentsByCaloriesLessThan(Long calories);
}
