package com.miti.server.service.impl;

import com.miti.server.model.entity.Comment;
import com.miti.server.repository.CommentRepository;
import com.miti.server.service.CommentService;
import com.miti.server.service.RecipeService;
import com.miti.server.service.UserService;
import com.miti.server.util.Check;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final RecipeService recipeService;

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(new Comment(
                comment.getComment(),
                comment.getCommentator(),
                comment.getRecipe()
        ));
    }

    @Override
    public void addAllComments(List<Comment> comments) {
        List<Comment> _comments = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++) {
            _comments.add(comments.get(i));
        }
        commentRepository.saveAll(_comments);
    }

    @Override
    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(()
                -> new RuntimeException("Comment with id: " + commentId + " doesn't exist!"));
    }

    @Override
    public List<Comment> getCommentsByUserId(Long userId) {
        if (Check.param(userId)) {
            List<Comment> comments = commentRepository.getCommentsByCommentator(userService.getUserById(userId));
            if (comments != null)
                return comments;
            throw new RuntimeException("Comments with userId: " + userId + " doesn't exist!");
        }
        throw new RuntimeException("UserId: " + userId + " is incorrect!");
    }

    @Override
    public List<Comment> getCommentsByRecipeId(Long recipeId) {
        if (Check.param(recipeId)) {
            List<Comment> comments = commentRepository.getCommentsByRecipe(recipeService.getRecipeById(recipeId));
            if (comments != null)
                return comments;
            throw new RuntimeException("Comments with recipeId: " + recipeId + " doesn't exist!");
        }
        throw new RuntimeException("RecipeId: " + recipeId + " is incorrect!");
    }

    @Override
    public void deleteCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
