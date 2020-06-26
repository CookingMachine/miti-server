package com.miti.server.repo;

import com.miti.server.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteRepo extends JpaRepository<Favourite, Long> {
    List<Favourite> getFavouritesByUserId(Long userId);
}
