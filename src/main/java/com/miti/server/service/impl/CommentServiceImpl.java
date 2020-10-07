package com.miti.server.service.impl;

import com.miti.server.model.dto.CommentDTO;
import com.miti.server.model.entity.Comment;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.entity.User;
import com.miti.server.repository.CommentRepository;
import com.miti.server.repository.RecipeRepository;
import com.miti.server.repository.UserRepository;
import com.miti.server.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment addComment(String text, Long userId, Long recipeId) {
        Comment comment = new Comment(new CommentDTO(text, userId, recipeId));
        return addComment(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getCommentsByCommentator(String userName) {
        User user = userRepository.getUserByUsername(userName);
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
