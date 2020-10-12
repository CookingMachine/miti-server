package com.miti.server.config;

import com.miti.server.enums.IngredientCategory;
import com.miti.server.enums.Measure;
import com.miti.server.enums.Role;
import com.miti.server.model.entity.*;
import com.miti.server.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppInit implements ApplicationRunner {
  private final UserService userService;
  private final RecipeService recipeService;
  private final CategoryService categoryService;
  private final IngredientContextService ingredientContextService;
  private final IngredientService ingredientService;
  private final CommentService commentService;

  @Override
  public void run(ApplicationArguments args) {
    List<User> users = new ArrayList<>();
    List<Recipe> recipes = new ArrayList<>();
    List<Category> categories = new ArrayList<>();
    List<IngredientContext> ingredientContexts = new ArrayList<>();
    List<Ingredient> ingredients = new ArrayList<>();
    List<Comment> comments = new ArrayList<>();

    addUser(users, "admin", "$2y$12$lMziH.7icjkdRRpDVtaj4uAaG8qlVkBi/Hjl0e7aKsMXQY8i4HQNu", "admin@gmail.com", Role.ADMINISTRATION);
    addUser(users, "moder", "$2y$12$zbcKnFG.OX.7r2k3nqEyj.9psdQhVzIy5eBvtVQegbPGVHupC0NHS", "moder@gmail.com", Role.MODERATION);
    addUser(users, "user", "$2y$12$hrib5eAGooCIRrfViL4tju04yqpKdynd67YFqhCaTTPyOdjwEz1ia", "user@gmail.com", Role.USER);

    userService.addAllUsers(users);

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

    categoryService.addAllCategories(categories);

    addIngredient(ingredients, "tomato", "Помидор", IngredientCategory.VEGETABLES);
    addIngredient(ingredients, "cucumber", "Огурец свежий", IngredientCategory.VEGETABLES);
    addIngredient(ingredients, "cucumber_salt", "Малосоленный огурец", IngredientCategory.VEGETABLES);
    addIngredient(ingredients, "cucumber_marinade", "Маринованный огурец", IngredientCategory.VEGETABLES);
    addIngredient(ingredients, "paprika_red", "Красный болгарский перец", IngredientCategory.VEGETABLES);
    addIngredient(ingredients, "paprika_green", "Зелёный болгарский перец", IngredientCategory.VEGETABLES);
    addIngredient(ingredients, "paprika_yellow", "Жёлтый болгарский перец", IngredientCategory.VEGETABLES);
    addIngredient(ingredients, "apple", "яблоко", IngredientCategory.FRUITS);
    addIngredient(ingredients, "milk", "молоко", IngredientCategory.MILK_PRODUCTS);
    addIngredient(ingredients, "mussels", "мидии", IngredientCategory.SEA_PRODUCTS);
    addIngredient(ingredients, "beef", "говядина", IngredientCategory.MEAT);
    addIngredient(ingredients, "salmon", "лосось", IngredientCategory.FISH);
    addIngredient(ingredients, "italian", "итальянские специи", IngredientCategory.FLAVORING);
    addIngredient(ingredients, "basil", "базелик", IngredientCategory.GREENERY);
    addIngredient(ingredients, "olive_oil", "оливковое масло", IngredientCategory.OILS);
    addIngredient(ingredients, "paper", "перец", IngredientCategory.EXTRA);
    addIngredient(ingredients, "ice", "лёд", IngredientCategory.UNKNOWN);

    ingredientService.addAllIngredients(ingredients);

    addRecipe(recipes, "Салат оливье", "Классический салат Оливье в советское время готовили исключительно с вареной колбасой, желательно - с  Докторской. Мы не оступили от традиции и приготовили Оливье по канонам  советской гастрономии.",
        userService.getUserById(1L), categoryService.getCategoryById("salads"));
    addRecipe(recipes, "French Fries", "Просто рецепт приготовления картошки фри в сковороде! Картофель фри любим многими!",
        userService.getUserById(2L), categoryService.getCategoryById("snacks"));
    addRecipe(recipes, "Борщ", "Еще не знаете, какое первое блюдо сделать на обед? Я хочу показать вам несложный пошаговый способ, как приготовить самый вкусный борщ. Насыщенный, аппетитный и сытный… чудесная идея для всей семьи! Подробнее: https://povar.ru/recipes/samyi_vkusnyi_borsh-57233.html",
        userService.getUserById(3L), categoryService.getCategoryById("firsts"));

    recipeService.addAllRecipes(recipes);

    addIngredientContext(ingredientContexts, 1L, Measure.Ml, ingredientService.getIngredientById("cucumber"),  recipeService.getRecipeById(1L));
    addIngredientContext(ingredientContexts, 2L, Measure.Sl, ingredientService.getIngredientById("paprika_red"), recipeService.getRecipeById(3L));
    addIngredientContext(ingredientContexts, 10L, Measure.Sht, ingredientService.getIngredientById("cucumber_marinade"), recipeService.getRecipeById(1L));
    addIngredientContext(ingredientContexts, 1L, Measure.Kg, ingredientService.getIngredientById("cucumber"), recipeService.getRecipeById(2L));
    addIngredientContext(ingredientContexts, 1L, Measure.Chl, ingredientService.getIngredientById("paprika_red"), recipeService.getRecipeById(1L));
    addIngredientContext(ingredientContexts, 1L, Measure.Gr, ingredientService.getIngredientById("paprika_yellow"), recipeService.getRecipeById(1L));
    addIngredientContext(ingredientContexts, 1L, Measure.Lit, ingredientService.getIngredientById("tomato"), recipeService.getRecipeById(3L));
    addIngredientContext(ingredientContexts, 1L, Measure.St, ingredientService.getIngredientById("cucumber_salt"), recipeService.getRecipeById(1L));

    ingredientContextService.addAllIngredientContexts(ingredientContexts);

    addComments(comments, "Прекрасный рецепт. Спасибо вам огромное", recipeService.getRecipeById(1L), userService.getUserById(2L));
    addComments(comments, "Очень вкуснуя картошка. А главное - быстро.", recipeService.getRecipeById(2L), userService.getUserById(3L));
    addComments(comments, "Ммм, Лориса. Борщ просто вещь!", recipeService.getRecipeById(3L), userService.getUserById(1L));

    commentService.addAllComments(comments);
  }

  private void addUser(List<User> users, String username, String password, String email, Role role){
    users.add(new User(username, password, email, role));
  }

  private void addCategory(List<Category> categories, String id, String name){
    categories.add(new Category(id, name));
  }

  private void addIngredientContext(List<IngredientContext> ingredientContexts, Long amount,
                                    Measure measure, Ingredient ingredient, Recipe recipe) {
    ingredientContexts.add(new IngredientContext(amount, measure, ingredient, recipe));
  }

  private void addIngredient(List<Ingredient> ingredients, String id, String name, IngredientCategory category){
    ingredients.add(new Ingredient(id, name, category));
  }

  private void addRecipe(List<Recipe> recipes, String name, String description, User author, Category category ){
    recipes.add(new Recipe(name, description, author, category));
  }

  private void addComments(List<Comment> comments, String commentText, Recipe recipe, User user){
    comments.add(new Comment(commentText, user, recipe));
  }
}
