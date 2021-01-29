package com.miti.server.repository;

import com.miti.server.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

  Category getCategoryByName(String name);

  boolean existsByName(String name);
}
