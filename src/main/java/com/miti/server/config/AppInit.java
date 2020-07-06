package com.miti.server.config;

import com.miti.server.model.dto.CategoryDTO;
import com.miti.server.model.dto.IngredientDTO;
import com.miti.server.model.dto.RecipeDTO;
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

        userRepository.saveAll(users);

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

        categoryRepository.saveAll(categories);

        addIngredient(ingredients, "tomato", "Помидор");
        addIngredient(ingredients, "cucumber", "Огурец свежий");
        addIngredient(ingredients, "cucumber_salt", "Малосоленный огурец");
        addIngredient(ingredients, "cucumber_marinade", "Маринованный огурец");
        addIngredient(ingredients, "paprika_red", "Красный болгарский перец");
        addIngredient(ingredients, "paprika_green", "Зелёный болгарский перец");
        addIngredient(ingredients, "paprika_yellow", "Жёлтый болгарский перец");

        ingredientRepository.saveAll(ingredients);

        addRecipe(recipes, "Салат оливье", "Классический салат Оливье в советское время готовили исключительно с вареной колбасой, желательно - с  Докторской. Мы не оступили от традиции и приготовили Оливье по канонам  советской гастрономии.",
                userRepository.getUserById(1L), categoryRepository.getCategoryById("salads"));
        addRecipe(recipes, "French Fries", "Просто рецепт приготовления картошки фри в сковороде! Картофель фри любим многими!",
                userRepository.getUserById(2L), categoryRepository.getCategoryById("snacks"));
        addRecipe(recipes, "Борщ", "Еще не знаете, какое первое блюдо сделать на обед? Я хочу показать вам несложный пошаговый способ, как приготовить самый вкусный борщ. Насыщенный, аппетитный и сытный… чудесная идея для всей семьи! Подробнее: https://povar.ru/recipes/samyi_vkusnyi_borsh-57233.html",
                userRepository.getUserById(3L), categoryRepository.getCategoryById("firsts"));

        recipeRepository.saveAll(recipes);
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

    private void addIngredient(List<Ingredient> ingredients, String id, String name){
        if(!ingredientRepository.existsById(id)){
            ingredients.add(new Ingredient(new IngredientDTO(id, name)));
        }
    }

    private void addRecipe(List<Recipe> recipes, String name, String description, User author, Category category ){
        if(!recipeRepository.existsByName(name)){
            recipes.add(new Recipe(new RecipeDTO(name, description, author, category)));
        }
    }
}
