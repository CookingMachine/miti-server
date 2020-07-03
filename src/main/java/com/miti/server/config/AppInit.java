package com.miti.server.config;

import com.miti.server.model.dto.CategoryDTO;
import com.miti.server.model.dto.UserDTO;
import com.miti.server.enums.UserRole;
import com.miti.server.model.entity.*;
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

        addCategory(categories, "firsts", "Первые блюдо");
        addCategory(categories, "seconds", "Вторые блюдо");
        addCategory(categories, "soups", "Супы");
        addCategory(categories, "meat", "Мясо");
        addCategory(categories, "fish", "Рыба");
        addCategory(categories, "porridge", "Каши");
        addCategory(categories, "salads", "Салаты");
        addCategory(categories, "dessert", "Десерты");
        addCategory(categories, "snacks", "Закуски");
        addCategory(categories, "non_alc_drinks", "Безалкогольные напитки");
        addCategory(categories, "alc_drinks", "Алкогольные напитки");

        userRepository.saveAll(users);
        categoryRepository.saveAll(categories);
    }

    private void addUser(List<User> users, String username, String password, String email, UserRole role){
        if(!userRepository.existsByUsername(username)){
            users.add(new User(new UserDTO(username, password, email, role)));
        }
    }

    private void addCategory(List<Category> categories, String id, String name){
        if(!categoryRepository.existsById(id)){
            categories.add(new Category(new CategoryDTO(id, name)));
        }
    }
}
