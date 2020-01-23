package com.miti.server.service.impl;

import com.miti.server.entity.Comment;
import com.miti.server.entity.Recipe;
import com.miti.server.entity.User;
import com.miti.server.repo.CommentRepo;
import com.miti.server.repo.RecipeRepo;
import com.miti.server.repo.UserRepo;
import com.miti.server.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;
    private final UserRepo userRepo;
    private final RecipeRepo recipeRepo;

    CommentServiceImpl(CommentRepo commentRepo,
                       UserRepo userRepo,
                       RecipeRepo recipeRepo) {
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
        this.recipeRepo = recipeRepo;
    }

    @Override
    public Comment addComment(Comment comment) {
        Comment newComment = new Comment(comment.getComment(),
                comment.getCommentator(),
                comment.getRecipe());

        return commentRepo.save(newComment);
    }

    @Override
    public List<Comment> getCommentsByCommentator(String userName) {
        User user = userRepo.getUserByUserName(userName);
        List<Comment> comments = commentRepo.getCommentsByCommentator(user);
        return comments;
    }

    @Override
    public List<Comment> getCommentsByRecipe(Long recipeId) {
        Recipe recipe = recipeRepo.findById(recipeId).orElseThrow(()->
                new RuntimeException("Recipe with recipeId:" + recipeId + "doesnt exists"));
        List<Comment> comments = commentRepo.getCommentsByRecipe(recipe);
        return comments;
    }

}
