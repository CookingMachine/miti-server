package com.miti.server.repo;

import com.miti.server.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category getCategoryByName(String name);
}
