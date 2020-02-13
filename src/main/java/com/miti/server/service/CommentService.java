package com.miti.server.service;

import com.miti.server.entity.Comment;
import com.miti.server.entity.Recipe;
import com.miti.server.entity.User;

import java.util.List;

public interface CommentService {
    Comment addComment(Comment comment);

    Comment addComment(String text, String commentator, Long recipeId);

    List<Comment> getAllComments();

    List<Comment> getCommentsByCommentator(String userName);

    List<Comment> getCommentsByRecipe(Long recipeId);
}
