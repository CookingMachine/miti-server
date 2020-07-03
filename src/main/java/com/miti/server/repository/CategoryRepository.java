package com.miti.server.repository;

import com.miti.server.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getCategoryByName(String name);
}