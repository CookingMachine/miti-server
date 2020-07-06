package com.miti.server.config.check;

import com.miti.server.model.entity.User;

public class RecipeChecker {
    public boolean recipeChecker(String name, User author) {
        if ((name != null && !name.isEmpty()) && author != null)
            return true;
        return false;
    }
}
