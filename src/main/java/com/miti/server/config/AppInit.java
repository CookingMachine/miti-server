package com.miti.server.config;

import com.miti.server.entity.*;
import com.miti.server.entity.dto.UserDTO;
import com.miti.server.enums.UserRole;
import com.miti.server.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppInit implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private IngredientContextRepository ingredientContextRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<User> users = new ArrayList<>();
        List<Recipe> recipes = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        List<IngredientContext> ingredientContexts = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();

        addUser(users, "admin", "qwerty", "admin@gmail.com", UserRole.ADMINISTRATION);
        addUser(users, "moder", "qwerty", "moder@gmail.com", UserRole.MODERATION);
        addUser(users, "user", "qwerty", "user@gmail.com", UserRole.USER);


        userRepository.saveAll(users);
    }

    private void addUser(List<User> users, String username, String password, String email, UserRole role){
        if(!userRepository.existsByUsername(username)){
            users.add(new User(new UserDTO(username, password, email, role)));
        }
    }
}
