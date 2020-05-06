package com.miti.server.check;

import com.miti.server.entity.User;

public class RecipeChecker {
    public boolean recipeChecker(String name, User author) {
        if ((name != null && !name.isEmpty()) && author != null)
            return true;
        return false;
    }
}
