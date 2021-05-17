package com.miti.data.repository;

import com.miti.data.model.RestaurantRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantRatingRepository extends JpaRepository<RestaurantRating, UUID> {
}
