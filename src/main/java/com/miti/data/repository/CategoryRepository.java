package com.miti.data.repository;

import com.miti.data.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

  Category getCategoryByName(String name);

  boolean existsByName(String name);
}
