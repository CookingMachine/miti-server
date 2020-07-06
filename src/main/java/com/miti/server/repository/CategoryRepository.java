package com.miti.server.repository;

import com.miti.server.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Category getCategoryByName(String name);

    Category getCategoryById(String id);
}
