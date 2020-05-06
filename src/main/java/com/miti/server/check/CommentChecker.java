package com.miti.server.check;

import com.miti.server.entity.Recipe;
import com.miti.server.entity.User;

public class CommentChecker {
    public boolean commentChecker(String comment, User commentator) {
        if ((comment != null && !comment.isEmpty()) &&
                commentator != null)
            return true;
        return false;
    }
}
