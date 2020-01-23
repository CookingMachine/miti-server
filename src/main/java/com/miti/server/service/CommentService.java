package com.miti.server.service;

import com.miti.server.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(Comment comment);

    List<Comment> getCommentsByCommentator(String userName);

    List<Comment> getCommentsByRecipe(Long recipeId);
}
