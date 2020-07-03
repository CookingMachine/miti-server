package com.miti.server.service.impl;

import com.miti.server.entity.Comment;
import com.miti.server.entity.Recipe;
import com.miti.server.entity.User;
import com.miti.server.repository.CommentRepository;
import com.miti.server.repository.RecipeRepository;
import com.miti.server.repository.UserRepository;
import com.miti.server.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    CommentServiceImpl(CommentRepository commentRepository,
                       UserRepository userRepository,
                       RecipeRepository recipeRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment addComment(String text, String commentator, Long recipeId) {
        User user = userRepository.getUserByUserName(commentator);
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()->
                new RuntimeException("Recipe with id: " + recipeId + " is not find!"));
        Comment comment = new Comment(text, user, recipe);
        return addComment(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getCommentsByCommentator(String userName) {
        User user = userRepository.getUserByUserName(userName);
        List<Comment> comments = commentRepository.getCommentsByCommentator(user);
        return comments;
    }

    @Override
    public List<Comment> getCommentsByRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()->
                new RuntimeException("Recipe with recipeId:" + recipeId + "doesnt exists"));
        List<Comment> comments = commentRepository.getCommentsByRecipe(recipe);
        return comments;
    }

}
