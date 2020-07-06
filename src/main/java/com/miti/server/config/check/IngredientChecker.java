package com.miti.server.config.check;

public class IngredientChecker {


    public static boolean check(String name) {
        if (name != null && !name.isEmpty())
            return true;
        else
            return false;
    }
}
