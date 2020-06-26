package com.miti.server.service;


import com.miti.server.entity.Favourite;
import com.miti.server.entity.User;

import java.util.List;

public interface FavouriteService {
    List<Favourite> getFavouritesByUserId(Long userId);

    Favourite addFavourite(Long userId, Long recipeId);

    Favourite addFavourite(Favourite favourite);
}
