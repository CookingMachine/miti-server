package com.miti.server.service;

import com.miti.server.model.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(Comment comment);

    Comment addComment(String text, Long userId, Long recipeId);

    List<Comment> getAllComments();

    List<Comment> getCommentsByCommentator(String userName);

    List<Comment> getCommentsByRecipe(Long recipeId);
}
