package com.miti.server.service.impl;

import com.miti.server.entity.Favourite;
import com.miti.server.entity.Recipe;
import com.miti.server.entity.User;
import com.miti.server.repo.FavouriteRepo;
import com.miti.server.repo.RecipeRepo;
import com.miti.server.repo.UserRepo;
import com.miti.server.service.FavouriteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteServiceImpl implements FavouriteService {
    private final FavouriteRepo favouriteRepo;
    private final UserRepo userRepo;
    private final RecipeRepo recipeRepo;

    public FavouriteServiceImpl(FavouriteRepo favouriteRepo,
                                UserRepo userRepo,
                                RecipeRepo recipeRepo) {
        this.favouriteRepo = favouriteRepo;
        this.userRepo = userRepo;
        this.recipeRepo = recipeRepo;
    }

    @Override
    public List<Favourite> getFavouritesByUserId(Long userId) {
        return favouriteRepo.getFavouritesByUserId(userId);
    }

    @Override
    public Favourite addFavourite(Long userId, Long recipeId) {
        User _user = userRepo.findById(userId).orElseThrow(()
                -> new RuntimeException("User with id: " + userId + " doesn't exist!"));
        Recipe _recipe = recipeRepo.findById(recipeId).orElseThrow(()
                -> new RuntimeException("Recipe with id: " + recipeId + " doesn't exist!"));
        Favourite _favourite = new Favourite(_user, _recipe, _user.getId(), _recipe.getId());
        return addFavourite(_favourite);
    }

    @Override
    public Favourite addFavourite(Favourite favourite) {

        return favouriteRepo.save(favourite);
    }
}
