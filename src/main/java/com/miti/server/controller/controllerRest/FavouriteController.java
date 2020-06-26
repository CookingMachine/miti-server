package com.miti.server.controller.controllerRest;

import com.miti.server.entity.Favourite;
import com.miti.server.service.FavouriteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FavouriteController {
    private final FavouriteService favouriteService;

    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @GetMapping("/getFavouritesByUserId")
    public List<Favourite> getFavouritesByUserId(@RequestParam Long userId) {
        return favouriteService.getFavouritesByUserId(userId);
    }

    @PostMapping("/addFavourite")
    public Favourite addFavourite(@RequestParam Long userId, @RequestParam Long recipeId) {
        return favouriteService.addFavourite(userId, recipeId);
    }
}
