package com.miti.server.config;

import com.miti.server.enums.IngredientCategory;
import com.miti.server.enums.Measure;
import com.miti.server.model.dto.*;
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

        addIngredient(ingredients, "tomato", "Помидор", IngredientCategory.VEGETABLES);
        addIngredient(ingredients, "cucumber", "Огурец свежий", IngredientCategory.VEGETABLES);
        addIngredient(ingredients, "cucumber_salt", "Малосоленный огурец", IngredientCategory.VEGETABLES);
        addIngredient(ingredients, "cucumber_marinade", "Маринованный огурец", IngredientCategory.VEGETABLES);
        addIngredient(ingredients, "paprika_red", "Красный болгарский перец", IngredientCategory.VEGETABLES);
        addIngredient(ingredients, "paprika_green", "Зелёный болгарский перец", IngredientCategory.VEGETABLES);
        addIngredient(ingredients, "paprika_yellow", "Жёлтый болгарский перец", IngredientCategory.VEGETABLES);

        ingredientRepository.saveAll(ingredients);

        addRecipe(recipes, "Салат оливье", "Классический салат Оливье в советское время готовили исключительно с вареной колбасой, желательно - с  Докторской. Мы не оступили от традиции и приготовили Оливье по канонам  советской гастрономии.",
                userRepository.getUserById(1L), categoryRepository.getCategoryById("salads"));
        addRecipe(recipes, "French Fries", "Просто рецепт приготовления картошки фри в сковороде! Картофель фри любим многими!",
                userRepository.getUserById(2L), categoryRepository.getCategoryById("snacks"));
        addRecipe(recipes, "Борщ", "Еще не знаете, какое первое блюдо сделать на обед? Я хочу показать вам несложный пошаговый способ, как приготовить самый вкусный борщ. Насыщенный, аппетитный и сытный… чудесная идея для всей семьи! Подробнее: https://povar.ru/recipes/samyi_vkusnyi_borsh-57233.html",
                userRepository.getUserById(3L), categoryRepository.getCategoryById("firsts"));

        recipeRepository.saveAll(recipes);

        addIngredientContext(ingredientContexts, 1, Measure.Ml, ingredientRepository.getIngredientById("cucumber"),  recipeRepository.getRecipeById(1L));
        addIngredientContext(ingredientContexts, 2, Measure.Sl, ingredientRepository.getIngredientById("paprika_red"), recipeRepository.getRecipeById(3L));
        addIngredientContext(ingredientContexts, 10, Measure.Sht, ingredientRepository.getIngredientById("cucumber_marinade"), recipeRepository.getRecipeById(1L));

        ingredientContextRepository.saveAll(ingredientContexts);

        addComments(comments, "Прекрасный рецепт. Спасибо вам огромное", 1L, 2L);
        addComments(comments, "Очень вкуснуя картошка. А главное - быстро.", 2L, 3L);
        addComments(comments, "Ммм, Лориса. Борщ просто вещь!", 3L, 1L);

        commentRepository.saveAll(comments);
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

    private void addIngredientContext(List<IngredientContext> ingredientContexts, int amount,
                                      Measure measure, Ingredient ingredient, Recipe recipe) {
        ingredientContexts.add(new IngredientContext(new IngredientContextDTO(amount, measure, ingredient, recipe)));
    }

    private void addIngredient(List<Ingredient> ingredients, String id, String name, IngredientCategory category){
        if(!ingredientRepository.existsById(id)){
            ingredients.add(new Ingredient(new IngredientDTO(id, name, category)));
        }
    }

    private void addRecipe(List<Recipe> recipes, String name, String description, User author, Category category ){
        if(!recipeRepository.existsByName(name)){
            recipes.add(new Recipe(new RecipeDTO(name, description, author, category)));
        }
    }

    private void addComments(List<Comment> comments, String commentText, Long recipeId, Long userId){
        comments.add(new Comment(new CommentDTO(commentText, recipeId, userId)));
    }
}
